package pp.ua.xxanqw.litematica_wand.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
// import net.minecraft.world.level.Level; // Removed in 1.21.4 signature

import java.util.List;

public class WandItem extends Item {
    public WandItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("item.litematica_wand.wand.tooltip"));
        
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
