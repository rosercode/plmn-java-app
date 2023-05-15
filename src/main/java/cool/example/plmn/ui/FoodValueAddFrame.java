package cool.example.plmn.ui;

import cool.example.plmn.dao.FoodValueDao;

import javax.swing.*;
import java.sql.SQLException;

/**
 * @author wangshuo
 * @date 2023/5/16 2:45
 */

public class FoodValueAddFrame extends FoodValueOneFrame {
    public FoodValueAddFrame() {
        setTitle("食物价值信息 - 添加");
        saveButton.addActionListener(e -> {
            try {
                FoodValueDao.getInstance().insert(entity());
                JOptionPane.showMessageDialog(FoodValueAddFrame.this,
                        "食物价值信息添加成功", "注册", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }
}
