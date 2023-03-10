package workspacedead.datagen;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.registries.RegistryObject;
import workspacedead.block.ModBlocks;
import workspacedead.block.GrassyPotatoPlant.GrassyPotatoPlantBlock;
import workspacedead.item.ModItems;

public class ModBlockLootTables extends BlockLoot {

    private static final float[] NORMAL_LEAVES_SAPLING_CHANCES = new float[] { 0.05F, 0.0625F, 0.083333336F, 0.1F };

    @Override
    protected void addTables() {
        this.add(ModBlocks.DEADLEAVES.get(), (block) -> createLeavesDrops(block, ModBlocks.DEADSAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.dropSelf(ModBlocks.DEADSAPLING.get());
        this.dropSelf(ModBlocks.DEADLOG.get());
        this.dropSelf(ModBlocks.DEADWOOD.get());
        this.dropSelf(ModBlocks.DEADLOG_STRIPPED.get());
        this.dropSelf(ModBlocks.DEADWOOD_STRIPPED.get());

        this.dropSelf(ModBlocks.DEADPLANKS.get());
        this.dropSelf(ModBlocks.BURNTPLANKS.get());

        this.add(ModBlocks.DEADDOOR.get(), BlockLoot::createDoorTable);
        this.dropSelf(ModBlocks.DEADTRAPDOOR.get());
        this.add(ModBlocks.BURNTDOOR.get(), BlockLoot::createDoorTable);
        this.dropSelf(ModBlocks.BURNTTRAPDOOR.get());
        this.dropSelf(ModBlocks.DEADSLAB.get());
        this.dropSelf(ModBlocks.BURNTSLAB.get());
        this.dropSelf(ModBlocks.DEADFENCE.get());
        this.dropSelf(ModBlocks.BURNTFENCE.get());
        this.dropSelf(ModBlocks.DEADFENCE_GATE.get());
        this.dropSelf(ModBlocks.BURNTFENCE_GATE.get());
        this.dropSelf(ModBlocks.DEADSTAIRS.get());
        this.dropSelf(ModBlocks.BURNTSTAIRS.get());

        this.dropSelf(ModBlocks.DEADSTONE.get());
        this.dropSelf(ModBlocks.DEADDIRT.get());
        this.dropSelf(ModBlocks.DEADGRAVEL.get());
        this.dropSelf(ModBlocks.DEADSAND.get());
        this.dropSelf(ModBlocks.DEADCLAY.get());
        this.dropSelf(ModBlocks.DEADSLATE.get());

        this.dropSelf(ModBlocks.POOPBLOCK.get());
        this.dropSelf(ModBlocks.POOPBLOCK2X.get());
        this.dropSelf(ModBlocks.POOP_O_LANTERN.get());
        this.dropSelf(ModBlocks.CARVED_POOPBLOCK.get());

        this.dropSelf(ModBlocks.BIOMASS_BLOCK.get());

        LootItemCondition.Builder lootitemcondition$builder = //
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.GRASSYPOTATO_PLANT.get())//
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(GrassyPotatoPlantBlock.AGE, 7));

        // this.dropOther(ModBlocks.GRASSYPOTATO_PLANT.get(),
        // ModItems.GRASSYPOTATO_SEEDS.get());
        // this.add(ModBlocks.GRASSYPOTATO_PLANT.get(),
        // createCropDrops(ModBlocks.GRASSYPOTATO_PLANT.get(), Items.AIR, //
        // ModItems.GRASSYPOTATO_SEEDS.get(), lootitemcondition$builder));
        this.add(ModBlocks.GRASSYPOTATO_PLANT.get(), LootTable.lootTable()//
                .withPool(LootPool.lootPool()//
                        .add(LootItem.lootTableItem(Items.AIR)//
                                .when(lootitemcondition$builder)//
                                .otherwise(LootItem.lootTableItem(ModItems.GRASSYPOTATO_SEEDS.get()))))//
        );
    }

    // point the block iterator at our blocks, not minecraft.
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.MOD_BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}