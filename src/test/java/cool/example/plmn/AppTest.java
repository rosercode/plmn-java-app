package cool.example.plmn;

import cool.example.plmn.dao.UserDao;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void test1() throws SQLException {
        System.out.println(UserDao.getInstance().selectAll());
    }
}
