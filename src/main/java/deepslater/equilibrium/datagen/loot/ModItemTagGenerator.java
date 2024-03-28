package deepslater.equilibrium.datagen.loot;

import deepslater.equilibrium.Equilibrium;
import deepslater.equilibrium.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, Equilibrium.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ItemTags.MUSIC_DISCS).add(ModItems.MUSIC_DISC_DIAMOND.get());
        this.tag(ItemTags.MUSIC_DISCS).add(ModItems.MUSIC_DISC_GOLD.get());
        this.tag(ItemTags.MUSIC_DISCS).add(ModItems.MUSIC_DISC_PLATINUM.get());
    }
}