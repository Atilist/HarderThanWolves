package net.atilist.harderthanwolves.recipe;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MysticalInfuserShapelessRecipe implements MysticalInfuserRecipeTemplate {
    private final ItemStack output;
    private final List input;

    public MysticalInfuserShapelessRecipe(ItemStack arg, List list) {
        this.output = arg;
        this.input = list;
    }

    public ItemStack getOutput() {
        return this.output;
    }

    public boolean canCraft(CraftingInventoryWithoutHandler arg) {
        ArrayList var2 = new ArrayList(this.input);

        for(int var3 = 0; var3 < 3; ++var3) {
            for(int var4 = 0; var4 < 3; ++var4) {
                ItemStack var5 = arg.getStack(var4, var3);
                if (var5 != null) {
                    boolean var6 = false;
                    Iterator var7 = var2.iterator();

                    while(var7.hasNext()) {
                        ItemStack var8 = (ItemStack)var7.next();
                        if (var5.itemId == var8.itemId && (var8.getDamage() == -1 || var5.getDamage() == var8.getDamage())) {
                            var6 = true;
                            var2.remove(var8);
                            break;
                        }
                    }

                    if (!var6) {
                        return false;
                    }
                }
            }
        }

        return var2.isEmpty();
    }

    public ItemStack craft(CraftingInventoryWithoutHandler arg) {
        return this.output.copy();
    }

    public int getIngredientCount() {
        return this.input.size();
    }
}
