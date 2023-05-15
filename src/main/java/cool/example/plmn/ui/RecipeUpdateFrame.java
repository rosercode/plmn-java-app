package cool.example.plmn.ui;

import cool.example.plmn.dao.RecipeDao;
import cool.example.plmn.entity.Recipe;

import java.sql.SQLException;

/**
 * @author wangshuo
 * @date 2023/5/16 3:06
 */

public class RecipeUpdateFrame extends RecipeOneFrame{

    public RecipeUpdateFrame(Recipe entity) {
        super.entity = entity;
        setTitle("食谱-编辑");
        nameText.setText(entity.getRecipeName());
        descriptionText.setText(entity.getRecipeDescription());
        authorText.setText(entity.getAuthor());
        prepTimeText.setText(String.valueOf(entity.getPrepTime()));
        cookingTimeText.setText(String.valueOf(entity.getCookingTime()));
        servingSizeText.setText(String.valueOf(entity.getServingSize()));
        difficultyComboBox.setSelectedItem(entity.getDifficulty());
        ingredientsText.setText(entity.getIngredients());
        instructionsText.setText(entity.getInstructions());
        saveButton.addActionListener(e -> {
            try {
                RecipeDao.getInstance().updateById(entity());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

    }
}
