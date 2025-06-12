package dev.wp.industrialization_overdrive.datagen.server.provider.recipes;

import aztech.modern_industrialization.MIItem;
import dev.wp.industrialization_overdrive.IO;
import dev.wp.industrialization_overdrive.IOItems;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.swedz.tesseract.neoforge.compat.mi.recipe.MIMachineRecipeBuilder;
import net.swedz.tesseract.neoforge.compat.vanilla.recipe.ShapedRecipeBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public final class CommonRecipesServerDatagenProvider extends RecipesServerDatagenProvider {
    public CommonRecipesServerDatagenProvider(GatherDataEvent event) {
        super(event);
    }

    private static void addBasicCraftingRecipes(String path, String name, boolean assembler, ItemLike result, int resultCount, Consumer<ShapedRecipeBuilder> crafting, RecipeOutput output) {
        ShapedRecipeBuilder shapedRecipeBuilder = new ShapedRecipeBuilder();
        crafting.accept(shapedRecipeBuilder);
        shapedRecipeBuilder.output(result, resultCount);
        shapedRecipeBuilder.offerTo(output, IO.id(path + "/craft/" + name));

        if (assembler) {
            MIMachineRecipeBuilder.fromShapedToAssembler(shapedRecipeBuilder).offerTo(output, IO.id(path + "/craft/" + name + "/assembler"));
        }
    }


    @Override
    protected void buildRecipes(@NotNull RecipeOutput output) {
        addBasicCraftingRecipes(
                "shaped", "terminal", true,
                IOItems.TERMINAL.get(), 1,
                (r) -> r
                        .pattern("DGD")
                        .pattern("TET")
                        .define('D', MIItem.DIODE)
                        .define('T', MIItem.TRANSISTOR)
                        .define('G', Tags.Items.GLASS_PANES)
                        .define('E', MIItem.ELECTRONIC_CIRCUIT),
                output
        );
    }
}
