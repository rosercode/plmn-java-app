package cool.example.plmn.ui;

import cool.example.plmn.dao.UserDao;
import cool.example.plmn.entity.User;

import java.sql.SQLException;

/**
 * @author wangshuo
 * @date 2023/5/16 1:03
 */

public class UserUpdateFrame extends UserOneFrame{

    public UserUpdateFrame(User entity) {
        setTitle("编辑用户");
        usernameText.setText(entity.getUsername());
        passwordText.setText(entity.getPassword());
        roleComboBox.setSelectedIndex(entity.getRole()-1);
        saveButton.addActionListener(e -> {
            entity.setUsername(usernameText.getText());
            entity.setPassword(passwordText.getText());
            entity.setRole(roleComboBox.getSelectedIndex()+1);
            try {
                UserDao.getInstance().update(entity);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }
}
