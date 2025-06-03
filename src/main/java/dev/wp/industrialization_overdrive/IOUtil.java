package dev.wp.industrialization_overdrive;

import net.neoforged.fml.ModList;

public class IOUtil {
    public final static boolean isEILoaded = isModLoaded("extended_industrialization");

    public static boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }
}
