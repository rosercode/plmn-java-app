package cool.example.plmn.entity;

/**
 * @author wangshuo
 * @date 2023/5/15 19:20
 */

public class Recipe {
    private int recipeId;
    private String recipeName;
    private String recipeDescription;
    private String author;
    private int prepTime;
    private int cookingTime;
    private int servingSize;
    private String difficulty;
    private String ingredients;
    private String instructions;

    // Constructors
    public Recipe() {}

    public Recipe(String recipeName, String recipeDescription, String author, int prepTime, int cookingTime,
                  int servingSize, String difficulty, String ingredients, String instructions) {
        this.recipeName = recipeName;
        this.recipeDescription = recipeDescription;
        this.author = author;
        this.prepTime = prepTime;
        this.cookingTime = cookingTime;
        this.servingSize = servingSize;
        this.difficulty = difficulty;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    // Getters and Setters
    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public void setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public int getServingSize() {
        return servingSize;
    }

    public void setServingSize(int servingSize) {
        this.servingSize = servingSize;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "recipeId=" + recipeId +
                ", recipeName='" + recipeName + '\'' +
                ", recipeDescription='" + recipeDescription + '\'' +
                ", author='" + author + '\'' +
                ", prepTime=" + prepTime +
                ", cookingTime=" + cookingTime +
                ", servingSize=" + servingSize +
                ", difficulty='" + difficulty + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", instructions='" + instructions + '\'' +
                '}';
    }
}
