package cool.example.plmn.ui;

import cool.example.plmn.App;
import cool.example.plmn.dao.UserDao;
import cool.example.plmn.entity.User;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

import static cool.example.plmn.utils.CommonUtils.setLogo;

/**
 * @author wangshuo
 * @date 2023/5/7 16:47
 */

public class LoginFrame extends JFrame {

    private JLabel titleLabel, userLabel, passLabel;
    private JTextField userTextField;
    private JPasswordField passField;
    private JButton registerBtn, loginBtn;
    private JComboBox<String> roleComboBox;

    public Boolean isClosed = false;

    public LoginFrame() {
        // 设置窗口标题
        super("登录");

        // 设置Logo
        setLogo(this, LoginFrame.class.getClassLoader().getResource("img/cat.png"));

        // 创建组件
        titleLabel = new JLabel("app", SwingConstants.CENTER);
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 36));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0));
        userLabel = new JLabel("用户名：");
        userLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        passLabel = new JLabel("密    码：");
        passLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        roleComboBox = new JComboBox<>(new String[]{
                "管理员", "普通用户"
        });
        userTextField = new JTextField(20);
        userTextField.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        passField = new JPasswordField(20);
        passField.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        loginBtn = new JButton("登录");
        loginBtn.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        registerBtn = new JButton("点我注册");
        registerBtn.setFont(new Font("微软雅黑", Font.PLAIN, 16));


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
        add(passLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(passField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(roleComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(loginBtn, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(new JSeparator(SwingConstants.HORIZONTAL), gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        add(registerBtn, gbc);


        // 添加“如果没有账号，点击注册”按钮
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        JButton registerHintBtn = new JButton("如果没有账号，点击注册");
        registerHintBtn.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        registerHintBtn.setForeground(Color.BLUE);
        registerHintBtn.setBorderPainted(false);
        registerHintBtn.setContentAreaFilled(false);
        registerHintBtn.setFocusPainted(false);

        registerHintBtn.addActionListener(e -> {
            this.dispose();
            new RegisterFrame();
        });


        // 注册事件监听器
        loginBtn.addActionListener(e -> {
            // 获取用户名、密码
            String username = userTextField.getText();
            String password = new String(passField.getPassword());
            int role = roleComboBox.getSelectedIndex()+1;

            // 判断用户名是否为空
            if (username==null || username.equals("")){
                JOptionPane.showMessageDialog(LoginFrame.this,
                        "用户名不能为空", "注册失败", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 判断密码是否为空
            if (password.equals("")){
                JOptionPane.showMessageDialog(LoginFrame.this,
                        "密码不能为空", "注册失败", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 获取所有用户
            User user = null;
            try {
                user = UserDao.getInstance().selectUserByAll(username, password, role);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            if (user!=null) {
                JOptionPane.showMessageDialog(LoginFrame.this, "登录成功！");
                LoginFrame.this.setVisible(false);
                LoginFrame.this.dispose();
                isClosed = true;
                App.currentUser = user;
                return;
            }
            JOptionPane.showMessageDialog(LoginFrame.this,
                    "用户名、密码或身份错误，请重试！", "登录失败", JOptionPane.ERROR_MESSAGE);

        });

        // 添加注册按钮的监听器
        registerBtn.addActionListener(e -> {
            this.dispose();
            new RegisterFrame();
        });

        add(registerHintBtn, gbc);
        // note： setLocationRelativeTo 要在 setSize 之后
        setSize(600, 500);
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