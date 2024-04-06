/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package pp.ua.xxanqw.litematicawand.init;

import pp.ua.xxanqw.litematicawand.item.WandItem;
import pp.ua.xxanqw.litematicawand.item.Wand3dItem;
import pp.ua.xxanqw.litematicawand.LitematicaWandMod;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.Item;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;

public class LitematicaWandModItems {
	public static Item WAND;
	public static Item WAND_3D;

	public static void load() {
		WAND = register("wand", new WandItem());
		WAND_3D = register("wand_3d", new Wand3dItem());
	}

	public static void clientLoad() {
	}

	private static Item register(String registryName, Item item) {
		return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(LitematicaWandMod.MODID, registryName), item);
	}

	private static void registerBlockingProperty(Item item) {
		ItemProperties.register(item, new ResourceLocation("blocking"), (ClampedItemPropertyFunction) ItemProperties.getProperty(Items.SHIELD, new ResourceLocation("blocking")));
	}
}
