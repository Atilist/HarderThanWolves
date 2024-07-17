package net.atilist.harderthanwolves.events.init;

import net.atilist.harderthanwolves.item.LazyItemTemplate;
import net.atilist.harderthanwolves.item.StoneChiselItem;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

public class ItemListener {

    public static StoneChiselItem stoneChisel;

    public static LazyItemTemplate stoneBrick;

    public static LazyItemTemplate ironChunks;
    public static LazyItemTemplate goldChunks;

    public static LazyItemTemplate mysticalStick;
    public static LazyItemTemplate mysticalHemp;
    public static LazyItemTemplate mysticalHempFibers;
    public static LazyItemTemplate monsterCloth;
    public static LazyItemTemplate mysticalRock;
    public static LazyItemTemplate chargedMysticalRock;

    public static LazyItemTemplate crystallizedLapis;
    public static LazyItemTemplate rawLapisIngot;
    public static LazyItemTemplate lapisIngot;

    public static LazyItemTemplate rawDiamondIngot;
    public static LazyItemTemplate diamondIngot;

    @Entrypoint.Namespace
    public static final Namespace NAMESPACE = Null.get();

    @EventListener
    public void registerItems(ItemRegistryEvent event) {
        stoneChisel = new StoneChiselItem(Identifier.of(NAMESPACE, "stone_chisel"));

        stoneBrick = new LazyItemTemplate(Identifier.of(NAMESPACE, "stone_brick"));

        ironChunks = new LazyItemTemplate(Identifier.of(NAMESPACE, "iron_chunks"));
        goldChunks = new LazyItemTemplate(Identifier.of(NAMESPACE, "gold_chunks"));

        mysticalStick = new LazyItemTemplate(Identifier.of(NAMESPACE, "mystical_stick"));
        mysticalHemp = new LazyItemTemplate(Identifier.of(NAMESPACE, "mystical_hemp"));
        mysticalHempFibers = new LazyItemTemplate(Identifier.of(NAMESPACE, "mystical_hemp_fibers"));
        monsterCloth = new LazyItemTemplate(Identifier.of(NAMESPACE, "monster_cloth"));
        mysticalRock = new LazyItemTemplate(Identifier.of(NAMESPACE, "mystical_rock"));
        chargedMysticalRock = new LazyItemTemplate(Identifier.of(NAMESPACE, "charged_mystical_rock"));

        crystallizedLapis = new LazyItemTemplate(Identifier.of(NAMESPACE, "crystallized_lapis"));
        rawLapisIngot = new LazyItemTemplate(Identifier.of(NAMESPACE, "raw_lapis_ingot"));
        lapisIngot = new LazyItemTemplate(Identifier.of(NAMESPACE, "lapis_ingot"));

        rawDiamondIngot = new LazyItemTemplate(Identifier.of(NAMESPACE, "raw_diamond_ingot"));
        diamondIngot = new LazyItemTemplate(Identifier.of(NAMESPACE, "diamond_ingot"));
    }
}
