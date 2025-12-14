package pp.ua.xxanqw.litematica_wand.item;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import pp.ua.xxanqw.litematica_wand.Constants;
import java.util.function.Function;

public class ModItems {
    public static final Item WAND = registerItem("wand", WandItem::new, new Item.Settings().maxCount(1).rarity(Rarity.EPIC));

    private static Item registerItem(String path, Function<Item.Settings, Item> factory, Item.Settings settings) {
        RegistryKey<Item> registryKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Constants.MOD_ID, path));
        return Items.register(registryKey, factory, settings);
    }

    public static void registerModItems() {
        Constants.LOGGER.info("Registering items of {}", Constants.MOD_ID);
    }
}
