package cool.example.plmn.ui;

import cool.example.plmn.App;
import cool.example.plmn.dao.WeightRecordDao;
import cool.example.plmn.entity.WeightRecord;

import javax.swing.*;
import java.sql.SQLException;

/**
 * @author wangshuo
 * @date 2023/5/16 8:37
 */

public class WeightRecordAddFrame extends WeightRecordOneFrame{

    public WeightRecordAddFrame() {
        setTitle("体重记录-添加");
        saveButton.addActionListener(e -> {
            try {
                WeightRecord entity = entity();
                if (App.currentUser==null){
                    return;
                }
                entity.setUserId(App.currentUser.getId());
                WeightRecordDao.getInstance().insertWeightRecord(entity);
                JOptionPane.showMessageDialog(WeightRecordAddFrame.this,
                        "体重记录信息添加成功", "成功", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }
}
