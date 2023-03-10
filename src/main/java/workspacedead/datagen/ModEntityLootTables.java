package workspacedead.datagen;

import java.util.Map;
import java.util.stream.Collectors;

import net.minecraft.data.loot.EntityLoot;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.ForgeRegistries;
import workspacedead.WorkspaceDead;
import workspacedead.entity.ModEntityTypes;
import workspacedead.item.ModItems;

public class ModEntityLootTables extends EntityLoot {
    @Override
    protected void addTables() {
        var enderrod = LootTable.lootTable()//
                .withPool(LootPool.lootPool()//
                        .setRolls(ConstantValue.exactly(1.0F))//
                        .add(LootItem.lootTableItem(ModItems.ENDER_ROD.get())//
                                .apply(SetItemCountFunction//
                                        .setCount(UniformGenerator//
                                                .between(0.0F, 1.0F)))//
                                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))//
                        .when(LootItemKilledByPlayerCondition.killedByPlayer()));
        this.add(ModEntityTypes.DRACONICBLAZE.get(), enderrod);

        var bones = LootTable.lootTable()//
                .withPool(LootPool.lootPool()//
                        .setRolls(ConstantValue.exactly(1.0F))//
                        .add(LootItem.lootTableItem(Items.BONE)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                                .apply(LootingEnchantFunction
                                        .lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));

        this.add(ModEntityTypes.SKELETONCHICKEN.get(), bones);
        this.add(ModEntityTypes.SKELETONCOW.get(), bones);
        this.add(ModEntityTypes.SKELETONSHEEP.get(), bones);
        this.add(ModEntityTypes.SKELETONPIG.get(), bones);
        this.add(ModEntityTypes.SKELETONSPIDER.get(), bones);
        this.add(ModEntityTypes.SKELETONSLIME.get(), bones);
        this.add(ModEntityTypes.BONE_GOLEM.get(), bones);

        var potatoes = LootTable.lootTable()//
                .withPool(LootPool.lootPool()//
                        .setRolls(ConstantValue.exactly(1.0F))//
                        .add(LootItem.lootTableItem(Items.POTATO)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                                .apply(LootingEnchantFunction
                                        .lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))));

        this.add(ModEntityTypes.GRASSYPOTATO.get(), potatoes);
    }

    @Override
    protected Iterable<EntityType<?>> getKnownEntities() {
        return ForgeRegistries.ENTITIES//
                .getEntries()//
                .stream()//
                .filter(blocks -> blocks.getKey().location().getNamespace().equals(WorkspaceDead.MOD_ID)) //
                .map(Map.Entry::getValue)//
                .collect(Collectors.toList());
    }
}
