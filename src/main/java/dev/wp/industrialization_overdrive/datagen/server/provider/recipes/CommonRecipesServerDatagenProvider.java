package dev.wp.industrialization_overdrive.datagen.server.provider.recipes;

import dev.wp.industrialization_overdrive.IO;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.swedz.tesseract.neoforge.compat.mi.recipe.MIMachineRecipeBuilder;
import net.swedz.tesseract.neoforge.compat.vanilla.recipe.ShapedRecipeBuilder;

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
    protected void buildRecipes(RecipeOutput output) {
    }
}
