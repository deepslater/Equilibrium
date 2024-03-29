package deepslater.equilibrium.item;

import deepslater.equilibrium.Equilibrium;
import deepslater.equilibrium.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Equilibrium.MOD_ID);

    public static final RegistryObject<CreativeModeTab> EQUILIBRIUM_TAB =
            CREATIVE_MODE_TABS.register("equilibrium_tab",
                    () -> CreativeModeTab.builder()
                            .icon(() -> new ItemStack(ModItems.MUSIC_DISC_DIAMOND.get()))
                            .title(Component.translatable("creativetab.equilibrium"))
                            .displayItems((pParameters, pOutput) -> {
                                for (RegistryObject<Item> item : ModItems.ITEMS.getEntries()) {
                                    pOutput.accept(item.get());
                                }
                                for (RegistryObject<Block> block :ModBlocks.BLOCKS.getEntries()) {
                                        pOutput.accept(block.get());
                                    }
                            })
                            .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}