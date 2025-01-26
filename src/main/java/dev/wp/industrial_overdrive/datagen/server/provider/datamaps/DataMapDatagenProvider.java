package dev.wp.industrial_overdrive.datagen.server.provider.datamaps;

import aztech.modern_industrialization.MI;
import dev.wp.industrial_overdrive.IODataMaps;
import dev.wp.industrial_overdrive.datamap.PyrolyseOvenTier;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public final class DataMapDatagenProvider extends DataMapProvider {

    public DataMapDatagenProvider(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), event.getLookupProvider());
    }

    @Override
    protected void gather() {
        this.addPyrolyseOvenTier(MI.id("cupronickel_coil"), 8, 1.0F);
        this.addPyrolyseOvenTier(MI.id("kanthal_coil"), 32, 0.75F);
    }

    private void addPyrolyseOvenTier(ResourceLocation block, int batchSize, float euCostMultiplier) {
        this.builder(IODataMaps.PYROLYSE_OVEN_TIER).add(block, new PyrolyseOvenTier(batchSize, euCostMultiplier), false);
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
