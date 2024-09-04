package net.atilist.harderthanwolves.item;

import net.minecraft.item.ToolMaterial;
import net.modificationstation.stationapi.api.template.item.TemplateSwordItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class LazySwordItem extends TemplateSwordItem {
    public LazySwordItem(Identifier identifier, ToolMaterial toolMaterial) {
        super(identifier, toolMaterial);
        this.setTranslationKey(identifier.namespace, identifier.path);
    }
}
