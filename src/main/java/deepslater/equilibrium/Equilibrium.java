package deepslater.equilibrium;

import com.mojang.logging.LogUtils;
import deepslater.equilibrium.block.ModBlocks;
import deepslater.equilibrium.config.EquilibriumCommonConfigs;
import deepslater.equilibrium.item.ModCreativeModeTabs;
import deepslater.equilibrium.item.ModItems;
import deepslater.equilibrium.sound.ModSounds;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.EventBus;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Equilibrium.MOD_ID)
public class Equilibrium {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "equilibrium";
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final boolean CREATIVE_MODE_TAB = true;

    public Equilibrium() {
        // Holds a list of event listeners, in our case registry events
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModLoadingContext.get().registerConfig(
                ModConfig.Type.COMMON,
                EquilibriumCommonConfigs.SPEC,
                "equilibrium-common.toml"
        );

        // Each class with registry events must call a register method by which we supply the eventBus
        ModSounds.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        if (CREATIVE_MODE_TAB) {
            ModCreativeModeTabs.register(modEventBus);
        }

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {}

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (!CREATIVE_MODE_TAB) {
            if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
                event.accept(ModItems.MUSIC_DISC_GOLD);
                event.accept(ModItems.MUSIC_DISC_PLATINUM);
                event.accept(ModItems.MUSIC_DISC_DIAMOND);
            }
            if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
                event.accept(ModBlocks.ECHOES_BLOCK);
            }
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {}

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}