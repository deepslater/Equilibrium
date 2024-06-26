package deepslater.equilibrium.block;

import deepslater.equilibrium.Equilibrium;
import deepslater.equilibrium.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class ModBlocks {
    private static final ToIntFunction<BlockState> LIGHT_LEVEL_10 = value -> 10;

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Equilibrium.MOD_ID);

    // Helper method that registers an Item and associates it with a block we pass in
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    // Helper method that registers the block, then the blockItem, then returns the block
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    // can call .noLootTable() to override datagen
    public static final RegistryObject<Block> ECHOES_BLOCK = registerBlock("echoes_block",
            () -> new EchoesBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK)
                    .lightLevel(LIGHT_LEVEL_10)));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}