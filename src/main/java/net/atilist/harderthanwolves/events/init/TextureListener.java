package net.atilist.harderthanwolves.events.init;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

public class TextureListener {

    @Entrypoint.Namespace
    public static final Namespace NAMESPACE = Null.get();

    @EventListener
    public void registerTextures(TextureRegisterEvent event) {
        ItemListener.stoneChisel.setTexture(Identifier.of(NAMESPACE, "item/stoneChisel"));

        ItemListener.stoneBrick.setTexture(Identifier.of(NAMESPACE, "item/stoneBrick"));

        ItemListener.ironChunks.setTexture(Identifier.of(NAMESPACE, "item/ironChunks"));
        ItemListener.goldChunks.setTexture(Identifier.of(NAMESPACE, "item/goldChunks"));

        ItemListener.mysticalStick.setTexture(Identifier.of(NAMESPACE, "item/mysticalStick"));
        ItemListener.mysticalHemp.setTexture(Identifier.of(NAMESPACE, "item/mysticalHemp"));
        ItemListener.mysticalHempFibers.setTexture(Identifier.of(NAMESPACE, "item/mysticalHempFibers"));
        ItemListener.monsterCloth.setTexture(Identifier.of(NAMESPACE, "item/monsterCloth"));
        ItemListener.mysticalRock.setTexture(Identifier.of(NAMESPACE, "item/mysticalRock"));
        ItemListener.chargedMysticalRock.setTexture(Identifier.of(NAMESPACE, "item/chargedMysticalRock"));

        ItemListener.crystallizedLapis.setTexture(Identifier.of(NAMESPACE, "item/crystallizedLapis"));
        ItemListener.rawLapisIngot.setTexture(Identifier.of(NAMESPACE, "item/rawLapisIngot"));
        ItemListener.lapisIngot.setTexture(Identifier.of(NAMESPACE, "item/lapisIngot"));

        ItemListener.rawDiamondIngot.setTexture(Identifier.of(NAMESPACE, "item/rawDiamondIngot"));
        ItemListener.diamondIngot.setTexture(Identifier.of(NAMESPACE, "item/diamondIngot"));

        ItemListener.reinforcedGoldenSword.setTexture(Identifier.of(NAMESPACE, "item/reinforcedGoldenSword"));
        ItemListener.reinforcedGoldenShovel.setTexture(Identifier.of(NAMESPACE, "item/reinforcedGoldenShovel"));
        ItemListener.reinforcedGoldenPickaxe.setTexture(Identifier.of(NAMESPACE, "item/reinforcedGoldenPickaxe"));
        ItemListener.reinforcedGoldenAxe.setTexture(Identifier.of(NAMESPACE, "item/reinforcedGoldenAxe"));
        ItemListener.reinforcedGoldenHoe.setTexture(Identifier.of(NAMESPACE, "item/reinforcedGoldenHoe"));

        ItemListener.diamondGear.setTexture(Identifier.of(NAMESPACE, "item/diamondGear"));

        ItemListener.obsidianDust.setTexture(Identifier.of(NAMESPACE, "item/obsidianDust"));
        ItemListener.obsidianChunk.setTexture(Identifier.of(NAMESPACE, "item/obsidianChunk"));
        ItemListener.obsidianPlate.setTexture(Identifier.of(NAMESPACE, "item/obsidianPlate"));

        BlockListener.firePile.specifyTextures(getBlockTexture("block/firePile"));
        BlockListener.litFirePile.specifyTextures(getBlockTexture("block/litFirePile"));
        BlockListener.depletedFirePile.specifyTextures(getBlockTexture("block/depletedFirePile"));

        BlockListener.stoneBricks.specifyTextures(getBlockTexture("block/stoneBricks"));
        BlockListener.metalForge.specifyTextures(getBlockTexture("block/metalForgeTop"), getBlockTexture("block/metalForgeSide"), getBlockTexture("block/stoneBricks"));

        BlockListener.ironOreGravel.specifyTextures(getBlockTexture("block/ironOreGravel"));
        BlockListener.goldOreGravel.specifyTextures(getBlockTexture("block/goldOreGravel"));

        BlockListener.ironChunkBlock.specifyTextures(getBlockTexture("block/ironChunkBlock"));
        BlockListener.goldChunkBlock.specifyTextures(getBlockTexture("block/goldChunkBlock"));

        BlockListener.rotarySieve.specifyTextures(getBlockTexture("block/rotarySieveTop"), getBlockTexture("block/rotarySieveSide"), getBlockTexture("block/rotarySieveBottom"));

        BlockListener.mysticalCobblestone.specifyTextures(getBlockTexture("block/mysticalCobblestone"));
        BlockListener.mysticalGravel.specifyTextures(getBlockTexture("block/mysticalGravel"));

        BlockListener.lapisGravel.specifyTextures(getBlockTexture("block/lapisGravel"));

        BlockListener.emptyMonsterSiphon.specifyTextures(getBlockTexture("block/emptyMonsterSiphonTop"), getBlockTexture("block/monsterSiphonSide"), getBlockTexture("block/monsterSiphonBottom"));
        BlockListener.loadedMonsterSiphon.specifyTextures(getBlockTexture("block/loadedMonsterSiphonTop"), getBlockTexture("block/monsterSiphonSide"), getBlockTexture("block/monsterSiphonBottom"));
        BlockListener.chargedMonsterSiphon.specifyTextures(getBlockTexture("block/chargedMonsterSiphonTop"), getBlockTexture("block/monsterSiphonSide"), getBlockTexture("block/monsterSiphonBottom"));

        BlockListener.monsterSiphonExpander.specifyTextures(getBlockTexture("block/monsterSiphonExpander"));
        BlockListener.activeMonsterSiphonExpander.specifyTextures(getBlockTexture("block/activeMonsterSiphonExpander"));

        BlockListener.mysticalInfuserBase.specifyTextures(getBlockTexture("block/mysticalInfuserBase"));
        BlockListener.mysticalInfuser.specifyTextures(getBlockTexture("block/mysticalInfuserTop"), getBlockTexture("block/mysticalInfuserSide"), getBlockTexture("block/stoneBricks"));

        BlockListener.smoothObsidian.specifyTextures(getBlockTexture("block/smoothObsidian"));
        BlockListener.chiseledObsidian.specifyTextures(getBlockTexture("block/chiseledObsidian"));

        BlockListener.engravedObsidian.specifyTextures(getBlockTexture("block/engravedObsidian"));
        BlockListener.activeEngravedObsidian.specifyTextures(getBlockTexture("block/activeEngravedObsidian"));
        BlockListener.obsidianGlassMixture.specifyTextures(getBlockTexture("block/obsidianGlassMixture"));
        BlockListener.obsidianGlass.specifyTextures(getBlockTexture("block/obsidianGlass"));

        BlockListener.obsidianGenerator.specifyTextures(getBlockTexture("block/obsidianGeneratorTop"), getBlockTexture("block/obsidianGeneratorEmptySide"), getBlockTexture("block/obsidianGeneratorFullSide"), getBlockTexture("block/obsidianGeneratorBottom"));
        BlockListener.reinforcedMillStone.specifyTextures(getBlockTexture("block/reinforcedMillStoneTop"), getBlockTexture("block/reinforcedMillStoneSide"), getBlockTexture("block/reinforcedMillStoneBottom"));

        reinforcedSawSide = getBlockTexture("block/reinforcedSawSide");
        reinforcedSawFace = getBlockTexture("block/reinforcedSawFace");
        reinforcedSawSaw = getBlockTexture("block/reinforcedSawSaw");

        mysticalDevourerTop = getBlockTexture("block/mysticalDevourerTop");
        mysticalDevourerSide = getBlockTexture("block/mysticalDevourerSide");
        mysticalDevourerBottom = getBlockTexture("block/mysticalDevourerBottom");
        mysticalDevourerFront = getBlockTexture("block/mysticalDevourerFront");
    }

    public int getBlockTexture(String path) {
        return Atlases.getTerrain().addTexture(Identifier.of(NAMESPACE, path)).index;
    }

    public static int
            reinforcedSawSide,
            reinforcedSawFace,
            reinforcedSawSaw,

            mysticalDevourerTop,
            mysticalDevourerSide,
            mysticalDevourerBottom,
            mysticalDevourerFront;
}
