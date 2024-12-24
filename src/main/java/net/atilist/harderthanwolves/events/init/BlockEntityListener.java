package net.atilist.harderthanwolves.events.init;

import net.atilist.harderthanwolves.block.entity.MysticalInfuserBlockEntity;
import net.atilist.harderthanwolves.block.entity.ReinforcedMillStoneBlockEntity;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.block.entity.BlockEntityRegisterEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

public class BlockEntityListener {

    @Entrypoint.Namespace
    public static final Namespace NAMESPACE = Null.get();

    @EventListener
    private static void registerBlockEntities(BlockEntityRegisterEvent event) {
        event.register(MysticalInfuserBlockEntity.class, String.valueOf(Identifier.of(NAMESPACE, "MysticalInfuserBlock")));
        event.register(ReinforcedMillStoneBlockEntity.class, String.valueOf(Identifier.of(NAMESPACE, "ReinforcedMillStone")));
    }
}
