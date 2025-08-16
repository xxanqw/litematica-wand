package pp.ua.xxanqw.litematica_wand.item;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import pp.ua.xxanqw.litematica_wand.LitematicaWand;
import java.util.function.Function;


public class ModItems {
    public static final Item WAND = registerItem("wand", WandItem::new, new Item.Settings().maxCount(1).rarity(Rarity.EPIC));

    private static Item registerItem(String path, Function<Item.Settings, Item> factory, Item.Settings settings) {
        final RegistryKey<Item> registryKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of("litematica_wand", path));
        return Items.register(registryKey, factory, settings);
    }

    public static void registerModItems() {
        LitematicaWand.LOGGER.info("Registering items of " + LitematicaWand.MOD_ID);
    }
}
