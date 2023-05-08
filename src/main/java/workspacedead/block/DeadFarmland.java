package workspacedead.block;

import java.util.List;
import java.util.Random;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.eventbus.api.Event;
import workspacedead.effect.ModEffects;
import workspacedead.util.Chatter;

///
/// This works like Extra Utilities cursed earth
///
public class DeadFarmland extends Block {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public DeadFarmland(Properties pProperties) {
        super(pProperties);
        // this.registerDefaultState(this.stateDefinition.any().setValue(FACING,
        // Direction.NORTH));
    }

    // basically just check light level
    public boolean shouldSpawnMob(Level level, BlockPos pos) {
        return level.getMaxLocalRawBrightness(pos.above()) < 5;
    }

    // @SuppressWarnings("deprecation")
    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        // super.onPlace(state, level, pos, oldState, isMoving);
        level.scheduleTick(pos, this, Mth.nextInt(RANDOM, 20, 60)); // initial tick is smaller than the ongoing tick
    }

    // @Deprecated
    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, Random rand) {
        // super.tick(state, level, pos, rand);
        level.scheduleTick(pos, this, Mth.nextInt(RANDOM, 20 * 5, 20 * 10)); // 5 - 10 second delay
        if (shouldSpawnMob(level, pos)) {
            AABB areaToCheck = new AABB(pos).inflate(6, 2, 6);
            int entityCount = level
                    .getEntitiesOfClass(Mob.class, areaToCheck, entity -> entity != null && entity instanceof Enemy)
                    .size();

            if (entityCount < 8)
                spawnMob(level, pos);
        }
    }

    public void spawnMob(ServerLevel level, BlockPos pos) {
        Holder<Biome> biomeHolder = level.getBiome(pos);
        Biome biome = biomeHolder.value();
        List<SpawnerData> spawns = biome.getMobSettings().getMobs(MobCategory.MONSTER).unwrap();
        if (!spawns.isEmpty()) {
            int indexSize = spawns.size();
            // if (level.random.nextInt(10) == 0)
            // Chatter.chat("Mob List = " + indexSize);
            EntityType<?> type = spawns.get(RANDOM.nextInt(indexSize)).type;
            // Chatter.chat("Spawning " + type.toString());
            // if (type.is(ModTags.Entities.NO_DIRT_SPAWN))
            // return;
            // spawnplacement=SpawnPlacements.getPlacementType(type)
            var spawnplacement = SpawnPlacements.Type.NO_RESTRICTIONS;
            if (type == null || !NaturalSpawner.isSpawnPositionOk(spawnplacement, level, pos.above(), type)) {
                // Chatter.chat("Can't spawn " + type.toString());
                return;
            }
            Mob entity = (Mob) type.create(level);
            if (entity == null) {
                // Chatter.chat("Can't create " + type.toString());
            } else {
                entity.setPos(pos.getX() + 0.5D, pos.getY() + 1D, pos.getZ() + 0.5D);
                if (level.getEntities(entity.getType(), entity.getBoundingBox(), EntitySelector.ENTITY_STILL_ALIVE)
                        .isEmpty() && level.noCollision(entity)) {
                    // var result = ForgeEventFactory.canEntitySpawn(entity, level, pos.getX() +
                    // 0.5D, pos.getY() + 1D,
                    // pos.getZ() + 0.5D, null, MobSpawnType.SPAWNER); 
                    // if (result == Event.Result.DENY) {
                    // // Chatter.chat("Spawn denied " + type.toString());
                    // return;
                    // }
                    entity.addEffect(new MobEffectInstance(ModEffects.DOOMED.get(), 15 * 20)); // 15 second lifespan
                    entity.finalizeSpawn(level, level.getCurrentDifficultyAt(pos), MobSpawnType.NATURAL, null, null);
                    level.addFreshEntity(entity);
                }
            }
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, Level level, BlockPos pos, Random rand) {
        for (int i = 0; i < 1; ++i) {
            double d0 = (double) ((float) pos.getX() + rand.nextFloat());
            double d1 = (double) ((float) pos.getY() + rand.nextFloat());
            double d2 = (double) ((float) pos.getZ() + rand.nextFloat());
            double d3 = ((double) rand.nextFloat() - 0.5D) * 0.1D;
            double d4 = ((double) rand.nextFloat() - 0.5D) * 0.1D;
            double d5 = ((double) rand.nextFloat() - 0.5D) * 0.1D;
            level.addParticle(ParticleTypes.ELECTRIC_SPARK, d0, d1, d2, d3, d4, d5);
        }
    }

}
