package cool.example.plmn.dao;

import cool.example.plmn.entity.Food;
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

public class FoodDao {

    private volatile static FoodDao instance;
    public Connection conn = JDBCUtils.getConnection();


    public static FoodDao getInstance() {
        if (instance == null) {
            synchronized (FoodDao.class) {
                if (instance == null) {
                    instance = new FoodDao();
                }
            }
        }
        return instance;
    }

    // 插入一条食物记录
    public void insert(Food food) throws SQLException {
        String sql = "INSERT INTO t_food_ranking (name, nutrition_value, calorie) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = JDBCUtils.createStatementProxy(conn.prepareStatement(sql))) {
            stmt.setString(1, food.getName());
            stmt.setString(2, food.getNutritionValue());
            stmt.setInt(3, food.getCalorie());
            stmt.executeUpdate();
        }
    }

    // 根据食物名称查询食物记录
    public Food findByName(String name) throws SQLException {
        String sql = "SELECT * FROM t_food_ranking WHERE name = ?";
        try (PreparedStatement stmt = JDBCUtils.createStatementProxy(conn.prepareStatement(sql))) {
            stmt.setString(1, name);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Food(rs.getString("name"), rs.getString("nutrition_value"), rs.getInt("calorie"));
                } else {
                    return null;
                }
            }
        }
    }

    // 查询所有食物记录
    public List<Food> selectAll() throws SQLException {
        String sql = "SELECT * FROM t_food_ranking";
        try (PreparedStatement stmt = JDBCUtils.createStatementProxy(conn.prepareStatement(sql)); ResultSet rs = stmt.executeQuery()) {
            List<Food> foods = new ArrayList<>();
            while (rs.next()) {
                foods.add(new Food(rs.getString("name"), rs.getString("nutrition_value"), rs.getInt("calorie")));
            }
            return foods;
        }
    }

    // 更新食物记录
    public void update(Food food) throws SQLException {
        String sql = "UPDATE t_food_ranking SET name = ?, nutrition_value = ?, calorie = ? WHERE id = ?";
        try (PreparedStatement stmt = JDBCUtils.createStatementProxy(conn.prepareStatement(sql))) {
            stmt.setString(1, food.getName());
            stmt.setString(2, food.getNutritionValue());
            stmt.setInt(3, food.getCalorie());
            stmt.setInt(3, food.getId());
            stmt.executeUpdate();
        }
    }

    // 删除食物记录
    public void delete(String name) throws SQLException {
        String sql = "DELETE FROM t_food WHERE name = ?";
        try (PreparedStatement stmt = JDBCUtils.createStatementProxy(conn.prepareStatement(sql))) {
            stmt.setString(1, name);
            stmt.executeUpdate();
        }
    }
}
