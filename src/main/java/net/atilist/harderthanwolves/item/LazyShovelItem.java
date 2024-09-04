package net.atilist.harderthanwolves.item;

import net.minecraft.item.ToolMaterial;
import net.modificationstation.stationapi.api.template.item.TemplateShovelItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class LazyShovelItem extends TemplateShovelItem {
    public LazyShovelItem(Identifier identifier, ToolMaterial toolMaterial) {
        super(identifier, toolMaterial);
        this.setTranslationKey(identifier.namespace, identifier.path);
    }
}
