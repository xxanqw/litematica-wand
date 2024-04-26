package pp.ua.xxanqw.litematica_wand.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import pp.ua.xxanqw.litematica_wand.LitematicaWand;

import java.util.List;


public class ModItems {
    public static final Item WAND = registerItem("wand", new WandItem(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));
    public static final Item WAND_3D = registerItem("wand_3d", new Wand3DItem(new FabricItemSettings().maxCount(1).rarity(Rarity.EPIC)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(LitematicaWand.MOD_ID, name), item);
    }

    public static void registerModItems() {
        LitematicaWand.LOGGER.info("Registering items of " + LitematicaWand.MOD_ID);
    }
}
