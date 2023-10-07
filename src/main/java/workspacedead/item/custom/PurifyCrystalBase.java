package workspacedead.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import workspacedead.advancement.ModCriteriaTriggers;
import workspacedead.config.CommonConfig;
import workspacedead.particle.ModParticles;
import workspacedead.registry.MySounds;

import java.util.ArrayList;

public class PurifyCrystalBase extends Item {
    private final int _strength;

    public PurifyCrystalBase(Properties pProperties, int strength) {
        super(pProperties);
        _strength = strength;
    }

    protected void Particleify(Level level, BlockPos pos, double yoffset) {
        if (level.isClientSide()) {
            // create particles... client only, sad. not sure how to do it server side
            // (yet?)
            for (var i = 0; i < 360; i += 20) {
                var x = pos.getX() + 0.5d + Math.cos(i) * .9;
                var y = pos.getY() + .5 + yoffset;
                var z = pos.getZ() + 0.5d + Math.sin(i) * .9;
                level.addParticle(ModParticles.PURIFY_PARTICLES.get(), true, x, y, z, Math.cos(i) * -0.11d, -0.05d,
                        Math.sin(i) * -0.11d);
            }
        }
    }

    // purify block:
    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        var blockpos = pContext.getClickedPos();
        // var pos = pContext.getClickedPos();
        // var block = pContext.getLevel().getBlockEntity(pos);

        var list = new ArrayList<String>(CommonConfig.weak_purify_blocks.get());
        if (_strength > 50) {
            list.addAll(CommonConfig.purify_blocks.get());
        }
        var crouching = pContext.isSecondaryUseActive();
        var cnt = 0;
        if (_strength > 0 && crouching) {
            var range = 1;
            if (_strength > 50)
                range = 2;
            for (var z = -range; z <= range; z++) {
                for (var y = -range; y <= range; y++) {
                    for (var x = -range; x <= range; x++) {
                        if (Math.abs(x) == range && Math.abs(y) == range && Math.abs(z) == range)
                            continue;
                        var nblockpos = blockpos.offset(x, y, z);
                        cnt += transformBlock(nblockpos, list, pContext);
                    }
                }
            }
        } else {
            var nblockpos = blockpos.offset(0, 0, 0);
            cnt += transformBlock(nblockpos, list, pContext);
        }
        if (cnt > 0) {
            pContext.getLevel().playSound(pContext.getPlayer(), blockpos, MySounds.PURIFY.get(), SoundSource.BLOCKS, 1,
                    1);
            pContext.getLevel().playSound(pContext.getPlayer(), blockpos, SoundEvents.AMETHYST_BLOCK_HIT,
                    SoundSource.BLOCKS, .5f, 1.4f);
            if (pContext.getPlayer() instanceof ServerPlayer player)
                ModCriteriaTriggers.PURIFICATION.trigger(player);
            return InteractionResult.SUCCESS;
        }
        return super.useOn(pContext);
    }

    private int transformBlock(BlockPos nblockpos, ArrayList<String> list, UseOnContext pContext) {
        var level = pContext.getLevel();
        var blockstate = level.getBlockState(nblockpos);
        Block found;
        Block ato = null;
        for (String t : list) {
            var arr = t.split(":");
            if (arr.length == 4) {
                found = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(arr[0], arr[1]));
                if (found != null && blockstate.is(found))
                    ato = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(arr[2], arr[3]));
            }
        }
        if (ato != null) {
            // swap block
            if (!level.isClientSide())
                level.setBlock(nblockpos, ato.defaultBlockState(), 1 | 2);
            Particleify(level, nblockpos, .7);
            return 1;
        }
        return 0;
    }

    // purify livingentity:
    @Override
    public InteractionResult interactLivingEntity(ItemStack pStack, Player pPlayer, LivingEntity pInteractionTarget,
            InteractionHand pUsedHand) {
        var list = new ArrayList<String>(CommonConfig.weak_purify_mobs.get());
        if (_strength > 50) {
            list.addAll(CommonConfig.purify_mobs.get());
        }
        for (String t : list) {
            var arr = t.split(":");
            if (arr.length == 4) {
                var ent = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(arr[0], arr[1]));
                if (ent != null)
                    if (pInteractionTarget.getType() == ent) {
                        if (!pPlayer.level.isClientSide()) {
                            var newent = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(arr[2], arr[3]));
                            if (newent != null) {
                                var heyhey = newent.create(pPlayer.level);
                                if (heyhey != null) {
                                    heyhey.moveTo(pInteractionTarget.blockPosition(), pInteractionTarget.getYRot(),
                                            pInteractionTarget.getXRot());
                                    if (heyhey instanceof LivingEntity le) {
                                        le.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 120));
                                        le.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 120));
                                        le.setSpeed(0);
                                    }
                                    if (pInteractionTarget instanceof IronGolem ig && heyhey instanceof IronGolem ig2) {
                                        ig2.setPlayerCreated(ig.isPlayerCreated());
                                    }
                                    pPlayer.level.addFreshEntity(heyhey);
                                    heyhey.setXRot(pInteractionTarget.getXRot());
                                    heyhey.setYRot(pInteractionTarget.getYRot());
                                    pInteractionTarget.discard();
                                }
                            }
                        }
                        Particleify(pPlayer.level, pInteractionTarget.getOnPos(), 1.4);
                        pPlayer.level.playSound(pPlayer, pInteractionTarget.blockPosition(), MySounds.PURIFY.get(),
                                SoundSource.PLAYERS, 1, 1);
                        pPlayer.level.playSound(pPlayer, pInteractionTarget.blockPosition(),
                                SoundEvents.AMETHYST_BLOCK_HIT, SoundSource.PLAYERS, .5f, 1.4f);
                        if (pPlayer instanceof ServerPlayer serverplayer)
                            ModCriteriaTriggers.PURIFICATION.trigger(serverplayer);
                        return InteractionResult.SUCCESS;
                    }
            }
        }
        return super.interactLivingEntity(pStack, pPlayer, pInteractionTarget, pUsedHand);
    }

}
