package dev.wp.industrial_overdrive;

import aztech.modern_industrialization.machines.MachineBlock;
import aztech.modern_industrialization.machines.MachineBlockEntity;
import aztech.modern_industrialization.machines.MachineBlockEntityRenderer;
import aztech.modern_industrialization.machines.blockentities.multiblocks.LargeTankMultiblockBlockEntity;
import aztech.modern_industrialization.machines.multiblocks.MultiblockMachineBER;
import aztech.modern_industrialization.machines.multiblocks.MultiblockMachineBlockEntity;
import aztech.modern_industrialization.machines.multiblocks.MultiblockTankBER;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

@Mod(value = IO.ID, dist = Dist.CLIENT)
@EventBusSubscriber(value = Dist.CLIENT, modid = IO.ID, bus = EventBusSubscriber.Bus.MOD)
public final class IOClient {
    public IOClient(IEventBus bus) {

    }

    @SubscribeEvent
    private static void registerBlockEntityRenderers(FMLClientSetupEvent event) {
        for (DeferredHolder<Block, ? extends Block> blockDef : IOBlocks.Registry.BLOCKS.getEntries()) {
            if (blockDef.get() instanceof MachineBlock machine) {
                MachineBlockEntity blockEntity = machine.getBlockEntityInstance();
                BlockEntityType type = blockEntity.getType();

                BlockEntityRendererProvider provider = switch (blockEntity) {
                    case LargeTankMultiblockBlockEntity be -> MultiblockTankBER::new;
                    case MultiblockMachineBlockEntity be -> MultiblockMachineBER::new;
                    default -> MachineBlockEntityRenderer::new;
                };
                BlockEntityRenderers.register(type, provider);
            }
        }
    }
}
