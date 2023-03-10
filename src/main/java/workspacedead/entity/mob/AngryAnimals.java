package workspacedead.entity.mob;

import java.util.Random;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.LevelAccessor;
import workspacedead.block.ModBlocks;

public class AngryAnimals {
    public static boolean checkAnimalSpawnRules(EntityType<? extends Monster> pAnimal, LevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, Random pRandom) {
        return pLevel.getBlockState(pPos.below()).is(ModBlocks.DEADDIRT.get()) || pLevel.getBlockState(pPos.below()).is(ModBlocks.DEADSAND.get());
    }
}
