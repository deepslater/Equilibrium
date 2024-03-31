package deepslater.equilibrium.datagen.loot;

import com.google.common.collect.Sets;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;

import java.util.Set;

public class ModLootTables {
    public ModLootTables() {
        super();
    }
    private static final Set<ResourceLocation> MOD_LOCATIONS = Sets.newHashSet();
    public static final ResourceLocation SOUL_WORKSHOP;

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
    }
}