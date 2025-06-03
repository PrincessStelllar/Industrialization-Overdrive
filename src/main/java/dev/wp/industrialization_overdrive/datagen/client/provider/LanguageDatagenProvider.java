package dev.wp.industrialization_overdrive.datagen.client.provider;

import aztech.modern_industrialization.MI;
import dev.wp.industrialization_overdrive.IO;
import dev.wp.industrialization_overdrive.IOItems;
import dev.wp.industrialization_overdrive.IOText;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.swedz.tesseract.neoforge.datagen.mi.MIDatagenHooks;
import net.swedz.tesseract.neoforge.registry.holder.ItemHolder;

public final class LanguageDatagenProvider extends LanguageProvider {
    public LanguageDatagenProvider(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), IO.ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        for (IOText text : IOText.values()) {
            this.add(text.getTranslationKey(), text.englishText());
        }

        for (ItemHolder item : IOItems.values()) {
            this.add(item.asItem(), item.identifier().englishName());
        }

        MIDatagenHooks.Client.withLanguageHook(this, IO.ID);

        this.add("itemGroup.%s.%s".formatted(IO.ID, IO.ID), IO.NAME);

        this.add("pyro_tier.%s.%s.%s".formatted(IO.ID, MI.ID, "cupronickel_coil"), "Cupronickel");
        this.add("pyro_tier.%s.%s.%s".formatted(IO.ID, MI.ID, "kanthal_coil"), "Kanthal");
        this.add("rei_categories.%s.%s".formatted(IO.ID, "pyrolyse_oven"), "Pyrolyse Oven");
    }
}
