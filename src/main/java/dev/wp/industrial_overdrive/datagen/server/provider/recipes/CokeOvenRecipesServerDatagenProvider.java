package dev.wp.industrial_overdrive.datagen.server.provider.recipes;

import aztech.modern_industrialization.MIFluids;
import aztech.modern_industrialization.fluid.MIFluid;
import aztech.modern_industrialization.machines.init.MIMachineRecipeTypes;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;
import net.minecraft.tags.ItemTags;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public class CokeOvenRecipesServerDatagenProvider extends RecipesServerDatagenProvider {
    public CokeOvenRecipesServerDatagenProvider(GatherDataEvent event) {
        super(event);
    }

    @Override
    protected void buildRecipes(RecipeOutput output) {
        addMachineRecipe(
                "coke_oven", "charcoal_from_logs", MIMachineRecipeTypes.COKE_OVEN,
                8, 32*20/4,
                recipe -> recipe
                        .addItemInput(ItemTags.LOGS_THAT_BURN, 4)
                        .addItemOutput(Items.CHARCOAL, 5)
                        .addFluidOutput(MIFluids.CREOSOTE, 1000),
                output
        );
        addMachineRecipe(
                "coke_oven", "charcoal_from_logs_fast", MIMachineRecipeTypes.COKE_OVEN,
                8, 32*20/4/2,
                recipe -> recipe
                        .addItemInput(ItemTags.LOGS_THAT_BURN, 4)
                        .addFluidInput(MIFluids.NITROGEN, 250)
                        .addItemOutput(Items.CHARCOAL, 5)
                        .addFluidOutput(MIFluids.CREOSOTE, 1000),
                output
        );
    }
}
