package deepslater.equilibrium.datagen;

import deepslater.equilibrium.Equilibrium;
import deepslater.equilibrium.item.ModItems;
import deepslater.equilibrium.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_,
                               CompletableFuture<TagLookup<Block>> p_275322_,
                               @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, Equilibrium.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ModTags.Items.TROPHY_DISC_COMPONENTS)
                .add(
                        Items.MUSIC_DISC_13,
                        Items.MUSIC_DISC_CAT,
                        Items.MUSIC_DISC_BLOCKS,
                        Items.MUSIC_DISC_CHIRP,
                        Items.MUSIC_DISC_FAR,
                        Items.MUSIC_DISC_MALL,
                        Items.MUSIC_DISC_MELLOHI,
                        Items.MUSIC_DISC_STAL,
                        Items.MUSIC_DISC_WARD,
                        Items.MUSIC_DISC_11,
                        Items.MUSIC_DISC_WAIT,
                        Items.MUSIC_DISC_OTHERSIDE,
                        Items.MUSIC_DISC_PIGSTEP,
                        Items.MUSIC_DISC_RELIC
                );

        this.tag(ItemTags.MUSIC_DISCS)
                .add(
                        ModItems.MUSIC_DISC_DIAMOND.get(),
                        ModItems.MUSIC_DISC_GOLD.get(),
                        ModItems.MUSIC_DISC_PLATINUM.get()
                );
    }
}