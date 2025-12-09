package pp.ua.xxanqw.litematica_wand.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import pp.ua.xxanqw.litematica_wand.LitematicaWand;

public class ModItemsGroup {

    public static void registerItemGroups() {
        LitematicaWand.LOGGER.info("Registering item group entries for " + LitematicaWand.MOD_ID);

        // Add the Wand item to the vanilla "Tools" group
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.add(ModItems.WAND);
        });
    }
}
