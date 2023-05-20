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
    public void insert(FoodValue foodValue) throws SQLException {
        String sql = "INSERT INTO t_food_value (name, nutrition_value, calorie) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = JDBCUtils.prepareStatement(conn, sql)) {
            stmt.setString(1, foodValue.getName());
            stmt.setString(2, foodValue.getNutritionValue());
            stmt.setInt(3, foodValue.getCalorie());
            stmt.executeUpdate();
        }
    }

    // 根据食物名称查询食物记录
    public FoodValue findByName(String name) throws SQLException {
        String sql = "SELECT * FROM t_food_value WHERE name = ?";
        try (PreparedStatement stmt = JDBCUtils.prepareStatement(conn, sql)) {
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
        String sql = "SELECT * FROM t_food_value WHERE id = ?";
        try (PreparedStatement stmt = JDBCUtils.prepareStatement(conn, sql)) {
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
        String sql = "SELECT * FROM t_food_value";
        try (PreparedStatement stmt = JDBCUtils.prepareStatement(conn, sql); ResultSet rs = stmt.executeQuery()) {
            List<FoodValue> foodValues = new ArrayList<>();
            while (rs.next()) {
                foodValues.add(new FoodValue(rs.getInt("id"), rs.getString("name"), rs.getString("nutrition_value"), rs.getInt("calorie")));
            }
            return foodValues;
        }
    }

    // 更新食物记录
    public void update(FoodValue foodValue) throws SQLException {
        String sql = "UPDATE t_food_value SET name = ?, nutrition_value = ?, calorie = ? WHERE id = ?";
        try (PreparedStatement stmt = JDBCUtils.prepareStatement(conn, sql)) {
            stmt.setString(1, foodValue.getName());
            stmt.setString(2, foodValue.getNutritionValue());
            stmt.setInt(3, foodValue.getCalorie());
            stmt.setInt(4, foodValue.getId());
            stmt.executeUpdate();
        }
    }

    // 根据食物名称删除食物记录
    public void deleteByName(String name) throws SQLException {
        String sql = "DELETE FROM t_food_value WHERE name = ?";
        try (PreparedStatement stmt = JDBCUtils.prepareStatement(conn, sql)) {
            stmt.setString(1, name);
            stmt.executeUpdate();
        }
    }

    // 根据食物id删除食物记录
    public void deleteById(Integer id) throws SQLException {
        String sql = "DELETE FROM t_food_value WHERE id = ?";
        try (PreparedStatement stmt = JDBCUtils.prepareStatement(conn, sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

}
