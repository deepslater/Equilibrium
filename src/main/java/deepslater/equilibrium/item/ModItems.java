package deepslater.equilibrium.item;

import deepslater.equilibrium.Equilibrium;
import deepslater.equilibrium.sound.ModSounds;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.RecordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    // DeferredRegister is the
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Equilibrium.MOD_ID);

    // RecordItem has the following parameters: comparator value, sound output
    public static final RegistryObject<Item> MUSIC_DISC_DIAMOND = ITEMS.register("music_disc_diamond",
            () -> new RecordItem(15, ModSounds.WHO_LIKES_TO_PARTY,
                    new Item.Properties().rarity(Rarity.EPIC).stacksTo(1),5140));
    public static final RegistryObject<Item> MUSIC_DISC_GOLD = ITEMS.register("music_disc_gold",
            () -> new RecordItem(15, ModSounds.SNEAKY_SNITCH,
                    new Item.Properties().rarity(Rarity.RARE).stacksTo(1),2720));
    public static final RegistryObject<Item> MUSIC_DISC_PLATINUM = ITEMS.register("music_disc_platinum",
            () -> new RecordItem(15, ModSounds.MONKEYS_SPINNING_MONKEYS,
                    new Item.Properties().rarity(Rarity.RARE).stacksTo(1),2500));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}