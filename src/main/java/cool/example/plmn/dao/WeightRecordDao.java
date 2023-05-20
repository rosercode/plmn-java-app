package cool.example.plmn.dao;

import cool.example.plmn.entity.WeightRecord;
import cool.example.plmn.utils.JDBCUtils;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Dictionary;
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
        try (PreparedStatement statement = JDBCUtils.prepareStatement(conn, sql)) {
            statement.setTimestamp(1, Timestamp.valueOf(record.getRecordTime()));
            statement.setInt(2, record.getUserId());
            statement.setDouble(3, record.getWeight());
            statement.setString(4, record.getNote());
            statement.executeUpdate();
        }
    }

    public WeightRecord selectById(Integer id) throws SQLException {
        String sql = "SELECT * FROM t_weight_records WHERE id = ?";
        PreparedStatement statement = JDBCUtils.prepareStatement(conn, sql);
        statement.setInt(1, id);
        return selectOne(statement);
    }

    // 要获取指定用户的最新一条记录
    public WeightRecord selectLatestRecordByUserId(Integer userId) throws SQLException {
        String sql = "SELECT * FROM t_weight_records WHERE user_id = ? ORDER BY record_time DESC LIMIT 1";
        PreparedStatement statement = JDBCUtils.prepareStatement(conn, sql);
        statement.setInt(1, userId);
        return selectOne(statement);
    }

    public WeightRecord selectOne(PreparedStatement pstmt) throws SQLException {
        WeightRecord entity = new WeightRecord();
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return new WeightRecord(rs.getInt("id"), rs.getTimestamp("record_time").toLocalDateTime(),
                        rs.getInt("user_id"), rs.getDouble("weight"), rs.getString("note"));
            }
        }
        return entity;
    }


    public List<WeightRecord> selectWeightRecordsByUserId(int userId) throws SQLException {
        final String sql = "SELECT * FROM t_weight_records WHERE user_id = ?";
        List<WeightRecord> records = new ArrayList<>();
        try (PreparedStatement statement = JDBCUtils.prepareStatement(conn, sql)) {
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
        PreparedStatement statement = JDBCUtils.prepareStatement(conn, sql);
        statement.setObject(1, entity.getRecordTime());
        statement.setInt(2, entity.getUserId());
        statement.setDouble(3, entity.getWeight());
        statement.setString(4, entity.getNote());
        statement.setInt(5, entity.getId());
        statement.executeUpdate();
    }

    public void deleteById(Integer id) throws SQLException {
        String sql = "DELETE FROM t_weight_records WHERE id = ?";
        PreparedStatement statement = JDBCUtils.prepareStatement(conn, sql);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

}