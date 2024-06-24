package net.atilist.harderthanwolves.events.init;

import net.atilist.harderthanwolves.item.LazyItemTemplate;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.item.Item;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

public class ItemListener {

    public static LazyItemTemplate rawDiamondIngot;
    public static LazyItemTemplate diamondIngot;

    @Entrypoint.Namespace
    public static final Namespace NAMESPACE = Null.get();

    @EventListener
    public void registerItems(ItemRegistryEvent event) {
        diamondIngot = new LazyItemTemplate(Identifier.of(NAMESPACE, "diamond_ingot"));
        rawDiamondIngot = new LazyItemTemplate(Identifier.of(NAMESPACE, "raw_diamond_ingot"));
    }
}
