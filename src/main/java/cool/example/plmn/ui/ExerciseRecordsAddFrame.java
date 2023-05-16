package cool.example.plmn.ui;

import cool.example.plmn.App;
import cool.example.plmn.dao.ExerciseRecordsDao;
import cool.example.plmn.entity.ExerciseRecords;

import javax.swing.*;
import java.sql.SQLException;

/**
 * @author wangshuo
 * @date 2023/5/16 16:38
 */

public class ExerciseRecordsAddFrame extends ExerciseRecordsOneFrame {

    public ExerciseRecordsAddFrame() {
        setTitle("运动消耗记录 - 添加");
        saveButton.addActionListener(e -> {
            try {
                ExerciseRecords entity = entity();
                entity.setUserId(App.currentUser.getId());
                ExerciseRecordsDao.getInstance().insertExerciseRecord(entity);
                JOptionPane.showMessageDialog(ExerciseRecordsAddFrame.this,
                        "运动消耗记录添加成功", "注册", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }
}
