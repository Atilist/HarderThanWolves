package net.atilist.harderthanwolves.item;

import net.minecraft.item.ToolMaterial;
import net.modificationstation.stationapi.api.template.item.TemplateAxeItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class LazyAxeItem extends TemplateAxeItem {
    public LazyAxeItem(Identifier identifier, ToolMaterial toolMaterial) {
        super(identifier, toolMaterial);
        this.setTranslationKey(identifier.namespace, identifier.path);
    }
}
