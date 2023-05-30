package workspacedead.effect;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Entity.RemovalReason;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import workspacedead.sound.ModSounds;

public class DoomedEffect extends MobEffect {

    protected DoomedEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        super.applyEffectTick(pLivingEntity, pAmplifier);
        var einstance = pLivingEntity.getActiveEffectsMap().get(ModEffects.DOOMED.get());
        if (einstance != null) {
            if (pLivingEntity instanceof Mob) {
                var dur = einstance.getDuration();
                if (dur < 40  && pLivingEntity.level.isClientSide())
                    animateTick(pLivingEntity);
                if (dur < 2) {
                    pLivingEntity.playSound(ModSounds.POP.get(), 1F, 1);
                    pLivingEntity.remove(RemovalReason.KILLED);

                }
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;// (pDuration == 1);
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(LivingEntity entity) {
        if (entity.level.isClientSide) {
            for (int i = 0; i < 2; ++i) {
                entity.level.addParticle(ParticleTypes.FLAME, //
                        entity.getRandomX(0.5D), //
                        entity.getRandomY() - 0.25D, //
                        entity.getRandomZ(0.5D), //
                        0,0,0);
                        //(entity.level.random.nextDouble() - 0.5D) * 2.0D, //
                        //-entity.level.random.nextDouble(), //
                        //(entity.level.random.nextDouble() - 0.5D) * 2.0D);
            }
            for (int i = 0; i < 2; ++i) {
                entity.level.addParticle(ParticleTypes.SMOKE, //
                        entity.getRandomX(0.5D), //
                        entity.getRandomY() - 0.25D, //
                        entity.getRandomZ(0.5D), //
                        0,0,0);
                        //(entity.level.random.nextDouble() - 0.5D) * 2.0D, //
                        //-entity.level.random.nextDouble(), //
                        //(entity.level.random.nextDouble() - 0.5D) * 2.0D);
            }
        }
    }
}
