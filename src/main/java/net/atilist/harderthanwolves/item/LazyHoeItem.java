package net.atilist.harderthanwolves.item;

import net.minecraft.item.ToolMaterial;
import net.modificationstation.stationapi.api.template.item.TemplateHoeItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class LazyHoeItem extends TemplateHoeItem {
    public LazyHoeItem(Identifier identifier, ToolMaterial toolMaterial) {
        super(identifier, toolMaterial);
        this.setTranslationKey(identifier.namespace, identifier.path);
    }
}
