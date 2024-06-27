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

        ItemListener.rawDiamondIngot.setTexture(Identifier.of(NAMESPACE, "item/rawDiamondIngot"));
        ItemListener.diamondIngot.setTexture(Identifier.of(NAMESPACE, "item/diamondIngot"));

        BlockListener.firePile.specifyTextures(getBlockTexture("block/firePile"));
        BlockListener.litFirePile.specifyTextures(getBlockTexture("block/litFirePile"));
        BlockListener.depletedFirePile.specifyTextures(getBlockTexture("block/depletedFirePile"));

        BlockListener.stoneBricks.specifyTextures(getBlockTexture("block/stoneBricks"));
        BlockListener.metalForge.specifyTextures(getBlockTexture("block/metalForgeTop"), getBlockTexture("block/metalForgeSide"), getBlockTexture("block/stoneBricks"));

        BlockListener.ironOreGravel.specifyTextures(getBlockTexture("block/ironOreGravel"));
        BlockListener.goldOreGravel.specifyTextures(getBlockTexture("block/goldOreGravel"));

        BlockListener.smoothObsidian.specifyTextures(getBlockTexture("block/smoothObsidian"));
        BlockListener.chiseledObsidian.specifyTextures(getBlockTexture("block/chiseledObsidian"));
    }

    public int getBlockTexture(String path) {
        return Atlases.getTerrain().addTexture(Identifier.of(NAMESPACE, path)).index;
    }
}
