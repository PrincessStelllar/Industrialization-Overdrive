package dev.wp.industrialization_overdrive;

import dev.wp.industrialization_overdrive.machines.blockentities.multiblock.PyrolyseOvenBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.swedz.tesseract.neoforge.tooltip.BiParser;
import net.swedz.tesseract.neoforge.tooltip.Parser;
import net.swedz.tesseract.neoforge.tooltip.TooltipAttachment;

import static aztech.modern_industrialization.MITooltips.NUMBER_TEXT;
import static net.swedz.tesseract.neoforge.compat.mi.tooltip.MICompatibleTextLine.line;

public class IOTooltips {
    private static final BiParser<Boolean, Float> MAYBE_SPACED_PERCENTAGE_PARSER = (space, ratio) ->
            Component.literal("%d%s%%".formatted((int) (ratio * 100), space ? " " : "")).withStyle(NUMBER_TEXT);
    public static final Parser<Float> PERCENTAGE_PARSER = (ratio) -> MAYBE_SPACED_PERCENTAGE_PARSER.parse(false, ratio);
    public static final TooltipAttachment COILS_PYRO = TooltipAttachment.singleLine(
            (stack, item) ->
                    item instanceof BlockItem blockItem &&
                            PyrolyseOvenBlockEntity.getTiersByCoil().containsKey(BuiltInRegistries.BLOCK.getKey(blockItem.getBlock())),
            (flags, ctx, stack, item) -> {
                PyrolyseOvenBlockEntity.Tier tier = PyrolyseOvenBlockEntity.getTiersByCoil()
                        .get(BuiltInRegistries.BLOCK.getKey(((BlockItem) stack.getItem()).getBlock()));
                int batchSize = tier.batchSize();
                float euCostMultiplier = tier.euCostMultiplier();
                return line(IOText.COILS_PYRO_TIER).arg(batchSize).arg(euCostMultiplier, PERCENTAGE_PARSER);
            }
    );

    public static void init() {
    }
}
