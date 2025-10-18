package pp.ua.xxanqw.litematica_wand;

import net.fabricmc.api.ModInitializer;
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
    }
}
