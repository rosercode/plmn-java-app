package cool.example.plmn.dao;

import cool.example.plmn.entity.ExerciseRecords;
import cool.example.plmn.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangshuo
 * @date 2023/5/15 19:56
 */

public class ExerciseRecordsDao {

    private volatile static ExerciseRecordsDao instance;
    public Connection conn = JDBCUtils.getConnection();

    public static ExerciseRecordsDao getInstance() {
        if (instance == null) {
            synchronized (ExerciseRecordsDao.class) {
                if (instance == null) {
                    instance = new ExerciseRecordsDao();
                }
            }
        }
        return instance;
    }

    // 创建新的运动消耗记录
    public void insertExerciseRecord(ExerciseRecords entity) throws SQLException {
        String sql = "INSERT INTO ExerciseLogs (user_id, exercise_type, exercise_intensity, calories_burned, start_time, end_time) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = JDBCUtils.createStatementProxy(conn.prepareStatement(sql))) {
            statement.setInt(1, entity.getUserId());
            statement.setString(2, entity.getExerciseType());
            statement.setString(3, entity.getExerciseIntensity());
            statement.setFloat(4, entity.getCaloriesBurned());
            statement.setDate(5, new java.sql.Date(entity.getStartTime().getTime()));
            statement.setDate(6, new java.sql.Date(entity.getEndTime().getTime()));

            statement.executeUpdate();
        }
    }

    // 通过id获取特定的运动消耗记录
    public ExerciseRecords selectExerciseRecordById(int id) throws SQLException {
        String sql = "SELECT * FROM t_exercise_records WHERE id = ?";
        ExerciseRecords exerciseLog = null;

        try (PreparedStatement statement = JDBCUtils.createStatementProxy(conn.prepareStatement(sql))) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    exerciseLog = mapResultSetToExerciseLog(resultSet);
                }
            }
        }

        return exerciseLog;
    }

    // 更新运动消耗记录
    public void updateExerciseLog(ExerciseRecords exerciseLog) throws SQLException {
        String sql = "UPDATE t_exercise_records SET user_id = ?, exercise_type = ?, exercise_intensity = ?, calories_burned = ?, start_time = ?, end_time = ? WHERE id = ?";

        try (PreparedStatement statement = JDBCUtils.createStatementProxy(conn.prepareStatement(sql))) {
            statement.executeUpdate();
        }
    }

    // 删除运动消耗记录
    public void deleteExerciseLog(int id) throws SQLException {
        String sql = "DELETE FROM t_exercise_records WHERE id = ?";

        try (PreparedStatement statement = JDBCUtils.createStatementProxy(conn.prepareStatement(sql))) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    // 获取所有的运动消耗记录
    public List<ExerciseRecords> selectAll() throws SQLException {
        String sql = "SELECT * FROM t_exercise_records";
        List<ExerciseRecords> entities = new ArrayList<>();
        try (PreparedStatement statement = JDBCUtils.createStatementProxy(conn.prepareStatement(sql))) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    ExerciseRecords exerciseRecords = mapResultSetToExerciseLog(resultSet);
                    entities.add(exerciseRecords);
                }
            }
        }

        return entities;
    }

    // 将查询结果集映射到ExerciseLog对象
    private ExerciseRecords mapResultSetToExerciseLog(ResultSet resultSet) throws SQLException {
        ExerciseRecords exerciseLog = new ExerciseRecords();
        exerciseLog.setId(resultSet.getInt("id"));
        exerciseLog.setUserId(resultSet.getInt("user_id"));
        exerciseLog.setExerciseType(resultSet.getString("exercise_type"));
        exerciseLog.setExerciseIntensity(resultSet.getString("exercise_intensity"));
        exerciseLog.setCaloriesBurned(resultSet.getFloat("calories_burned"));
        exerciseLog.setStartTime(resultSet.getDate("start_time"));
        exerciseLog.setEndTime(resultSet.getDate("end_time"));
        return exerciseLog;
    }
}

