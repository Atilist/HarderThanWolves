package net.atilist.harderthanwolves.events.init;

import net.atilist.harderthanwolves.block.entity.MysticalInfuserBlockEntity;
import net.atilist.harderthanwolves.gui.MysticalInfuserScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kozibrodka.wolves.block.entity.*;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.modificationstation.stationapi.api.event.registry.GuiHandlerRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.GuiHandlerRegistry;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;
import uk.co.benjiweber.expressions.tuple.BiTuple;

public class ScreenHandlerListener {

    @Entrypoint.Namespace
    public static final Namespace NAMESPACE = Null.get();

    @Environment(EnvType.CLIENT)
    @EventListener
    public void registerScreenHandlers(GuiHandlerRegistryEvent event) {
        GuiHandlerRegistry registry = event.registry;
        registry.registerValueNoMessage(Identifier.of(NAMESPACE, "openMysticalInfuser"), BiTuple.of(this::openMysticalInfuser, HopperBlockEntity::new));
    }

    public static int tempGuiX;
    public static int tempGuiY;
    public static int tempGuiZ;

    @Environment(EnvType.CLIENT)
    public Screen openMysticalInfuser(PlayerEntity player, Inventory inventoryBase) {
        return new MysticalInfuserScreen(player.inventory, (MysticalInfuserBlockEntity) inventoryBase, tempGuiX, tempGuiY, tempGuiZ);
    }
}
