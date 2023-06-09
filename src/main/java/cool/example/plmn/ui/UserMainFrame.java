package cool.example.plmn.ui;

import cool.example.plmn.dao.UserDao;
import cool.example.plmn.entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

/**
 * @author wangshuo
 * @date 2023/5/16 1:03
 */

public class UserMainFrame extends BaseMainFrame {

    private JButton addButton, deleteButton, updateButton, flashButton;

    private UserDao userDao = UserDao.getInstance();

    String[] columnNames = {"ID", "username", "password", "role"};

    public UserMainFrame() {
        setTitle("用户管理");
        initTable();
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // 创建按钮面板
        JPanel buttonPanel = new JPanel();

        addButton = new JButton("Add");
        deleteButton = new JButton("Delete");
        updateButton = new JButton("Update");
        flashButton = new JButton("Flash");

        // 添加按钮点击事件监听器
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserAddFrame();
            }
        });

        // 处理删除按钮点击事件
        deleteButton.addActionListener(e -> {
            if (selectedId == null || selectedId == -1) {
                JOptionPane.showMessageDialog(UserMainFrame.this,
                        "请选择行", "Fail", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                userDao.deleteUserById(selectedId);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        // 处理更新按钮点击事件
        updateButton.addActionListener(e -> {
            if (selectedId == null || selectedId == -1) {
                JOptionPane.showMessageDialog(UserMainFrame.this,
                        "请选择行", "Fail", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                new UserUpdateFrame(userDao.getUserById(selectedId));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        flashButton.addActionListener(e -> {
            updateTableData(getTableData());
        });

        // 将按钮添加到面板
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(flashButton);

        // 将按钮面板添加到窗口底部
        add(buttonPanel, BorderLayout.SOUTH);

        showFrame();
    }

    public void initTable() {
        // 创建表格模型和数据
        DefaultTableModel model = new DefaultTableModel(getTableData(), columnNames);
        table.setModel(model);
    }

    // 更新表格内容
    private void updateTableData(Object[][] data) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setDataVector(data, columnNames);
    }


    public Object[][] getTableData() {
        java.util.List<User> userList = null;
        try {
            userList = userDao.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assert userList != null;
        Object[][] data = new Object[userList.size()][];
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            String role = user.getRole() == 1 ? "管理员" : (user.getRole() == 2 ? "普通用户" : "错误");
            Object[] objects = new Object[]{
                    user.getId(), user.getUsername(), user.getPassword(), role
            };
            data[i] = objects;
        }
        return data;
    }
}
