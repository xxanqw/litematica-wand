package pp.ua.xxanqw.litematica_wand.item;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import pp.ua.xxanqw.litematica_wand.Constants;

import java.util.function.Function;

public class ModItems {
    public static final Item WAND = registerItem("wand", WandItem::new, new Item.Properties().stacksTo(1).rarity(Rarity.EPIC));

    private static Item registerItem(String path, Function<Item.Properties, Item> factory, Item.Properties properties) {
        ResourceKey<Item> registryKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Constants.MOD_ID, path));
        return Registry.register(BuiltInRegistries.ITEM, registryKey, factory.apply(properties));
    }

    public static void registerModItems() {
        Constants.LOGGER.info("Registering items of {}", Constants.MOD_ID);
    }
}
