package pp.ua.xxanqw.litematica_wand.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import pp.ua.xxanqw.litematica_wand.LitematicaWand;

public class ModItemsGroup {
    public static final ItemGroup WAND_GROUP = FabricItemGroup.builder().displayName(Text.translatable("itemgroup.wand"))
                    .icon(() -> new ItemStack(ModItems.WAND)).entries((displayContext, entries) -> {
                        entries.add(ModItems.WAND);
                        entries.add(ModItems.WAND_3D);
                    }).build();

    public static void registerItemGroups() {
        Registry.register(Registries.ITEM_GROUP, new Identifier(LitematicaWand.MOD_ID, "wand"), WAND_GROUP);
        LitematicaWand.LOGGER.info("Registering item groups of " + LitematicaWand.MOD_ID);
    }
}
