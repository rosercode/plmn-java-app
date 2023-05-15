package cool.example.plmn.ui;

import cool.example.plmn.dao.UserDao;

import javax.swing.*;
import java.sql.SQLException;

/**
 * @author wangshuo
 * @date 2023/5/16 1:03
 */

public class UserAddFrame extends UserOneFrame{

    public UserAddFrame() {
        setTitle("添加用户");
        saveButton.addActionListener(e -> {
            try {
                UserDao.getInstance().insertUser(entity());
                JOptionPane.showMessageDialog(UserAddFrame.this,
                        "用户添加成功", "注册", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

    }
}
