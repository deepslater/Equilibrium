package deepslater.equilibrium.datagen.loot;

import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import net.minecraft.world.level.storage.loot.functions.EnchantWithLevelsFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemDamageFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;

public class ModChestLootTables implements LootTableSubProvider {
    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> p_250931_) {
        p_250931_.accept(ModLootTables.SOUL_WORKSHOP, soulWorkshopLootTable());
    }

    public static LootTable.Builder soulWorkshopLootTable() {
        return LootTable.lootTable()
                // Growth Catalyst pool - MOVE TO LOOT MODIFIERS TO PREVENT ISSUES
                .withPool(LootPool.lootPool()
                        .setRolls(rollsExactly(1.0f))
                        .setBonusRolls(rollsExactly(3.0f))
                        .add(nothingToAdd(99))
                        .add(itemToAdd(Items.SCULK_SHRIEKER, 1))
                )
                // Growth Catalyst ingredients
                .withPool(LootPool.lootPool()
                        .setRolls(rollsBetween(1.0f, 2.0f))
                        .setBonusRolls(rollsExactly(1.0f))
                        .add(itemsToAdd(Items.ECHO_SHARD, 25, 1.0f, 4.0f))
                        .add(itemsToAdd(Items.AMETHYST_SHARD, 25, 1.0f, 16.0f))
                        .add(itemsToAdd(Items.AMETHYST_CLUSTER, 20, 1.0f, 2.0f))
                        .add(itemToAdd(Items.BUDDING_AMETHYST, 5))
                        .add(itemToAdd(Items.SCULK_CATALYST, 25))
                )
                // Tools
                .withPool(LootPool.lootPool()
                        .setRolls(rollsExactly(1.0f))
                        .setBonusRolls(rollsExactly(1.0f))
                        .add(sEnchItemToAdd(Items.DIAMOND_SHOVEL, 20, 30.0f, 50.0f))
                        .add(sEnchItemToAdd(Items.NETHERITE_SHOVEL, 5, 30.0f, 50.0f))
                        .add(itemToAdd(Items.SPYGLASS, 25))
                        .add(sEnchItemToAdd(Items.DIAMOND_HOE, 25, 30.0f, 50.0f))
                        .add(nothingToAdd(25))
                )
                // Soul light items
                .withPool(LootPool.lootPool()
                        .setRolls(rollsBetween(1.0f, 2.0f))
                        .setBonusRolls(rollsExactly(1.0f))
                        .add(itemsToAdd(Items.SOUL_TORCH, 55, 16.0f, 64.0f))
                        .add(itemsToAdd(Items.SOUL_LANTERN, 30, 1.0f, 4.0f))
                        .add(itemToAdd(Items.SOUL_CAMPFIRE, 15))
                )
                // Soul Sand Valley items
                .withPool(LootPool.lootPool()
                        .setRolls(rollsBetween(1.0f, 2.0f))
                        .setBonusRolls(ConstantValue.exactly(1.0f))
                        .add(itemsToAdd(Items.SOUL_SAND, 30, 10.0f, 32.0f))
                        .add(itemsToAdd(Items.SOUL_SOIL, 30, 10.0f, 32.0f))
                        .add(itemsToAdd(Items.BONE_BLOCK, 30, 1.0f, 10.0f))
                        .add(nothingToAdd(10))
                );
    }

    public static NumberProvider rollsExactly(float amt) {
        return ConstantValue.exactly(amt);
    }

    public static NumberProvider rollsBetween(float minAmt, float maxAmt) {
        return UniformGenerator.between(minAmt, maxAmt);
    }

    public static LootPoolEntryContainer.Builder<?> itemToAdd(ItemLike item, int weight) {
        return LootItem.lootTableItem(item)
                .setWeight(weight)
                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0f)));
    }

    public static LootPoolEntryContainer.Builder<?> itemsToAdd(ItemLike item, int weight, float minAmt, float maxAmt) {
        return LootItem.lootTableItem(item)
                .setWeight(weight)
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minAmt, maxAmt)));
    }

    public static LootPoolEntryContainer.Builder<?> rEnchItemToAdd(ItemLike item, int weight) {
        return LootItem.lootTableItem(item)
                .setWeight(weight)
                .apply(EnchantRandomlyFunction.randomApplicableEnchantment())
                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0f)));
    }

    public static LootPoolEntryContainer.Builder<?> sEnchItemToAdd(
            ItemLike item, int weight, float minLevels, float maxLevels) {
        return LootItem.lootTableItem(item)
                .setWeight(weight)
                .apply(EnchantWithLevelsFunction.enchantWithLevels(UniformGenerator.between(minLevels, maxLevels))
                        .allowTreasure())
                .apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.2f, 0.6f)))
                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0f)));
    }

    @NotNull
    private static LootPoolEntryContainer.Builder<?> nothingToAdd(int weight) {
        return EmptyLootItem.emptyItem().setWeight(weight);
    }
}