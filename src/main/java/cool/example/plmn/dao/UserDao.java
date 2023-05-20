package cool.example.plmn.dao;

import cool.example.plmn.entity.User;
import cool.example.plmn.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangshuo
 * @date 2023/5/15 16:28
 */
public class UserDao {

    private volatile static UserDao instance;
    public Connection conn = JDBCUtils.getConnection();


    public static UserDao getInstance() {
        if (instance == null) {
            synchronized (UserDao.class) {
                if (instance == null) {
                    instance = new UserDao();
                }
            }
        }
        return instance;
    }


    // 插入新用户
    public void insertUser(User entity) throws SQLException {
        final String sql = "INSERT INTO t_users (username, password, role) VALUES (?, ?, ?)";
        PreparedStatement stmt = JDBCUtils.prepareStatement(conn, sql);
        stmt.setString(1, entity.getUsername());
        stmt.setString(2, entity.getPassword());
        stmt.setInt(3, entity.getRole());
        stmt.executeUpdate();
    }

    // 获取所有用户列表
    public List<User> selectAll() throws SQLException {
        final String sql = "SELECT * FROM t_users";
        List<User> users = new ArrayList<>();
        PreparedStatement stmt = JDBCUtils.prepareStatement(conn, sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String username = rs.getString("username");
            String password = rs.getString("password");
            int role = rs.getInt("role");
            User user = new User(id, username, password, role);
            users.add(user);
        }
        return users;
    }

    /**
     * 通过用户名、密码、角色获取
     * @return
     * @throws SQLException
     */
    public User selectUserByAll(String queryUsername, String queryPassword, Integer queryRole) throws SQLException {
        final String sql = "SELECT * FROM t_users WHERE username = ? and password = ? and role = ?";
        User user = null;
        try (PreparedStatement stmt = JDBCUtils.prepareStatement(conn, sql)) {
            stmt.setString(1, queryUsername);
            stmt.setString(2, queryPassword);
            stmt.setInt(3, queryRole);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = mapResultSetToUser(rs);
                }
            }
        }
        return user;
    }

    // 根据 ID 查找用户
    public User getUserById(int id) throws SQLException {
        final String sql = "SELECT * FROM t_users WHERE id = ?";
        User user = null;
        try (PreparedStatement stmt = JDBCUtils.prepareStatement(conn, sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    int role = rs.getInt("role");
                    user = new User(id, username, password, role);
                }
            }
        }
        return user;
    }

    // 根据 ID 删除用户
    public void deleteUserById(int id) throws SQLException {
        final String sql = "DELETE FROM t_users WHERE id = ?";
        try (PreparedStatement stmt = JDBCUtils.prepareStatement(conn, sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // 更新用户信息
    public void update(User user) throws SQLException {
        final String sql = "UPDATE t_users SET username = ?, password = ?, role = ? WHERE id = ?";
        try (PreparedStatement stmt = JDBCUtils.prepareStatement(conn, sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setInt(3, user.getRole());
            stmt.setInt(4, user.getId());
            stmt.executeUpdate();
        }
    }

    // 将查询结果集映射到 User 对象
    // todo 这里考虑基于反射来封装成一个通用的方法
    private User mapResultSetToUser(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        String username = rs.getString("username");
        String password = rs.getString("password");
        int role = rs.getInt("role");
        return  new User(id, username, password, role);
    }
}

