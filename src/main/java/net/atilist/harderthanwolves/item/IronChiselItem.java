package net.atilist.harderthanwolves.item;

import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.modificationstation.stationapi.api.template.item.TemplatePickaxeItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class IronChiselItem extends TemplatePickaxeItem {
    public IronChiselItem(Identifier identifier) {
        super(identifier, ToolMaterial.IRON);
        this.setTranslationKey(identifier.namespace, identifier.path);
        this.setMaxCount(1);
        this.setMaxDamage(64);
    }

    public boolean postMine(ItemStack stack, int blockId, int x, int y, int z, LivingEntity miner) {
        if (blockId == Block.STONE.id) {
            stack.damage(1, miner);
        }

        return super.postMine(stack, blockId, x, y, z, miner);
    }
}
