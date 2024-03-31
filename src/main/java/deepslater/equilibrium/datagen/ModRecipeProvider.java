package deepslater.equilibrium.datagen;

import deepslater.equilibrium.block.ModBlocks;
import deepslater.equilibrium.item.ModItems;
import deepslater.equilibrium.util.ModTags;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        // Block Recipes
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ECHOES_BLOCK.get())
                .pattern("EE")
                .pattern("EE")
                .define('E', Items.ECHO_SHARD)
                .unlockedBy(getHasName(Items.ECHO_SHARD), has(Items.ECHO_SHARD))
                .save(consumer);

        // Item Recipes
        // Diamond Disc recipe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MUSIC_DISC_DIAMOND.get())
                .pattern("PPP")
                .pattern("P5P")
                .pattern("PPP")
                .define('P', ModItems.MUSIC_DISC_PLATINUM.get())
                .define('5', Items.MUSIC_DISC_5)
                .unlockedBy(getHasName(ModItems.MUSIC_DISC_PLATINUM.get()), has(ModItems.MUSIC_DISC_PLATINUM.get()))
                .save(consumer);

        // Platinum Disc Recipes
        // Half gold half 5
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.MUSIC_DISC_PLATINUM.get())
                .requires(
                        Ingredient.of(ModTags.Items.TROPHY_DISC_COMPONENTS), 5
                )
                .requires(ModItems.MUSIC_DISC_GOLD.get())
                .unlockedBy(getHasName(ModItems.MUSIC_DISC_GOLD.get()), has(ModItems.MUSIC_DISC_GOLD.get()))
                .save(consumer, "equilibrium:platinum_disc_from_discs");
        // Two golds
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.MUSIC_DISC_PLATINUM.get())
                .requires(Ingredient.of(ModItems.MUSIC_DISC_GOLD.get()),2)
                .unlockedBy(getHasName(ModItems.MUSIC_DISC_GOLD.get()), has(ModItems.MUSIC_DISC_GOLD.get()))
                .save(consumer,"equilibrium:platinum_disc_from_gold");

        // Gold Disc recipe
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.MUSIC_DISC_GOLD.get())
                .requires(Ingredient.of(ModTags.Items.TROPHY_DISC_COMPONENTS), 5)
                .unlockedBy(getHasName(Items.MUSIC_DISC_13), has(Items.MUSIC_DISC_13))
                .save(consumer);
    }

    // Future recipe helper method(s) for shapeless, shaped, etc
}