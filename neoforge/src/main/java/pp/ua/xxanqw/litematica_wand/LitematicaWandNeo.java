package pp.ua.xxanqw.litematica_wand;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import pp.ua.xxanqw.litematica_wand.item.WandItem;

@Mod("litematica_wand")
public class LitematicaWandNeo {
    public static final String MOD_ID = "litematica_wand";

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, MOD_ID);

    public static final DeferredHolder<Item, WandItem> WAND = ITEMS.register("wand",
    () -> new WandItem(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1).setId(
         ResourceKey.create(Registries.ITEM,
         Identifier.fromNamespaceAndPath(MOD_ID, "wand"))
    )));

    public LitematicaWandNeo(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
        modEventBus.addListener(this::addCreativeTab);
    }

    @SubscribeEvent
    public void addCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(WAND.get());
        }
    }
}