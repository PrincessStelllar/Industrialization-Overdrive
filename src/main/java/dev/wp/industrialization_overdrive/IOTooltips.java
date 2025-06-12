package dev.wp.industrialization_overdrive;

import com.google.common.collect.Lists;
import dev.wp.industrialization_overdrive.item.Terminal;
import dev.wp.industrialization_overdrive.machines.blockentities.multiblock.PyrolyseOvenBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.swedz.tesseract.neoforge.tooltip.BiParser;
import net.swedz.tesseract.neoforge.tooltip.Parser;
import net.swedz.tesseract.neoforge.tooltip.TooltipAttachment;

import java.util.List;

import static aztech.modern_industrialization.MITooltips.*;
import static net.swedz.tesseract.neoforge.compat.mi.tooltip.MICompatibleTextLine.line;

public class IOTooltips {
    private static final BiParser<Boolean, Float> MAYBE_SPACED_PERCENTAGE_PARSER = (space, ratio) ->
            Component.literal("%d%s%%".formatted((int) (ratio * 100), space ? " " : "")).withStyle(NUMBER_TEXT);

    public static final Parser<Float> PERCENTAGE_PARSER = (ratio) -> MAYBE_SPACED_PERCENTAGE_PARSER.parse(false, ratio);
    public static final Parser<BlockPos> POS_PARSER = (pos) -> Component.literal("%d, %d, %d".formatted(pos.getX(), pos.getY(), pos.getZ())).withStyle(NUMBER_TEXT);

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

    public static final TooltipAttachment TERMINAL = TooltipAttachment.multilines(
            IOItems.TERMINAL,
            (flags, ctx, stack, item) -> {
                List<Component> tooltip = Lists.newArrayList();
                if (IOUtil.isAE2Loaded) {
                    GlobalPos linkPos = Terminal.getLinkPos(stack);
                    if (linkPos != null)
                        tooltip.add(line(IOText.TERMINAL_LINK_INFO).arg(linkPos.pos(), POS_PARSER));
                    else tooltip.add(line(IOText.TERMINAL_LINK_NOT_LINKED));
                }
                tooltip.add(line(IOText.TERMINAL_HELP_1).arg("sneak", KEYBIND_PARSER).arg("use", KEYBIND_PARSER));
                tooltip.add(line(IOUtil.isAE2Loaded ? IOText.TERMINAL_HELP_2_ALT : IOText.TERMINAL_HELP_2));
                return tooltip;
            }
    );

    public static void init() {
    }
}
