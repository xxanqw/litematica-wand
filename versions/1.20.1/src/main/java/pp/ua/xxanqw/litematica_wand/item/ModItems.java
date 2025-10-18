package pp.ua.xxanqw.litematica_wand.item;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import pp.ua.xxanqw.litematica_wand.LitematicaWand;

public class ModItems {
    public static final Item WAND = registerItem("wand", new WandItem(new Item.Settings().maxCount(1).rarity(Rarity.EPIC)));

    private static Item registerItem(String path, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(LitematicaWand.MOD_ID, path), item);
    }

    public static void registerModItems() {
        LitematicaWand.LOGGER.info("Registering items of {}", LitematicaWand.MOD_ID);
    }
}
