package cool.example.plmn.ui;

import cool.example.plmn.dao.ExerciseRecordsDao;
import cool.example.plmn.dao.WeightRecordDao;
import cool.example.plmn.entity.ExerciseRecords;
import cool.example.plmn.entity.WeightRecord;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

/**
 * @author wangshuo
 * @date 2023/5/16 16:38
 */

public class ExerciseRecordsUpdateFrame extends ExerciseRecordsOneFrame{

    public ExerciseRecordsUpdateFrame(ExerciseRecords entity) {
        setTitle("运动消耗记录 - 编辑");
        super.entity = entity;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        typeText.setText(entity.getExerciseType());
        intensityText.setText(entity.getExerciseIntensity());
        caloriesText.setText(String.valueOf(entity.getCaloriesBurned()));
        startTimeText.setText(format.format(entity.getStartTime()));
        endTimeText.setText(format.format(entity.getEndTime()));
        saveButton.addActionListener(e -> {
            try {
                ExerciseRecordsDao.getInstance().updateExerciseLog(entity());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }
}
