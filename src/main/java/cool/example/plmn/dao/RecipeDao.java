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
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = JDBCUtils.prepareStatement(conn, sql)) {
            setRecipeParameters(pstmt, recipe);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Recipe selectById(int id) throws SQLException {
        String sql = "SELECT * FROM t_recipes WHERE id = ?";
        PreparedStatement statement = JDBCUtils.prepareStatement(conn, sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return mapResultSetToRecipe(resultSet);
        }
        return null;
    }


    // 获取所有食谱
    public List<Recipe> selectAll() throws SQLException {
        final String sql = "SELECT * FROM t_recipes";
        List<Recipe> recipes = new ArrayList<>();
        try (Statement stmt = JDBCUtils.prepareStatement(conn, sql);
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                recipes.add(mapResultSetToRecipe(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recipes;
    }

    public void updateById(Recipe recipe) throws SQLException {
        String sql = "UPDATE t_recipes SET recipe_name = ?, recipe_description = ?, author = ?, prep_time = ?," +
                " cooking_time = ?, serving_size = ?, difficulty = ?, ingredients = ?, instructions = ? " +
                "WHERE id = ?";
        PreparedStatement statement = JDBCUtils.prepareStatement(conn, sql);
        setRecipeParameters(statement, recipe);
        statement.setInt(10, recipe.getId());
        statement.executeUpdate();
    }

    private void setRecipeParameters(PreparedStatement statement, Recipe recipe) throws SQLException {
        statement.setString(1, recipe.getRecipeName());
        statement.setString(2, recipe.getRecipeDescription());
        statement.setString(3, recipe.getAuthor());
        statement.setInt(4, recipe.getPrepTime());
        statement.setInt(5, recipe.getCookingTime());
        statement.setInt(6, recipe.getServingSize());
        statement.setString(7, recipe.getDifficulty());
        statement.setString(8, recipe.getIngredients());
        statement.setString(9, recipe.getInstructions());
    }

    public void deleteById(Integer id) throws SQLException {
        String sql = "DELETE FROM t_recipes WHERE id = ?";
        try (PreparedStatement stmt = JDBCUtils.prepareStatement(conn, sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private Recipe mapResultSetToRecipe(ResultSet rs) throws SQLException {
        Recipe recipe = new Recipe();
        recipe.setId(rs.getInt("id"));
        recipe.setRecipeName(rs.getString("recipe_name"));
        recipe.setRecipeDescription(rs.getString("recipe_description"));
        recipe.setAuthor(rs.getString("author"));
        recipe.setPrepTime(rs.getInt("prep_time"));
        recipe.setCookingTime(rs.getInt("cooking_time"));
        recipe.setServingSize(rs.getInt("serving_size"));
        recipe.setDifficulty(rs.getString("difficulty"));
        recipe.setIngredients(rs.getString("ingredients"));
        recipe.setInstructions(rs.getString("instructions"));
        return recipe;
    }
}
