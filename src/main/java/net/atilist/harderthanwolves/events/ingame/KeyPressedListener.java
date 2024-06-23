package net.atilist.harderthanwolves.events.ingame;

import net.fabricmc.loader.api.FabricLoader;
import net.atilist.harderthanwolves.events.init.AchievementListener;
import net.atilist.harderthanwolves.events.init.KeyBindingListener;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.Minecraft;
import net.modificationstation.stationapi.api.client.event.keyboard.KeyStateChangedEvent;
import org.lwjgl.input.Keyboard;

public class KeyPressedListener {

    @EventListener
    public void keyPressed(KeyStateChangedEvent event) {
    }
}
