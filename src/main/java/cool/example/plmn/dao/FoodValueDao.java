package cool.example.plmn.dao;

import cool.example.plmn.entity.FoodValue;
import cool.example.plmn.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangshuo
 * @date 2023/5/15 16:49
 */

public class FoodValueDao {

    private volatile static FoodValueDao instance;
    public Connection conn = JDBCUtils.getConnection();


    public static FoodValueDao getInstance() {
        if (instance == null) {
            synchronized (FoodValueDao.class) {
                if (instance == null) {
                    instance = new FoodValueDao();
                }
            }
        }
        return instance;
    }

    // 插入一条食物记录
    public void insert(FoodValue entity) throws SQLException {
        String sql = "INSERT INTO t_food_ranking (name, nutrition_value, calorie) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = JDBCUtils.createStatementProxy(conn.prepareStatement(sql))) {
            stmt.setString(1, entity.getName());
            stmt.setString(2, entity.getNutritionValue());
            stmt.setInt(3, entity.getCalorie());
            stmt.executeUpdate();
        }
    }

    // 根据食物名称查询食物记录
    public FoodValue findByName(String name) throws SQLException {
        String sql = "SELECT * FROM t_food_ranking WHERE name = ?";
        try (PreparedStatement stmt = JDBCUtils.createStatementProxy(conn.prepareStatement(sql))) {
            stmt.setString(1, name);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new FoodValue(rs.getString("name"), rs.getString("nutrition_value"), rs.getInt("calorie"));
                } else {
                    return null;
                }
            }
        }
    }

    // 根据食物 id查询食物记录
    public FoodValue findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM t_food_ranking WHERE id = ?";
        try (PreparedStatement stmt = JDBCUtils.createStatementProxy(conn.prepareStatement(sql))) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new FoodValue(rs.getInt("id"), rs.getString("name"), rs.getString("nutrition_value"), rs.getInt("calorie"));
                } else {
                    return null;
                }
            }
        }
    }

    // 查询所有食物记录
    public List<FoodValue> selectAll() throws SQLException {
        String sql = "SELECT * FROM t_food_ranking";
        try (PreparedStatement stmt = JDBCUtils.createStatementProxy(conn.prepareStatement(sql)); ResultSet rs = stmt.executeQuery()) {
            List<FoodValue> foods = new ArrayList<>();
            while (rs.next()) {
                foods.add(new FoodValue(rs.getInt("id"), rs.getString("name"), rs.getString("nutrition_value"), rs.getInt("calorie")));
            }
            return foods;
        }
    }

    // 更新食物记录
    public void update(FoodValue food) throws SQLException {
        String sql = "UPDATE t_food_ranking SET name = ?, nutrition_value = ?, calorie = ? WHERE id = ?";
        try (PreparedStatement stmt = JDBCUtils.createStatementProxy(conn.prepareStatement(sql))) {
            stmt.setString(1, food.getName());
            stmt.setString(2, food.getNutritionValue());
            stmt.setInt(3, food.getCalorie());
            stmt.setInt(4, food.getId());
            stmt.executeUpdate();
        }
    }

    // 根据食物名称删除食物记录
    public void deleteByName(String name) throws SQLException {
        String sql = "DELETE FROM t_food_ranking WHERE name = ?";
        try (PreparedStatement stmt = JDBCUtils.createStatementProxy(conn.prepareStatement(sql))) {
            stmt.setString(1, name);
            stmt.executeUpdate();
        }
    }

    // 根据食物id删除食物记录
    public void deleteById(Integer id) throws SQLException {
        String sql = "DELETE FROM t_food_ranking WHERE id = ?";
        try (PreparedStatement stmt = JDBCUtils.createStatementProxy(conn.prepareStatement(sql))) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

}
