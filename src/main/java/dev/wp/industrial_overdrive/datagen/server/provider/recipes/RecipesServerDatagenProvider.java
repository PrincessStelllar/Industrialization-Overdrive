package dev.wp.industrial_overdrive.datagen.server.provider.recipes;

import aztech.modern_industrialization.machines.recipe.MachineRecipeBuilder;
import aztech.modern_industrialization.machines.recipe.MachineRecipeType;
import aztech.modern_industrialization.materials.Material;
import aztech.modern_industrialization.materials.part.PartTemplate;
import dev.wp.industrial_overdrive.IO;
import dev.wp.industrial_overdrive.datagen.api.RecipeHelper;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.function.Consumer;

import static aztech.modern_industrialization.materials.property.MaterialProperty.HARDNESS;

public abstract class RecipesServerDatagenProvider extends RecipeProvider {
    protected RecipesServerDatagenProvider(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), event.getLookupProvider());
    }

    protected static Ingredient ingredient(String maybeTag) {
        return RecipeHelper.ingredient(maybeTag);
    }

    protected static boolean hasPart(Material material, PartTemplate part) {
        return material.getParts().containsKey(part.key());
    }

    protected static void addMachineRecipe(String path, String name, MachineRecipeType recipeType, int eu, int duration, Consumer<MachineRecipeBuilder> recipeBuilder, RecipeOutput output) {
        MachineRecipeBuilder recipe = new MachineRecipeBuilder(recipeType, eu, duration);
        recipeBuilder.accept(recipe);
        output.accept(IO.id(path + "/" + name), RecipeHelper.getActualRecipe(recipe), null);
    }

    protected static void addMaterialMachineRecipe(Material material, String name, MachineRecipeType recipeType, int eu, int duration, Consumer<MachineRecipeBuilder> recipeBuilder, RecipeOutput output) {
        addMachineRecipe("materials/%s/%s".formatted(material.name, recipeType.getPath()), name, recipeType, eu, duration, recipeBuilder, output);
    }

    protected static void addMaterialMachineRecipe(Material material, String name, MachineRecipeType recipeType, int eu, Consumer<MachineRecipeBuilder> recipeBuilder, RecipeOutput output) {
        addMaterialMachineRecipe(material, name, recipeType, eu, (int) (200 * material.get(HARDNESS).timeFactor), recipeBuilder, output);
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
