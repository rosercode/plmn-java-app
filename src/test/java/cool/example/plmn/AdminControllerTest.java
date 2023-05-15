package cool.example.plmn;

import cool.example.plmn.controller.AdminController;
import cool.example.plmn.controller.UserController;
import org.junit.Test;

/**
 * @author wangshuo
 * @date 2023/5/15 23:32
 */

public class AdminControllerTest {

    @Test
    public void test1() {
        new AdminController().start();
    }

}
