package pp.ua.xxanqw.litematica_wand;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pp.ua.xxanqw.litematica_wand.item.ModItems;
import pp.ua.xxanqw.litematica_wand.item.ModItemsGroup;

public class LitematicaWand implements ModInitializer {
    public static final String MOD_ID = "litematica_wand";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing Litematica Wand");
        ModItems.registerModItems();
        ModItemsGroup.registerItemGroups();
        
        // Register tooltip for the wand item
        ItemTooltipCallback.EVENT.register((itemStack, tooltipContext, tooltipType, list) -> {
            if (itemStack.isOf(ModItems.WAND)) {
                list.add(Text.translatable("item.litematica_wand.wand.tooltip").formatted(Formatting.BOLD));
            }
        });
    }
}
