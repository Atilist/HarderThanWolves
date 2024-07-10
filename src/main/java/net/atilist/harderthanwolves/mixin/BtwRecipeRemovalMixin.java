package net.atilist.harderthanwolves.mixin;

import net.kozibrodka.wolves.events.RecipeListener;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RecipeListener.class)
public class BtwRecipeRemovalMixin {
    @Inject(at = @At("TAIL"), method = "addAllModRecipes", remap = false)
    private static void removeSomeRecipes(RecipeRegisterEvent event, CallbackInfo ci) {
        //RecipeRemover.removeRecipe(BlockListener.hibachi);
    }
}
