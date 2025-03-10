package net.atilist.harderthanwolves.compat.ami.reinforcedmillstone;

import net.glasslauncher.mods.alwaysmoreitems.api.gui.AMIDrawable;
import net.glasslauncher.mods.alwaysmoreitems.api.gui.GuiItemStackGroup;
import net.glasslauncher.mods.alwaysmoreitems.api.gui.RecipeLayout;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeCategory;
import net.glasslauncher.mods.alwaysmoreitems.api.recipe.RecipeWrapper;
import net.glasslauncher.mods.alwaysmoreitems.gui.DrawableHelper;
import net.kozibrodka.wolves.compat.ami.ItemRenderUtil;
import net.kozibrodka.wolves.events.BlockListener;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ReinforcedMillStoneRecipeCategory implements RecipeCategory {

    @NotNull
    private final AMIDrawable background = DrawableHelper.createDrawable("/assets/wolves/stationapi/gui/ami_tabs/one_in_one_out.png", 12, 0, 160, 100);

    @NotNull
    @Override
    public String getUid() {
        return "reinforced_mill_stone";
    }

    @NotNull
    @Override
    public String getTitle() {
        return "Reinforced Mill Stone";
    }

    @NotNull
    @Override
    public AMIDrawable getBackground() {
        return background;
    }

    @Override
    public void drawExtras(Minecraft minecraft) {
        ItemRenderUtil.drawScaledItem(minecraft, new ItemStack(net.atilist.harderthanwolves.events.init.BlockListener.reinforcedMillStone), 64, 28, 2);
        ItemRenderUtil.drawScaledItem(minecraft, new ItemStack(BlockListener.gearBox), 64, 70, 1);
        ItemRenderUtil.drawScaledItem(minecraft, new ItemStack(BlockListener.axleBlock), 80, 70, 1);
        minecraft.textRenderer.draw("Requires rotational force!", 15, 88, 0x884444);
    }

    @Override
    public void drawAnimations(Minecraft minecraft) {

    }

    @Override
    public void setRecipe(@NotNull RecipeLayout recipeLayout, @NotNull RecipeWrapper recipeWrapper) {
        GuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
        int xOffset = 13;
        int yOffset = 34;
        guiItemStacks.init(0, true, xOffset, yOffset);
        guiItemStacks.init(1, false, 116 + xOffset, yOffset);
        guiItemStacks.setFromRecipe(0, recipeWrapper.getInputs().get(0));
        guiItemStacks.setFromRecipe(1, recipeWrapper.getOutputs().get(0));
    }
}
