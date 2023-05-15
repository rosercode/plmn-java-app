package cool.example.plmn.jdbc.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * @author wangshuo
 * @date 2023/5/15 17:55
 */

public class StatementProxy implements InvocationHandler {
    private Statement targetStatement;
    private PreparedStatement preparedTargetStatement;

    public StatementProxy(Statement targetStatement) {
        this.targetStatement = targetStatement;
    }

    public StatementProxy(PreparedStatement preparedTargetStatement) {
        this.preparedTargetStatement = preparedTargetStatement;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (targetStatement!=null){
            if (method.getName().equals("executeQuery") || method.getName().equals("executeUpdate")) {
                // 在执行查询之前打印 SQL 语句
                String sql = (String) args[0];
                System.out.println("Executing SQL query: " + sql);
            }
            // 调用目标 Statement 对象的方法
            return method.invoke(targetStatement, args);
        }
        if (preparedTargetStatement!=null){
            if (method.getName().equals("executeQuery") || method.getName().equals("executeUpdate")) {
                // 在执行查询之前打印 SQL 语句
                String sql = preparedTargetStatement.toString(); // 获取完整的 SQL 语句
                System.out.println("Executing SQL query: " + sql);
            }
            return method.invoke(preparedTargetStatement, args);
        }
        return null;
    }
}
