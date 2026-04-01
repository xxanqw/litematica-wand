package pp.ua.xxanqw.litematica_wand.item;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.world.item.CreativeModeTabs;
import pp.ua.xxanqw.litematica_wand.LitematicaWand;

public class ModItemsGroup {

    public static void registerItemGroups() {
        LitematicaWand.LOGGER.info("Registering item group entries for " + LitematicaWand.MOD_ID);

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(entries -> {
            entries.accept(ModItems.WAND);
        });
    }
}
