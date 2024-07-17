package net.atilist.harderthanwolves.block.entity;

import net.atilist.harderthanwolves.events.init.BlockListener;
import net.atilist.harderthanwolves.events.init.ItemListener;
import net.atilist.harderthanwolves.recipe.CraftingInventoryWithoutHandler;
import net.atilist.harderthanwolves.recipe.MysticalInfuserCraftingManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;

public class MysticalInfuserBlockEntity extends BlockEntity implements Inventory {
    private ItemStack[] inventory = new ItemStack[12];
    public int burnTime = 0;
    public int fuelTime = 0;
    public int cookTime = 0;
    public boolean structureRequirementsMet = false;

    @Override
    public int size() {
        return this.inventory.length;
    }

    @Override
    public ItemStack getStack(int slot) {
        return this.inventory[slot];
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        if (this.inventory[slot] != null) {
            ItemStack itemStack;
            if (this.inventory[slot].count <= amount) {
                itemStack = this.inventory[slot];
                this.inventory[slot] = null;
            } else {
                itemStack = this.inventory[slot].split(amount);
                if (this.inventory[slot].count == 0) {
                    this.inventory[slot] = null;
                }

            }
            return itemStack;
        } else {
            return null;
        }
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        this.inventory[slot] = stack;
        if (stack != null && stack.count > this.getMaxCountPerStack()) {
            stack.count = this.getMaxCountPerStack();
        }
    }

    @Override
    public String getName() {
        return "MysticalInfuser";
    }

    @Override
    public int getMaxCountPerStack() {
        return 64;
    }

    public void tick() {
        if (this.burnTime > 0) {
            --this.burnTime;
        }
        if (!this.world.isRemote) {
            if (this.burnTime == 0 && this.canAcceptRecipeOutput() && (this.inventory[1] == null || this.inventory[1].count < 64)) {
                this.fuelTime = this.burnTime = this.getFuelTime(this.inventory[0]);
                if (this.burnTime > 0) {
                    if (this.inventory[0] != null) {
                        --this.inventory[0].count;
                        if (this.inventory[0].count == 0) {
                            this.inventory[0] = null;
                        }
                    }
                    if (this.inventory[1] != null) {
                        ++this.inventory[1].count;
                    } else {
                        this.inventory[1] = new ItemStack(ItemListener.mysticalRock, 1);
                    }
                }
            }
            if (this.isBurning() && this.canAcceptRecipeOutput()) {
                if (!structureRequirementsMet) {
                    structureRequirementsMet = areStructureRequirementsMet();
                }
                if (!structureRequirementsMet) {
                    return;
                }
                ++this.cookTime;
                if (this.cookTime == 200) {
                    this.cookTime = 0;
                    this.craftRecipe();
                }
            } else {
                this.cookTime = 0;
            }
        }

    }

    private boolean canAcceptRecipeOutput() {
        if (this.inventory[3] == null) {
            return false;
        } else {
            CraftingInventoryWithoutHandler infusionMatrix = new CraftingInventoryWithoutHandler(3, 3);
            for (int slots = 0; slots < 9; slots++) {
                infusionMatrix.setStack(slots, this.inventory[slots + 3]);
            }
            ItemStack var1 = MysticalInfuserCraftingManager.getInstance().findMatchingRecipe(infusionMatrix);
            if (var1 == null) {
                return false;
            } else if (this.inventory[2] == null) {
                return true;
            } else if (!this.inventory[2].isItemEqual(var1)) {
                return false;
            } else if (this.inventory[2].count < this.getMaxCountPerStack() && this.inventory[2].count < this.inventory[2].getMaxCount()) {
                return true;
            } else {
                return this.inventory[2].count < var1.getMaxCount();
            }
        }
    }

    public void craftRecipe() {
        if (this.canAcceptRecipeOutput()) {
            CraftingInventoryWithoutHandler infusionMatrix = new CraftingInventoryWithoutHandler(3, 3);
            for (int slots = 0; slots < 9; slots++) {
                infusionMatrix.setStack(slots, this.inventory[slots + 3]);
            }
            ItemStack var1 = MysticalInfuserCraftingManager.getInstance().findMatchingRecipe(infusionMatrix);
            if (this.inventory[2] == null) {
                this.inventory[2] = var1.copy();
            } else if (this.inventory[2].itemId == var1.itemId) {
                ++this.inventory[2].count;
            }

            for (int inputSlots = 0; inputSlots < 9; inputSlots++) {
                if (this.inventory[inputSlots + 3] == null) {
                    continue;
                }
                --this.inventory[inputSlots + 3].count;
                if (this.inventory[inputSlots + 3].count <= 0) {
                    this.inventory[inputSlots + 3] = null;
                }
            }

        }
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        if (this.world.getBlockEntity(this.x, this.y, this.z) != this) {
            return false;
        } else {
            return !(player.getSquaredDistance((double)this.x + 0.5, (double)this.y + 0.5, (double)this.z + 0.5) > 64.0);
        }
    }

    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        NbtList var2 = nbt.getList("Items");
        this.inventory = new ItemStack[this.size()];

        for(int var3 = 0; var3 < var2.size(); ++var3) {
            NbtCompound var4 = (NbtCompound)var2.get(var3);
            byte var5 = var4.getByte("Slot");
            if (var5 >= 0 && var5 < this.inventory.length) {
                this.inventory[var5] = new ItemStack(var4);
            }
        }

        this.burnTime = nbt.getShort("BurnTime");
        this.cookTime = nbt.getShort("CookTime");
        this.fuelTime = this.getFuelTime(this.inventory[0]);
    }

    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putShort("BurnTime", (short)this.burnTime);
        nbt.putShort("CookTime", (short)this.cookTime);
        NbtList var2 = new NbtList();

        for(int var3 = 0; var3 < this.inventory.length; ++var3) {
            if (this.inventory[var3] != null) {
                NbtCompound var4 = new NbtCompound();
                var4.putByte("Slot", (byte)var3);
                this.inventory[var3].writeNbt(var4);
                var2.add(var4);
            }
        }

        nbt.put("Items", var2);
    }

    @Environment(EnvType.CLIENT)
    public int getCookTimeDelta(int multiplier) {
        return this.cookTime * multiplier / 200;
    }

    @Environment(EnvType.CLIENT)
    public int getFuelTimeDelta(int multiplier) {
        if (this.fuelTime == 0) {
            this.fuelTime = 200;
        }

        return this.burnTime * multiplier / this.fuelTime;
    }

    private int getFuelTime(ItemStack itemStack) {
        if (itemStack == null) {
            return 0;
        } else {
            int itemId = itemStack.getItem().id;
            if (itemId == ItemListener.chargedMysticalRock.id) {
                return 200;
            }
        }
        return 0;
    }

    public boolean isBurning() {
        return this.burnTime > 0;
    }

    public boolean areStructureRequirementsMet() {
        for (int yOffset = 0; yOffset < 4; yOffset++) {
            if (world.getBlockId(x + 2, y + yOffset, z + 2) != Block.BOOKSHELF.id) {
                return false;
            }
            if (world.getBlockId(x + 2, y + yOffset, z - 2) != Block.BOOKSHELF.id) {
                return false;
            }
            if (world.getBlockId(x - 2, y + yOffset, z + 2) != Block.BOOKSHELF.id) {
                return false;
            }
            if (world.getBlockId(x - 2, y + yOffset, z - 2) != Block.BOOKSHELF.id) {
                return false;
            }
        }
        for (int xOffset = -2; xOffset <= 2; xOffset++) {
            for (int zOffset = -2; zOffset <= 2; zOffset++) {
                if (world.getBlockId(x + xOffset, y - 1, z + zOffset) != BlockListener.mysticalInfuserBase.id) {
                    return false;
                }
            }
        }
        return true;
    }
}
