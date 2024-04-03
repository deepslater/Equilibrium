package deepslater.equilibrium.datagen.loot;

import com.mojang.datafixers.kinds.Const;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.EmptyLootItem;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;

public class ModChestLootTables implements LootTableSubProvider {
    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> p_250931_) {
        p_250931_.accept(ModLootTables.SOUL_WORKSHOP, soulWorkshopLootTable());
        p_250931_.accept(ModLootTables.SEMIPRECIOUS_ARMOR, semipreciousArmorLootTable());
        p_250931_.accept(ModLootTables.PRECIOUS_UNDERGROUND_TOOL, preciousUndergroundToolLootTable());
        p_250931_.accept(ModLootTables.MINECART_WITH_INGREDIENT, minecartWithIngredientLootTable());
        p_250931_.accept(BuiltInLootTables.SIMPLE_DUNGEON, simpleDungeonLootTable());
        p_250931_.accept(BuiltInLootTables.ABANDONED_MINESHAFT, mineshaftLootTable());
        p_250931_.accept(BuiltInLootTables.VILLAGE_WEAPONSMITH, villageWeaponsmithLootTable());
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
                        .add(levelsEnchTItemToAdd(Items.DIAMOND_SHOVEL, 20, 30.0f, 50.0f))
                        .add(levelsEnchTItemToAdd(Items.NETHERITE_SHOVEL, 5, 30.0f, 50.0f))
                        .add(itemToAdd(Items.SPYGLASS, 25))
                        .add(levelsEnchTItemToAdd(Items.DIAMOND_HOE, 25, 30.0f, 50.0f))
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
                        .setBonusRolls(rollsExactly(1.0f))
                        .add(itemsToAdd(Items.SOUL_SAND, 30, 10.0f, 32.0f))
                        .add(itemsToAdd(Items.SOUL_SOIL, 30, 10.0f, 32.0f))
                        .add(itemsToAdd(Items.BONE_BLOCK, 30, 1.0f, 10.0f))
                        .add(nothingToAdd(10))
                );
    }

    public static LootTable.Builder villageWeaponsmithLootTable() {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(rollsBetween(3.0f, 8.0f))
                        .setBonusRolls(rollsExactly(1.0f))
                        .add(itemsToAdd(Items.STICK, 16, 3.0f, 7.0f))
                        .add(itemsToAdd(Items.COAL, 12,3.0f, 7.0f))
                        .add(itemsToAdd(Items.IRON_INGOT, 12, 1.0f, 5.0f))
                        .add(itemsToAdd(Items.FLINT, 16, 3.0f, 7.0f))
                        .add(itemsToAdd(Items.GOLD_INGOT, 7, 1.0f, 3.0f))
                        .add(itemsToAdd(Items.OBSIDIAN, 7, 3.0f, 7.0f))
                        .add(itemToAdd(Items.IRON_AXE, 7))
                        .add(itemToAdd(Items.IRON_SWORD, 7))
                        .add(itemsToAdd(Items.EMERALD, 5, 1.0f, 4.0f))
                        .add(levelsEnchItemToAdd(Items.IRON_AXE, 5, 5.0f, 19.0f))
                        .add(levelsEnchItemToAdd(Items.IRON_SWORD, 5, 5.0f, 19.0f))
                        .add(LootItem.lootTableItem(Items.TRIDENT)
                                .setWeight(1)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0f)))
                                .apply(SetItemDamageFunction.setDamage(
                                        UniformGenerator.between(0.1f, 0.2f))
                                )
                        )
                );
    }

    public static LootTable.Builder mineshaftLootTable() {
        return LootTable.lootTable()
                // Efficiency
                .withPool(LootPool.lootPool()
                        .setRolls(rollsExactly(1.0f))
                        .add(nothingToAdd(80))
                        .add(LootItem.lootTableItem(Items.BOOK).setWeight(20)
                                .apply((new EnchantRandomlyFunction.Builder())
                                .withEnchantment(Enchantments.BLOCK_EFFICIENCY))
                        )
                )
                // Food
                .withPool(LootPool.lootPool()
                        .setRolls(rollsExactly(1.0f))
                        .setBonusRolls(rollsExactly(1.0f))
                        .add(itemToAdd(Items.GOLDEN_APPLE, 28))
                        .add(itemToAdd(Items.ENCHANTED_GOLDEN_APPLE, 2))
                        .add(itemsToAdd(Items.GOLDEN_CARROT, 60, 1.0f, 9.0f))
                        .add(itemsToAdd(Items.SPIDER_EYE, 10, 1.0f, 4.0f))
                )
                // Rails
                .withPool(LootPool.lootPool()
                        .setRolls(rollsExactly(3.0f))
                        .setBonusRolls(rollsExactly(1.0f))
                        .add(tableToAdd(ModLootTables.MINECART_WITH_INGREDIENT, 20,0))
                        .add(itemsToAdd(Items.RAIL, 40, 4.0f, 8.0f))
                        .add(itemsToAdd(Items.POWERED_RAIL, 25, 1.0f, 4.0f))
                        .add(itemsToAdd(Items.DETECTOR_RAIL, 8, 1.0f, 4.0f))
                        .add(itemsToAdd(Items.ACTIVATOR_RAIL, 7, 1.0f, 4.0f))
                )
                // Tools
                .withPool(LootPool.lootPool()
                        .setRolls(rollsBetween(2.0f, 4.0f))
                        .setBonusRolls(rollsExactly(1.0f))
                        .add(itemsToAdd(Items.REDSTONE, 10, 4.0f, 9.0f))
                        .add(itemsToAdd(Items.LAPIS_LAZULI, 10, 4.0f, 9.0f))
                        .add(itemsToAdd(Items.COAL, 20, 3.0f, 8.0f))
                        .add(itemsToAdd(Items.RAW_IRON, 20, 1.0f, 5.0f))
                        .add(itemsToAdd(Items.RAW_COPPER, 20, 9.0f, 20.0f))
                        .add(itemToAdd(Items.NAME_TAG, 17))
                        .add(itemToAdd(Items.IRON_PICKAXE, 3))
                )
                // Treasure
                .withPool(LootPool.lootPool()
                        .setRolls(rollsExactly(1.0f))
                        .setBonusRolls(rollsExactly(1.0f))
                        .add(rEnchItemToAdd(Items.BOOK, 15))
                        .add(itemsToAdd(Items.DIAMOND, 10, 1.0f, 2.0f))
                        .add(itemsToAdd(Items.RAW_GOLD, 15, 1.0f, 3.0f))
                        .add(itemToAdd(Items.MUSIC_DISC_CAT, 20))
                        .add(nothingToAdd(40))
                );
    }

    public static LootTable.Builder simpleDungeonLootTable() {
        return LootTable.lootTable()
                // Seeds
                .withPool(LootPool.lootPool()
                        .setRolls(rollsExactly(1.0f))
                        .setBonusRolls(rollsExactly(1.0f))
                        .add(itemsToAdd(Items.PUMPKIN_SEEDS, 35, 1.0f, 4.0f))
                        .add(itemsToAdd(Items.MELON_SEEDS, 65, 1.0f, 4.0f))
                )
                // Food
                .withPool(LootPool.lootPool()
                        .setRolls(rollsExactly(1.0f))
                        .setBonusRolls(rollsExactly(1.0f))
                        .add(itemToAdd(Items.ENCHANTED_GOLDEN_APPLE, 3))
                        .add(itemsToAdd(Items.GOLDEN_APPLE, 25, 1.0f, 3.0f))
                        .add(itemsToAdd(Items.GOLDEN_CARROT, 32, 1.0f, 9.0f))
                        .add(itemsToAdd(Items.COOKIE, 40, 5.0f, 10.0f))
                )
                // Tools
                .withPool(LootPool.lootPool()
                        .setRolls(rollsBetween(1.0f, 4.0f))
                        .setBonusRolls(rollsExactly(1.0f))
                        .add(tableToAdd(ModLootTables.SEMIPRECIOUS_ARMOR, 17, 0))
                        .add(tableToAdd(ModLootTables.PRECIOUS_UNDERGROUND_TOOL, 17, 0))
                        .add(itemsToAdd(Items.NAME_TAG, 17, 1.0f, 3.0f))
                        .add(itemToAdd(Items.BUCKET, 17))
                        .add(itemsToAdd(Items.IRON_INGOT, 17, 1.0f, 6.0f))
                        .add(itemsToAdd(Items.COAL, 17, 1.0f, 6.0f))
                        .add(itemsToAdd(Items.REDSTONE, 15, 1.0f, 6.0f))

                )
                // Treasure
                .withPool(LootPool.lootPool()
                        .setRolls(rollsBetween(1.0f, 2.0f))
                        .setBonusRolls(rollsExactly(1.0f))
                        .add(rEnchItemToAdd(Items.BOOK, 15))
                        .add(itemToAdd(Items.MUSIC_DISC_13, 22))
                        .add(itemToAdd(Items.MUSIC_DISC_11, 22))
                        .add(itemsToAdd(Items.GOLD_INGOT, 41, 1.0f, 6.0f))
                );
    }

    // Helper tables
    public static LootTable.Builder semipreciousArmorLootTable() {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(rollsExactly(1.0f))
                        .add(cursedItemToAdd(Items.CHAINMAIL_HELMET, 9, 1.0f, 30.0f))
                        .add(cursedItemToAdd(Items.CHAINMAIL_CHESTPLATE, 9, 1.0f, 30.0f))
                        .add(cursedItemToAdd(Items.CHAINMAIL_LEGGINGS, 9, 1.0f, 30.0f))
                        .add(cursedItemToAdd(Items.CHAINMAIL_BOOTS, 9, 15.0f, 30.0f))
                        .add(cursedItemToAdd(Items.IRON_HELMET, 8, 1.0f, 10.0f))
                        .add(cursedItemToAdd(Items.IRON_CHESTPLATE, 8, 1.0f, 10.0f))
                        .add(cursedItemToAdd(Items.IRON_LEGGINGS, 8, 1.0f, 10.0f))
                        .add(cursedItemToAdd(Items.IRON_BOOTS, 8, 1.0f, 10.0f))
                        .add(cursedItemToAdd(Items.GOLDEN_HELMET, 8, 20.0f, 25.0f))
                        .add(cursedItemToAdd(Items.GOLDEN_CHESTPLATE, 8, 20.0f, 25.0f))
                        .add(cursedItemToAdd(Items.GOLDEN_LEGGINGS, 8, 20.0f, 25.0f))
                        .add(cursedItemToAdd(Items.GOLDEN_BOOTS, 8, 20.0f, 25.0f))
                );
    }

    public static LootTable.Builder preciousUndergroundToolLootTable() {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(rollsExactly(1.0f))
                        .add(cursedItemToAdd(Items.IRON_SWORD, 30, 1.0f, 10.0f))
                        .add(cursedItemToAdd(Items.GOLDEN_SWORD, 19, 20.0f, 25.0f))
                        .add(cursedItemToAdd(Items.DIAMOND_SWORD, 1, 1.0f, 5.0f))
                        .add(cursedItemToAdd(Items.IRON_PICKAXE, 30, 1.0f, 10.0f))
                        .add(cursedItemToAdd(Items.GOLDEN_PICKAXE, 19, 20.0f, 25.0f))
                        .add(cursedItemToAdd(Items.DIAMOND_PICKAXE, 1, 1.0f, 5.0f))
                );
    }

    public static LootTable.Builder minecartWithIngredientLootTable() {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(rollsExactly(1.0f))
                        .add(nothingToAdd(25))
                        .add(itemToAdd(Items.TNT, 25))
                        .add(itemToAdd(Items.HOPPER, 25))
                        .add(itemToAdd(Items.FURNACE, 25))
        );
    }

    // Helper methods
    public static NumberProvider rollsExactly(float amt) {
        return ConstantValue.exactly(amt);
    }

    public static NumberProvider rollsBetween(float minAmt, float maxAmt) {
        return UniformGenerator.between(minAmt, maxAmt);
    }

    public static LootPoolEntryContainer.Builder<?> tableToAdd(ResourceLocation table, int weight, int quality){
        return LootTableReference.lootTableReference(table)
                .setWeight(weight)
                .setQuality(quality);
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

    public static LootPoolEntryContainer.Builder<?> levelsEnchItemToAdd(
            ItemLike item, int weight, float minLevels, float maxLevels) {
        return LootItem.lootTableItem(item)
                .setWeight(weight)
                .apply(EnchantWithLevelsFunction.enchantWithLevels(UniformGenerator.between(minLevels, maxLevels)))
                .apply(SetItemDamageFunction.setDamage(ConstantValue.exactly(1.0f)))
                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0f)));
    }

    public static LootPoolEntryContainer.Builder<?> levelsEnchTItemToAdd(
            ItemLike item, int weight, float minLevels, float maxLevels) {
        return LootItem.lootTableItem(item)
                .setWeight(weight)
                .apply(EnchantWithLevelsFunction.enchantWithLevels(UniformGenerator.between(minLevels, maxLevels))
                        .allowTreasure())
                .apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.1f, 1.0f)))
                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0f)));
    }

    public static LootPoolEntryContainer.Builder<?> cursedItemToAdd(
            ItemLike item, int weight, float minLevels, float maxLevels) {
        return LootItem.lootTableItem(item)
                .setWeight(weight)
                .apply(EnchantWithLevelsFunction.enchantWithLevels(UniformGenerator.between(minLevels, maxLevels))
                        .allowTreasure())
                .apply(new SetEnchantmentsFunction.Builder()
                        .withEnchantment(Enchantments.VANISHING_CURSE, (ConstantValue.exactly(1)))
                )
                .apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.4f, 0.6f)))
                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0f)));
    }

    @NotNull
    private static LootPoolEntryContainer.Builder<?> nothingToAdd(int weight) {
        return EmptyLootItem.emptyItem().setWeight(weight);
    }
}