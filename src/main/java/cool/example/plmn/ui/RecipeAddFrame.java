package cool.example.plmn.ui;

import cool.example.plmn.dao.RecipeDao;

import javax.swing.*;

/**
 * @author wangshuo
 * @date 2023/5/16 3:06
 */

public class RecipeAddFrame extends RecipeOneFrame{

    public RecipeAddFrame() {
        setTitle("食谱-添加");
        saveButton.addActionListener(e -> {
            RecipeDao.getInstance().insert(entity());
            JOptionPane.showMessageDialog(RecipeAddFrame.this,
                    "食物价值信息添加成功", "注册", JOptionPane.INFORMATION_MESSAGE);
        });
    }
}
