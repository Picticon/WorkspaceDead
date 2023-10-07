package workspacedead.datagen;

import java.util.function.Function;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.CopyNameFunction;
import net.minecraft.world.level.storage.loot.functions.CopyNbtFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.nbt.ContextNbtProvider;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.RegistryObject;
import workspacedead.block.GrassyPotatoPlantBlock;
import workspacedead.block.SpawnEggPlant.SpawnEggBlockEntity;
import workspacedead.item.custom.SpawnEggSeedsItem;
import workspacedead.registry.MyBlocks;
import workspacedead.registry.MyItems;

public class ModBlockLootTables extends BlockLoot {

    private static final float[] NORMAL_LEAVES_SAPLING_CHANCES = new float[] { 0.05F, 0.0625F, 0.083333336F, 0.1F };

    @Override
    protected void addTables() {
        this.add(MyBlocks.DEADLEAVES.get(),
                (block) -> createLeavesDrops(block, MyBlocks.DEADSAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.dropSelf(MyBlocks.DEADSAPLING.get());
        this.dropSelf(MyBlocks.DEADLOG.get());
        this.dropSelf(MyBlocks.DEADWOOD.get());
        this.dropSelf(MyBlocks.DEADLOG_STRIPPED.get());
        this.dropSelf(MyBlocks.DEADWOOD_STRIPPED.get());

        this.dropSelf(MyBlocks.DEADPLANKS.get());
        this.dropSelf(MyBlocks.BURNTPLANKS.get());

        this.add(MyBlocks.DEADDOOR.get(), BlockLoot::createDoorTable);
        this.dropSelf(MyBlocks.DEADTRAPDOOR.get());
        this.add(MyBlocks.BURNTDOOR.get(), BlockLoot::createDoorTable);
        this.dropSelf(MyBlocks.BURNTTRAPDOOR.get());
        this.dropSelf(MyBlocks.DEADSLAB.get());
        this.dropSelf(MyBlocks.BURNTSLAB.get());
        this.dropSelf(MyBlocks.DEADFENCE.get());
        this.dropSelf(MyBlocks.BURNTFENCE.get());
        this.dropSelf(MyBlocks.DEADFENCE_GATE.get());
        this.dropSelf(MyBlocks.BURNTFENCE_GATE.get());
        this.dropSelf(MyBlocks.DEADSTAIRS.get());
        this.dropSelf(MyBlocks.BURNTSTAIRS.get());

        this.dropSelf(MyBlocks.POTATOBLOCK.get());

        this.dropSelf(MyBlocks.DEADSTONE.get());
        this.dropSelf(MyBlocks.DEADDIRT.get());
        this.dropSelf(MyBlocks.DEADGRAVEL.get());
        this.dropSelf(MyBlocks.DEADSAND.get());
        this.dropSelf(MyBlocks.DEADCLAY.get());
        this.dropSelf(MyBlocks.DEADSLATE.get());
        this.add(MyBlocks.DEADORE.get(), (block) -> createOreDrop(block, MyItems.DEAD_ORE_CHUNK.get()));

        this.dropSelf(MyBlocks.POOPBLOCK.get());
        this.dropSelf(MyBlocks.POOPBLOCK2X.get());
        this.dropSelf(MyBlocks.POOP_O_LANTERN.get());
        this.dropSelf(MyBlocks.CARVED_POOPBLOCK.get());

        this.dropSelf(MyBlocks.BIOMASS_BLOCK.get());
        this.dropSelf(MyBlocks.MRHANKY_BLOCK.get());
        this.dropSelf(MyBlocks.DEAD_FARMLAND.get());

        this.dropSelf(MyBlocks.YELLOW_GENERATOR.get());
        this.dropSelf(MyBlocks.SATURATOR_BLOCK.get());
        this.dropSelf(MyBlocks.DESATURATOR_BLOCK.get());
        this.dropSelf(MyBlocks.FULLMETALALCHEMISER_BLOCK.get());

        LootItemCondition.Builder lootitemcondition$builder = //
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(MyBlocks.GRASSYPOTATO_PLANT.get())//
                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                .hasProperty(GrassyPotatoPlantBlock.AGE, 7));

        this.add(MyBlocks.DEADGRASS.get(),
                LootTable.lootTable()
                        .withPool(LootPool.lootPool()
                                .add(LootItem.lootTableItem(MyItems.DEADGRASS_SCRAPS.get())
                                        .when(LootItemRandomChanceCondition.randomChance(0.334F))
                                        .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE, 2)))));

        this.add(MyBlocks.GRASSYPOTATO_PLANT.get(), LootTable.lootTable()//
                .withPool(LootPool.lootPool()//
                        .add(LootItem.lootTableItem(Items.AIR)//
                                .when(lootitemcondition$builder)//
                                .otherwise(LootItem.lootTableItem(MyItems.GRASSYPOTATO_SEEDS.get()))))//
        );

        this.add(MyBlocks.SPAWNEGG_PLANT.get(),
                block -> droppingWithFunctions(block,
                        builder -> builder.apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY))
                                .apply(CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY)
                                        .copy(SpawnEggBlockEntity.entityidkey, SpawnEggSeedsItem.EssenceTagID))));

        // this.add(ModBlocks.SPAWNEGG_PLANT.get(), noDrop());
    }

    // point the block iterator at our blocks, not minecraft.
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return MyBlocks.MOD_BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }

    private static LootTable.Builder droppingWithFunctions(Block block,
            Function<LootItem.Builder<?>, LootItem.Builder<?>> mapping) {
        return LootTable.lootTable().withPool(applyExplosionCondition(block, LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1)).add(mapping.apply(LootItem.lootTableItem(block)))));
    }
}