package net.atilist.harderthanwolves.events.init;

import net.atilist.harderthanwolves.block.DepletedFirePile;
import net.atilist.harderthanwolves.block.FirePile;
import net.atilist.harderthanwolves.block.LazyBlockTemplate;
import net.atilist.harderthanwolves.block.LitFirePile;
import net.atilist.harderthanwolves.item.LazyItemTemplate;
import net.atilist.harderthanwolves.wrappers.ExampleBlockWithModel;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

public class BlockListener {

    public static Block exampleBlock;
    public static Block exampleBlock2;
    public static LazyBlockTemplate smoothObsidian;
    public static LazyBlockTemplate chiseledObsidian;
    public static LazyBlockTemplate firePile;
    public static LazyBlockTemplate litFirePile;
    public static LazyBlockTemplate depletedFirePile;

    @Entrypoint.Namespace
    public static final Namespace NAMESPACE = Null.get();

    @EventListener
    public void registerBlocks(BlockRegistryEvent event) {
        System.out.println(NAMESPACE);
        exampleBlock = new TemplateBlock(Identifier.of(NAMESPACE, "test"), Material.SOIL).setTranslationKey(NAMESPACE, "test");
        exampleBlock2 = new ExampleBlockWithModel(Identifier.of(NAMESPACE, "test2"), Material.SOIL).setTranslationKey(NAMESPACE, "test2");
        smoothObsidian = new LazyBlockTemplate(Identifier.of(NAMESPACE, "smooth_obsidian"), Material.STONE, 2.5F, Block.STONE_SOUND_GROUP);
        chiseledObsidian = new LazyBlockTemplate(Identifier.of(NAMESPACE, "chiseled_obsidian"), Material.STONE, 2.5F, Block.STONE_SOUND_GROUP);
        firePile = new FirePile(Identifier.of(NAMESPACE, "fire_pile"), Material.WOOD, 1.5F, Block.WOOD_SOUND_GROUP);
        litFirePile = new LitFirePile(Identifier.of(NAMESPACE, "lit_fire_pile"), Material.WOOD, 1.5F, Block.WOOD_SOUND_GROUP);
        depletedFirePile = new DepletedFirePile(Identifier.of(NAMESPACE, "depleted_fire_pile"), Material.WOOD, 1.5F, Block.WOOD_SOUND_GROUP);
    }
}
