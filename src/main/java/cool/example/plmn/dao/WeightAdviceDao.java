package cool.example.plmn.dao;

import cool.example.plmn.entity.WeightAdvice;
import cool.example.plmn.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author wangshuo
 * @date 2023/5/15 16:39
 */

public class WeightAdviceDao {

    private volatile static WeightAdviceDao instance;
    public Connection conn = JDBCUtils.getConnection();


    public static WeightAdviceDao getInstance() {
        if (instance == null) {
            synchronized (WeightAdviceDao.class) {
                if (instance == null) {
                    instance = new WeightAdviceDao();
                }
            }
        }
        return instance;
    }

    public void insert(WeightAdvice weightAdvice) {
        final String sql = "INSERT INTO t_weight_advice (min_weight, max_weight, advice) VALUES (?, ?, ?)";
        try (PreparedStatement ps = JDBCUtils.createStatementProxy(conn.prepareStatement(sql))) {
            ps.setDouble(1, weightAdvice.getMinWeight());
            ps.setDouble(2, weightAdvice.getMaxWeight());
            ps.setString(3, weightAdvice.getAdvice());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public WeightAdvice findByWeight(double weight) {
        final String sql = "SELECT advice FROM t_weight_advice WHERE ? >= min_weight AND ? <= max_weight";
        try (PreparedStatement ps = JDBCUtils.createStatementProxy(conn.prepareStatement(sql))) {
            ps.setDouble(1, weight);
            ps.setDouble(2, weight);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new WeightAdvice(rs.getString("advice"));
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
