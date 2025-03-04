package net.atilist.harderthanwolves.mixin;

import net.atilist.harderthanwolves.events.init.BlockListener;
import net.minecraft.block.NetherPortalBlock;
import net.minecraft.world.World;
import net.minecraft.world.dimension.NetherDimension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NetherPortalBlock.class)
public class NetherPortalMixin {
    @Inject(at = @At("HEAD"), method = "create", cancellable = true)
    private void addIgnitionCondition(World world, int x, int y, int z, CallbackInfoReturnable<Boolean> cir) {
        if (world.dimension instanceof NetherDimension) {
            return;
        }
        boolean xPortal = (world.getBlockId(x + 1, y - 1, z) == BlockListener.chiseledObsidian.id && world.getBlockId(x - 2, y - 1, z) == BlockListener.chiseledObsidian.id && world.getBlockId(x + 1, y + 3, z) == BlockListener.chiseledObsidian.id && world.getBlockId(x - 2, y + 3, z) == BlockListener.chiseledObsidian.id
                && world.getBlockId(x, y - 1, z + 1) == BlockListener.activeEngravedObsidian.id  && world.getBlockId(x - 1, y - 1, z + 1) == BlockListener.activeEngravedObsidian.id && world.getBlockId(x, y - 1, z - 1) == BlockListener.activeEngravedObsidian.id  && world.getBlockId(x - 1, y - 1, z - 1) == BlockListener.activeEngravedObsidian.id)
                || (world.getBlockId(x + 2, y - 1, z) == BlockListener.chiseledObsidian.id && world.getBlockId(x - 1, y - 1, z) == BlockListener.chiseledObsidian.id && world.getBlockId(x + 2, y + 3, z) == BlockListener.chiseledObsidian.id && world.getBlockId(x - 1, y + 3, z) == BlockListener.chiseledObsidian.id
                && world.getBlockId(x + 1, y - 1, z + 1) == BlockListener.activeEngravedObsidian.id  && world.getBlockId(x, y - 1, z + 1) == BlockListener.activeEngravedObsidian.id && world.getBlockId(x + 1, y - 1, z - 1) == BlockListener.activeEngravedObsidian.id  && world.getBlockId(x, y - 1, z - 1) == BlockListener.activeEngravedObsidian.id);
        boolean zPortal = (world.getBlockId(x, y - 1, z + 1) == BlockListener.chiseledObsidian.id && world.getBlockId(x, y - 1, z - 2) == BlockListener.chiseledObsidian.id && world.getBlockId(x, y + 3, z + 1) == BlockListener.chiseledObsidian.id && world.getBlockId(x, y + 3, z - 2) == BlockListener.chiseledObsidian.id
                && world.getBlockId(x + 1, y - 1, z) == BlockListener.activeEngravedObsidian.id  && world.getBlockId(x + 1, y - 1, z - 1) == BlockListener.activeEngravedObsidian.id && world.getBlockId(x - 1, y - 1, z) == BlockListener.activeEngravedObsidian.id  && world.getBlockId(x - 1, y - 1, z - 1) == BlockListener.activeEngravedObsidian.id)
                || (world.getBlockId(x, y - 1, z + 2) == BlockListener.chiseledObsidian.id && world.getBlockId(x, y - 1, z - 1) == BlockListener.chiseledObsidian.id && world.getBlockId(x, y + 3, z + 2) == BlockListener.chiseledObsidian.id && world.getBlockId(x, y + 3, z - 1) == BlockListener.chiseledObsidian.id
                && world.getBlockId(x + 1, y - 1, z + 1) == BlockListener.activeEngravedObsidian.id  && world.getBlockId(x + 1, y - 1, z) == BlockListener.activeEngravedObsidian.id && world.getBlockId(x - 1, y - 1, z + 1) == BlockListener.activeEngravedObsidian.id  && world.getBlockId(x - 1, y - 1, z) == BlockListener.activeEngravedObsidian.id);
        if (!xPortal && !zPortal) {
            cir.setReturnValue(false);
        }
    }
}
