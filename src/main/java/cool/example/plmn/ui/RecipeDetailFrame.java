package cool.example.plmn.ui;

import cool.example.plmn.entity.Recipe;

/**
 * @author wangshuo
 * @date 2023/5/16 3:06
 */

public class RecipeDetailFrame extends RecipeUpdateFrame{

    public RecipeDetailFrame(Recipe recipe) {
        super(recipe);
        setTitle("食谱-详情");
        nameText.setEditable(false);
        descriptionText.setEditable(false);
        authorText.setEditable(false);
        prepTimeText.setEditable(false);
        cookingTimeText.setEditable(false);
        servingSizeText.setEditable(false);
        difficultyComboBox.setEditable(false);
        ingredientsText.setEditable(false);
        instructionsText.setEditable(false);
    }
}
