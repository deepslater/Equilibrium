package deepslater.equilibrium.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class EquilibriumCommonConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> CREATIVE_TAB;
    public static final ForgeConfigSpec.ConfigValue<Boolean> TROPHY_ADDITIONS;
    public static final ForgeConfigSpec.ConfigValue<Boolean> LOOT_TWEAKS;

    static {
        BUILDER.push("Configs for Equilibrium");

        TROPHY_ADDITIONS = BUILDER.comment("Enable endgame trophy items to mark your achievements?")
                .define("EnableTrophyAdditions",true);
        CREATIVE_TAB = BUILDER.comment("Enable a Creative Tab?")
                .define("EnableCreativeTab", false);
        LOOT_TWEAKS = BUILDER.comment("Enable alterations to vanilla Chest loot? Disable if issues arise")
                .define("EnableLootTweaks",true);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }

}