package cool.example.plmn.dao;

import cool.example.plmn.entity.WeightRecord;
import cool.example.plmn.utils.JDBCUtils;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangshuo
 * @date 2023/5/15 19:48
 */


public class WeightRecordDao {

    private volatile static WeightRecordDao instance;
    public Connection connection = JDBCUtils.getConnection();


    public static WeightRecordDao getInstance() {
        if (instance == null) {
            synchronized (WeightRecordDao.class) {
                if (instance == null) {
                    instance = new WeightRecordDao();
                }
            }
        }
        return instance;
    }

    public void insertWeightRecord(WeightRecord record) throws SQLException {
        final String sql = "INSERT INTO weight_records (record_time, user_id, weight, note) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = JDBCUtils.createStatementProxy(connection.prepareStatement(sql))) {
            statement.setTimestamp(1, Timestamp.valueOf(record.getRecordTime()));
            statement.setInt(2, record.getUserId());
            statement.setDouble(3, record.getWeight());
            statement.setString(4, record.getNote());
            statement.executeUpdate();
        }
    }

    public List<WeightRecord> selectWeightRecordsByUserId(int userId) throws SQLException {
        final String sql = "SELECT * FROM weight_records WHERE user_id = ?";
        List<WeightRecord> records = new ArrayList<>();
        try (PreparedStatement statement = JDBCUtils.createStatementProxy(connection.prepareStatement(sql))) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    LocalDateTime recordTime = resultSet.getTimestamp("record_time").toLocalDateTime();
                    double weight = resultSet.getDouble("weight");
                    String note = resultSet.getString("note");
                    WeightRecord record = new WeightRecord(id, recordTime, userId, weight, note);
                    records.add(record);
                }
            }
        }
        return records;
    }
}