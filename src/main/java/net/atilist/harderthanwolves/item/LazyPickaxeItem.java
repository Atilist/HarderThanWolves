package net.atilist.harderthanwolves.item;

import net.minecraft.item.ToolMaterial;
import net.modificationstation.stationapi.api.template.item.TemplatePickaxeItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class LazyPickaxeItem extends TemplatePickaxeItem {
    public LazyPickaxeItem(Identifier identifier, ToolMaterial toolMaterial) {
        super(identifier, toolMaterial);
        this.setTranslationKey(identifier.namespace, identifier.path);
    }
}
