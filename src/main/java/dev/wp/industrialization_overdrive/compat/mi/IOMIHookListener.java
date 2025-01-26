package dev.wp.industrialization_overdrive.compat.mi;

import dev.wp.industrialization_overdrive.IOMachines;
import dev.wp.industrialization_overdrive.IOTooltips;
import dev.wp.industrialization_overdrive.machines.guicomponents.multiprocessingarraymachineslot.MultiProcessingArrayMachineSlot;
import dev.wp.industrialization_overdrive.machines.guicomponents.multiprocessingarraymachineslot.MultiProcessingArrayMachineSlotClient;
import net.swedz.tesseract.neoforge.compat.mi.hook.MIHookEntrypoint;
import net.swedz.tesseract.neoforge.compat.mi.hook.MIHookListener;
import net.swedz.tesseract.neoforge.compat.mi.hook.context.listener.ClientGuiComponentsMIHookContext;
import net.swedz.tesseract.neoforge.compat.mi.hook.context.listener.MultiblockMachinesMIHookContext;

@MIHookEntrypoint
public class IOMIHookListener implements MIHookListener {
    @Override
    public void clientGuiComponents(ClientGuiComponentsMIHookContext hook) {
        hook.register(MultiProcessingArrayMachineSlot.ID, MultiProcessingArrayMachineSlotClient::new);
    }

//    @Override
//    public void machineCasings(MachineCasingsMIHookContext hook) {
//        IOMachines.casings(hook);
//    }

//    @Override
//    public void machineRecipeTypes(MachineRecipeTypesMIHookContext hook) {
//        IOMachines.recipeTypes(hook);
//    }

    @Override
    public void multiblockMachines(MultiblockMachinesMIHookContext hook) {
        IOMachines.multiblocks(hook);
    }

//    @Override
//    public void singleBlockCraftingMachines(SingleBlockCraftingMachinesMIHookContext hook) {
//        IOMachines.singleBlockCrafting(hook);
//    }

//    @Override
//    public void singleBlockSpecialMachines(SingleBlockSpecialMachinesMIHookContext hook) {
//        IOMachines.singleBlockSpecial(hook);
//    }

    @Override
    public void tooltips() {
        IOTooltips.init();
    }

//    @Override
//    public void viewerSetup(ViewerSetupMIHookContext hook) {
//    }
}
