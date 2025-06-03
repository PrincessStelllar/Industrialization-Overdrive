package dev.wp.industrialization_overdrive;

import aztech.modern_industrialization.MI;
import aztech.modern_industrialization.compat.rei.machines.ReiMachineRecipes;
import aztech.modern_industrialization.machines.guicomponents.ProgressBar;
import aztech.modern_industrialization.machines.init.MultiblockMachines;
import aztech.modern_industrialization.machines.recipe.MachineRecipeType;
import com.google.common.collect.Maps;
import dev.wp.industrialization_overdrive.machines.blockentities.multiblock.MultiProcessingArrayBlockEntity;
import dev.wp.industrialization_overdrive.machines.blockentities.multiblock.PyrolyseOvenBlockEntity;
import dev.wp.industrialization_overdrive.machines.recipe.PyrolyseOvenRecipeType;
import net.minecraft.resources.ResourceLocation;
import net.swedz.tesseract.neoforge.compat.mi.hook.context.listener.MachineRecipeTypesMIHookContext;
import net.swedz.tesseract.neoforge.compat.mi.hook.context.listener.MultiblockMachinesMIHookContext;

import java.util.Map;
import java.util.function.Function;

import static aztech.modern_industrialization.machines.models.MachineCasings.BRONZE_PLATED_BRICKS;
import static aztech.modern_industrialization.machines.models.MachineCasings.SOLID_TITANIUM;

public final class IOMachines {
//    public static void blastFurnaceTiers(BlastFurnaceTiersMIHookContext hook) {
//    }
//
//    public static final class Casings {
//    }
//
//    public static void casings(MachineCasingsMIHookContext hook) {
//    }

    public static final class RecipeTypes {
        public static MachineRecipeType PYROLYSE_OVEN;

        private static final Map<MachineRecipeType, String> RECIPE_TYPE_NAMES = Maps.newHashMap();

        public static Map<MachineRecipeType, String> getNames() {
            return RECIPE_TYPE_NAMES;
        }

        private static MachineRecipeType create(MachineRecipeTypesMIHookContext hook, String englishName, String id, Function<ResourceLocation, MachineRecipeType> creator) {
            MachineRecipeType recipeType = hook.create(id, creator);
            RECIPE_TYPE_NAMES.put(recipeType, englishName);
            return recipeType;
        }

        private static MachineRecipeType create(MachineRecipeTypesMIHookContext hook, String englishName, String id) {
            return create(hook, englishName, id, MachineRecipeType::new);
        }
    }

    public static void recipeTypes(MachineRecipeTypesMIHookContext hook) {
        RecipeTypes.PYROLYSE_OVEN = RecipeTypes.create(hook, "Pyrolyse Oven", "pyrolyse_oven", PyrolyseOvenRecipeType::new).withFluidInputs().withFluidOutputs().withItemInputs().withItemOutputs();
    }

    public static void multiblocks(MultiblockMachinesMIHookContext hook) {
        hook.register(
                "Multi Processing Array", "multi_processing_array", "multi_processing_array",
                SOLID_TITANIUM, true, false, false,
                MultiProcessingArrayBlockEntity::new,
                (__) -> MultiProcessingArrayBlockEntity.registerReiShapes()
        );
        hook.register(
                "Pyrolyse Oven", "pyrolyse_oven", "pyrolyse_oven",
                BRONZE_PLATED_BRICKS, true, false, false,
                PyrolyseOvenBlockEntity::new
        );
        ReiMachineRecipes.registerWorkstation(MI.id("coke_oven"), IO.id("pyrolyse_oven"));

        registerPyrolyseOvenCategory();
    }

    private static void registerPyrolyseOvenCategory() {
        new MultiblockMachines.Rei("Pyrolyse Oven", IO.id("pyrolyse_oven"),
                IOMachines.RecipeTypes.PYROLYSE_OVEN,
                new ProgressBar.Parameters(77, 33, "arrow"))
                .items(inputs -> inputs.addSlot(56, 35), outputs -> outputs.addSlot(102, 35))
                .fluids(fluids -> fluids.addSlot(36, 35), outputs -> outputs.addSlot(122, 35))
                .workstations(IO.id("pyrolyse_oven"))
                .register();
    }

//    public static void singleBlockCrafting(SingleBlockCraftingMachinesMIHookContext hook) {
//    }
//
//    public static void singleBlockSpecial(SingleBlockSpecialMachinesMIHookContext hook) {
//    }


}
