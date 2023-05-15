package cool.example.plmn.ui;

import cool.example.plmn.dao.FoodValueDao;
import cool.example.plmn.entity.FoodValue;

import java.sql.SQLException;

/**
 * @author wangshuo
 * @date 2023/5/16 2:45
 */

public class FoodValueUpdateFrame extends FoodValueOneFrame {

    public FoodValueUpdateFrame(FoodValue entity) {
        setTitle("食物价值信息 - 编辑");
        nameText.setText(entity.getName());
        valueText.setText(entity.getNutritionValue());
        calorieText.setText(String.valueOf(entity.getCalorie()));
        saveButton.addActionListener(e -> {
            entity.setName(nameText.getText());
            entity.setNutritionValue(valueText.getText());
            entity.setCalorie(Integer.parseInt(calorieText.getText()));
            try {
                FoodValueDao.getInstance().update(entity);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }
}
