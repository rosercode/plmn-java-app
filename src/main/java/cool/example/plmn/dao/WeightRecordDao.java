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
    public Connection conn = JDBCUtils.getConnection();


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
        final String sql = "INSERT INTO t_weight_records (record_time, user_id, weight, note) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = JDBCUtils.createStatementProxy(conn.prepareStatement(sql))) {
            statement.setTimestamp(1, Timestamp.valueOf(record.getRecordTime()));
            statement.setInt(2, record.getUserId());
            statement.setDouble(3, record.getWeight());
            statement.setString(4, record.getNote());
            statement.executeUpdate();
        }
    }

    public WeightRecord selectById(Integer id) throws SQLException {
        String sql = "SELECT * FROM t_weight_records WHERE id = ?";
        PreparedStatement statement = JDBCUtils.createStatementProxy(conn.prepareStatement(sql));
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return new WeightRecord(resultSet.getInt("id"), resultSet.getTimestamp("record_time").toLocalDateTime(),
                    resultSet.getInt("user_id"),resultSet.getDouble("weight"),  resultSet.getString("note"));
        }
        return null;
    }

    public List<WeightRecord> selectWeightRecordsByUserId(int userId) throws SQLException {
        final String sql = "SELECT * FROM t_weight_records WHERE user_id = ?";
        List<WeightRecord> records = new ArrayList<>();
        try (PreparedStatement statement = JDBCUtils.createStatementProxy(conn.prepareStatement(sql))) {
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

    public void updateById(WeightRecord entity) throws SQLException {
        String sql = "UPDATE t_weight_records SET record_time = ?, user_id = ?, weight = ?," +
                " note = ? WHERE id = ?";
        PreparedStatement statement = JDBCUtils.createStatementProxy(conn.prepareStatement(sql));
        statement.setObject(1, entity.getRecordTime());
        statement.setInt(2, entity.getUserId());
        statement.setDouble(3, entity.getWeight());
        statement.setString(4, entity.getNote());
        statement.setInt(5, entity.getId());
        statement.executeUpdate();
    }

    public void deleteById(Integer id) throws SQLException {
        String sql = "DELETE FROM t_weight_records WHERE id = ?";
        PreparedStatement statement = JDBCUtils.createStatementProxy(conn.prepareStatement(sql));
        statement.setInt(1, id);
        statement.executeUpdate();
    }

}