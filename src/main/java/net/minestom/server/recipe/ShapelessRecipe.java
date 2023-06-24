package net.minestom.server.recipe;

import dev.uten2c.rainstom.recipe.CraftingRecipeCategory;
import net.minestom.server.item.ItemStack;
import net.minestom.server.network.packet.server.play.DeclareRecipesPacket;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public abstract class ShapelessRecipe extends Recipe {
    private String group;
    private CraftingRecipeCategory category; // Rainstom categoryを追加
    private final List<DeclareRecipesPacket.Ingredient> ingredients;
    private ItemStack result;

    protected ShapelessRecipe(
            @NotNull String recipeId,
            @NotNull String group,
            @NotNull CraftingRecipeCategory category, // Rainstom categoryを追加
            @Nullable List<DeclareRecipesPacket.Ingredient> ingredients,
            @NotNull ItemStack result
    ) {
        super(Type.SHAPELESS, recipeId);
        this.group = group;
        this.category = category; // Rainstom categoryを追加
        this.ingredients = Objects.requireNonNullElseGet(ingredients, LinkedList::new);
        this.result = result;
    }

    @NotNull
    public String getGroup() {
        return group;
    }

    public void setGroup(@NotNull String group) {
        this.group = group;
    }

    // Rainstom start categoryを追加
    @NotNull
    public CraftingRecipeCategory getCategory() {
        return category;
    }

    public void setCategory(@NotNull CraftingRecipeCategory category) {
        this.category = category;
    }
    // Rainstom end categoryを追加

    public void addIngredient(DeclareRecipesPacket.Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    @NotNull
    public List<DeclareRecipesPacket.Ingredient> getIngredients() {
        return ingredients;
    }

    @NotNull
    public ItemStack getResult() {
        return result;
    }

    public void setResult(@NotNull ItemStack result) {
        this.result = result;
    }
}
