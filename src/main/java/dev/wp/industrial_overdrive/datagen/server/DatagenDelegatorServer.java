package dev.wp.industrial_overdrive.datagen.server;

import dev.wp.industrial_overdrive.datagen.server.provider.datamaps.DataMapDatagenProvider;
import dev.wp.industrial_overdrive.datagen.server.provider.loottable.BlockLootTableDatagenProvider;
import dev.wp.industrial_overdrive.datagen.server.provider.recipes.CokeOvenRecipesServerDatagenProvider;
import dev.wp.industrial_overdrive.datagen.server.provider.recipes.CommonRecipesServerDatagenProvider;
import dev.wp.industrial_overdrive.datagen.server.provider.recipes.MachineItemRecipesServerDatagenProvider;
import dev.wp.industrial_overdrive.datagen.server.provider.tags.BlockTagDatagenProvider;
import dev.wp.industrial_overdrive.datagen.server.provider.tags.ItemTagDatagenProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class DatagenDelegatorServer {
    public static void configure(GatherDataEvent event) {
        add(event, DataMapDatagenProvider::new);

        addLootTable(event, BlockLootTableDatagenProvider::new);

        add(event, CokeOvenRecipesServerDatagenProvider::new);
        add(event, CommonRecipesServerDatagenProvider::new);
        add(event, MachineItemRecipesServerDatagenProvider::new);

        add(event, BlockTagDatagenProvider::new);
        add(event, ItemTagDatagenProvider::new);
    }

    private static void add(GatherDataEvent event, Function<GatherDataEvent, DataProvider> providerCreator) {
        event.getGenerator().addProvider(event.includeServer(), providerCreator.apply(event));
    }

    private static void addLootTable(GatherDataEvent event, Function<HolderLookup.Provider, LootTableSubProvider> providerCreator) {
        event.getGenerator().addProvider(
                event.includeServer(),
                new LootTableProvider(
                        event.getGenerator().getPackOutput(),
                        Set.of(),
                        List.of(new LootTableProvider.SubProviderEntry(providerCreator, LootContextParamSets.BLOCK)),
                        event.getLookupProvider()
                )
        );
    }
}
