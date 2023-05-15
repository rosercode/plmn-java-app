package cool.example.plmn.ui;


import cool.example.plmn.dao.UserDao;
import cool.example.plmn.entity.User;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

import static cool.example.plmn.utils.CommonUtils.setLogo;

/**
 * @author wangshuo
 * @date 2023/5/7 16:48
 */

public class RegisterFrame extends JFrame {

    private JLabel titleLabel, userLabel, passLabel1, passLabel2;
    private JTextField userTextField;
    private JPasswordField passField1, passField2;
    private JButton registerBtn, loginBtn;
    private JComboBox<String> roleComboBox;

    public Boolean isClosed = false;

    public RegisterFrame() {
        // 设置窗口标题
        super("注册");

        // 设置Logo
        setLogo(this, RegisterFrame.class.getClassLoader().getResource("img/cat.png"));

        // 创建组件
        titleLabel = new JLabel("app", SwingConstants.CENTER);
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 36));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0));
        userLabel = new JLabel("用户名：");
        userLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));

        userTextField = new JTextField(20);
        userTextField.setFont(new Font("微软雅黑", Font.PLAIN, 18));

        passLabel1 = new JLabel("密    码：");
        passLabel1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        passField1 = new JPasswordField(20);
        passField1.setFont(new Font("微软雅黑", Font.PLAIN, 18));

        passLabel2 = new JLabel("密    码：");
        passLabel2.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        passField2 = new JPasswordField(20);
        passField2.setFont(new Font("微软雅黑", Font.PLAIN, 18));

        roleComboBox = new JComboBox<>(new String[]{
                "管理员", "普通用户"
        });

        registerBtn = new JButton("注册");
        registerBtn.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        loginBtn = new JButton("点我登录");
        loginBtn.setFont(new Font("微软雅黑", Font.PLAIN, 16));

        // 设置布局
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // 添加组件
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(userLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(userTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(passLabel1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(passField1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(passLabel2, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(passField2, gbc);


        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(roleComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(registerBtn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        add(new JSeparator(SwingConstants.HORIZONTAL), gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        add(loginBtn, gbc);


        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        JButton loginHintBtn = new JButton("已经有账号，点击登录");
        loginHintBtn.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        loginHintBtn.setForeground(Color.BLUE);
        loginHintBtn.setBorderPainted(false);
        loginHintBtn.setContentAreaFilled(false);
        loginHintBtn.setFocusPainted(false);

        loginHintBtn.addActionListener(e -> {
            this.dispose();
            new LoginFrame();
        });


        // 添加注册按钮的监听器
        registerBtn.addActionListener(e -> {

            // 获取用户名、密码、身份
            String username = userTextField.getText();
            String password1 = new String(passField1.getPassword());
            String password2 = new String(passField2.getPassword());
            int role = roleComboBox.getSelectedIndex()+1;

            if (username==null || username.equals("")){
                JOptionPane.showMessageDialog(RegisterFrame.this,
                        "用户名不能为空", "注册失败", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (password1.equals("") || password2.equals("")){
                JOptionPane.showMessageDialog(RegisterFrame.this,
                        "密码不能为空", "注册失败", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!password1.equals(password2)){
                JOptionPane.showMessageDialog(RegisterFrame.this,
                        "两次密码不一致", "注册失败", JOptionPane.ERROR_MESSAGE);
                return;
            }
            User entity = new User(username, password1, role);
            try {
                UserDao.getInstance().insertUser(entity);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(RegisterFrame.this,
                    "注册成功", "注册成功", JOptionPane.INFORMATION_MESSAGE);


        });

        // 注册事件监听器
        loginBtn.addActionListener(e -> {
            this.dispose();
            new LoginFrame();
        });
        add(loginHintBtn, gbc);
        setSize(650, 550);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void loop(){
        while (true){
            if (isClosed){
                break;
            }
            try {
                Thread.sleep(1*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}