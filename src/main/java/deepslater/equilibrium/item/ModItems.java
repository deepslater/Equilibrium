package deepslater.equilibrium.item;

import deepslater.equilibrium.Equilibrium;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Equilibrium.MOD_ID);

    public static final RegistryObject<Item> MUSIC_DISC_DIAMOND = ITEMS.register("music_disc_diamond",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MUSIC_DISC_GOLD = ITEMS.register("music_disc_gold",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MUSIC_DISC_PLATINUM = ITEMS.register("music_disc_platinum",
            () -> new Item(new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}