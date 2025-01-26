package dev.wp.industrialization_overdrive;

import dev.wp.industrialization_overdrive.datamap.PyrolyseOvenTier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.datamaps.DataMapType;
import net.neoforged.neoforge.registries.datamaps.RegisterDataMapTypesEvent;

public final class IODataMaps {
    public static final DataMapType<Block, PyrolyseOvenTier> PYROLYSE_OVEN_TIER = DataMapType
            .builder(IO.id("pyrolyse_oven_tier"), Registries.BLOCK, PyrolyseOvenTier.CODEC)
            .synced(PyrolyseOvenTier.CODEC, true)
            .build();

    public static void init(RegisterDataMapTypesEvent event) {
        event.register(PYROLYSE_OVEN_TIER);
    }
}
