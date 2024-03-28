package deepslater.equilibrium.sound;

import deepslater.equilibrium.Equilibrium;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Equilibrium.MOD_ID);

    public static final RegistryObject<SoundEvent> SNEAKY_SNITCH = registerSoundEvents("sneaky_snitch");
    public static final RegistryObject<SoundEvent> MONKEYS_SPINNING_MONKEYS = registerSoundEvents("monkeys_spinning_monkeys");
    public static final RegistryObject<SoundEvent> WHO_LIKES_TO_PARTY = registerSoundEvents("who_likes_to_party");

    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name,
                () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Equilibrium.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) { SOUND_EVENTS.register(eventBus); }
}