package deepslater.equilibrium.datagen.loot;

import com.google.common.collect.Sets;
import net.minecraft.resources.ResourceLocation;

import java.util.Set;

public class ModLootTables {
    public ModLootTables() {
        super();
    }
    private static final Set<ResourceLocation> MOD_LOCATIONS = Sets.newHashSet();
    public static final ResourceLocation SOUL_WORKSHOP;
    public static final ResourceLocation SEMIPRECIOUS_ARMOR;
    public static final ResourceLocation PRECIOUS_UNDERGROUND_TOOL;
    public static final ResourceLocation MINECART_WITH_INGREDIENT;

    private static ResourceLocation register(String pId) {
        return register(new ResourceLocation(pId));
    }

    private static ResourceLocation register(ResourceLocation pId) {
        if (MOD_LOCATIONS.add(pId)) {
            return pId;
        } else {
            throw new IllegalArgumentException("" + pId + " is already a registered mod loot table");
        }
    }

    static {
        SOUL_WORKSHOP = register("equilibrium:chests/soul_workshop");
        SEMIPRECIOUS_ARMOR = register("gameplay/semiprecious_armor");
        PRECIOUS_UNDERGROUND_TOOL = register("gameplay/precious_underground_tool");
        MINECART_WITH_INGREDIENT = register("gameplay/minecart_with");
    }
}