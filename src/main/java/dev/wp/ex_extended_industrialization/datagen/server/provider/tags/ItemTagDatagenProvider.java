package dev.wp.ex_extended_industrialization.datagen.server.provider.tags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import dev.wp.ex_extended_industrialization.ExEI;
import dev.wp.ex_extended_industrialization.ExEIItems;
import net.swedz.tesseract.neoforge.registry.holder.ItemHolder;

import java.util.Comparator;
import java.util.concurrent.CompletableFuture;

public final class ItemTagDatagenProvider extends ItemTagsProvider {
	public ItemTagDatagenProvider(GatherDataEvent event) {
		super(event.getGenerator().getPackOutput(), event.getLookupProvider(), CompletableFuture.completedFuture(TagLookup.empty()), ExEI.ID, event.getExistingFileHelper());
	}
	
	@Override
	protected void addTags(HolderLookup.Provider provider) {
		for(ItemHolder<?> item : ExEIItems.values().stream().sorted(Comparator.comparing((item) -> item.identifier().id())).toList()) {
			for(TagKey<Item> tag : item.tags()) {
				this.tag(tag).add(item.asItem());
			}
		}
	}
	
	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}
}
