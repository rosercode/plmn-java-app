package cool.example.plmn.ui;

import cool.example.plmn.dao.WeightRecordDao;
import cool.example.plmn.entity.WeightRecord;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

/**
 * @author wangshuo
 * @date 2023/5/16 8:37
 */

public class WeightRecordUpdateFrame extends WeightRecordOneFrame{

    public WeightRecordUpdateFrame(WeightRecord entity) {
        setTitle("体重记录-添加");
        super.entity = entity;
        recordTimeText.setText(entity.getRecordTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        weightText.setText(String.valueOf(entity.getWeight()));
        noteText.setText(String.valueOf(entity.getNote()));
        saveButton.addActionListener(e -> {
            try {
                WeightRecordDao.getInstance().updateById(entity());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }
}
