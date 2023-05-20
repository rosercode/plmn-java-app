package cool.example.plmn.utils;

import cool.example.plmn.jdbc.proxy.StatementProxy;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Proxy;
import java.sql.*;
import java.util.Properties;

/**
 * @author wangshuo
 * @date 2023/5/7 16:31
 * 数据库工具类，提供相关的工具方法
 */


public class JDBCUtils {

    private static Properties props = new Properties();
    private static String driver;
    private static String connUrl;
    private static String username;
    private static String password;



    static {
        try {
            InputStream in = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            props.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver = props.getProperty("jdbc.driverClassName");
        connUrl = props.getProperty("jdbc.url");
        username = props.getProperty("jdbc.username");
        password = props.getProperty("jdbc.password");
    }

    /**
     * 获取和数据库的连接
     * @return
     */
    public static Connection getConnection(){
        Connection connection = null;

        try  {
            Class.forName(driver);
            connection = DriverManager.getConnection(connUrl, username, password);
            System.out.println("Mysql is running.");
        } catch (SQLException e) {
            System.out.println("Mysql is not running.");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // 创建代理的 Statement 对象
    public static Statement createStatementProxy(Statement targetStatement) {
        Statement proxy = (Statement) Proxy.newProxyInstance(
                targetStatement.getClass().getClassLoader(),
                targetStatement.getClass().getInterfaces(),
                new StatementProxy(targetStatement));
        return proxy;
    }

    public static PreparedStatement createStatementProxy(PreparedStatement targetStatement) {
        PreparedStatement proxy = (PreparedStatement) Proxy.newProxyInstance(
                targetStatement.getClass().getClassLoader(),
                targetStatement.getClass().getInterfaces(),
                new StatementProxy(targetStatement));

        return proxy;
    }

    public static PreparedStatement prepareStatement(Connection conn, String sql) throws SQLException {
        return createStatementProxy(conn.prepareStatement(sql));
    }
}

