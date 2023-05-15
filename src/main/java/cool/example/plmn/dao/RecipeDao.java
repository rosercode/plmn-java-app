package cool.example.plmn.dao;

import cool.example.plmn.entity.Recipe;
import cool.example.plmn.utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangshuo
 * @date 2023/5/15 19:21
 */

public class RecipeDao {

    private volatile static RecipeDao instance;
    public Connection conn = JDBCUtils.getConnection();


    public static RecipeDao getInstance() {
        if (instance == null) {
            synchronized (RecipeDao.class) {
                if (instance == null) {
                    instance = new RecipeDao();
                }
            }
        }
        return instance;
    }

    // 插入新食谱
    public void insert(Recipe recipe) {
        final String sql = "INSERT INTO t_recipes (recipe_name, recipe_description, author, prep_time, " +
                "cooking_time, serving_size, difficulty, ingredients, instructions) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = JDBCUtils.createStatementProxy(conn.prepareStatement(sql))) {
            pstmt.setString(1, recipe.getRecipeName());
            pstmt.setString(2, recipe.getRecipeDescription());
            pstmt.setString(3, recipe.getAuthor());
            pstmt.setInt(4, recipe.getPrepTime());
            pstmt.setInt(5, recipe.getCookingTime());
            pstmt.setInt(6, recipe.getServingSize());
            pstmt.setString(7, recipe.getDifficulty());
            pstmt.setString(8, recipe.getIngredients());
            pstmt.setString(9, recipe.getInstructions());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // 获取所有食谱
    public List<Recipe> selectAll() {
        final String sql = "SELECT * FROM recipes";
        List<Recipe> recipes = new ArrayList<>();
        try (Statement stmt = JDBCUtils.createStatementProxy(conn.createStatement());
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Recipe recipe = new Recipe();
                recipe.setRecipeId(rs.getInt("recipe_id"));
                recipe.setRecipeName(rs.getString("recipe_name"));
                recipe.setRecipeDescription(rs.getString("recipe_description"));
                recipe.setAuthor(rs.getString("author"));
                recipe.setPrepTime(rs.getInt("prep_time"));
                recipe.setCookingTime(rs.getInt("cooking_time"));
                recipe.setServingSize(rs.getInt("serving_size"));
                recipe.setDifficulty(rs.getString("difficulty"));
                recipe.setIngredients(rs.getString("ingredients"));
                recipe.setInstructions(rs.getString("instructions"));

                recipes.add(recipe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recipes;
    }
}
