package net.atilist.harderthanwolves.recipe;

import net.kozibrodka.wolves.events.ItemListener;
import net.minecraft.item.ItemStack;

public class MysticalInfuserShapedRecipe implements MysticalInfuserRecipeTemplate {
    private int width;
    private int height;
    private ItemStack[] ingredients;
    private ItemStack output;
    public final int outputId;

    public MysticalInfuserShapedRecipe(int i, int j, ItemStack[] args, ItemStack arg) {
        this.outputId = arg.itemId;
        this.width = i;
        this.height = j;
        this.ingredients = args;
        this.output = arg;
    }

    public ItemStack getOutput() {
        return this.output;
    }

    public boolean canCraft(CraftingInventoryWithoutHandler arg) {
        for(int var2 = 0; var2 <= 3 - this.width; ++var2) {
            for(int var3 = 0; var3 <= 3 - this.height; ++var3) {
                if (this.matches(arg, var2, var3, true)) {
                    return true;
                }

                if (this.matches(arg, var2, var3, false)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean matches(CraftingInventoryWithoutHandler arg, int i, int j, boolean bl) {
        for(int var5 = 0; var5 < 3; ++var5) {
            for(int var6 = 0; var6 < 3; ++var6) {
                int var7 = var5 - i;
                int var8 = var6 - j;
                ItemStack var9 = null;
                if (var7 >= 0 && var8 >= 0 && var7 < this.width && var8 < this.height) {
                    if (bl) {
                        var9 = this.ingredients[this.width - var7 - 1 + var8 * this.width];
                    } else {
                        var9 = this.ingredients[var7 + var8 * this.width];
                    }
                }

                ItemStack var10 = arg.getStack(var5, var6);
                if (var10 != null || var9 != null) {
                    if (var10 == null && var9 != null || var10 != null && var9 == null) {
                        return false;
                    }

                    if (var9.itemId != var10.itemId) {
                        return false;
                    }

                    if (var9.getDamage() != -1 && var9.getDamage() != var10.getDamage()) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public ItemStack craft(CraftingInventoryWithoutHandler arg) {
        return new ItemStack(this.output.itemId, this.output.count, this.output.getDamage());
    }

    public int getIngredientCount() {
        return this.width * this.height;
    }

    public int getWidth() {
        return width;
    }

    public ItemStack[] getIngredients() {
        int widthCompensation = 3 - width;
        ItemStack[] convertedIngredients = new ItemStack[9];
        for (int i = 0; i < ingredients.length; i++) {
            ItemStack ingredient = ingredients[i];
            if (ingredient == null) {
                continue;
            }
            convertedIngredients[i + widthCompensation * (i / width)] = ingredient.copy();
        }
        for (int i = 0; i < 9; i++) {
            if (convertedIngredients[i] == null) {
                convertedIngredients[i] = new ItemStack(ItemListener.nothing, 1);
            }
        }
        return convertedIngredients;
    }
}
