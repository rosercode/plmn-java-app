package cool.example.plmn.ui;

import cool.example.plmn.entity.User;
import cool.example.plmn.utils.StringUtils;

import javax.swing.*;
import java.awt.*;

/**
 * @author wangshuo
 * @date 2023/5/16 1:03
 */

public class UserOneFrame extends JFrame {

    private static final long serialVersionUID = -1984182788841566838L;
    protected JPanel panelCenter, panelSouth;
    protected JButton saveButton, exitButton;
    protected JTextField usernameText, passwordText;

    protected JComboBox<String> roleComboBox = new JComboBox<>(new String[]{
            "管理员", "普通用户"
    });
    protected User entity;

    public UserOneFrame(){
        init();
    }

    protected void init() {

        // center panel
        panelCenter = new JPanel();
        panelCenter.setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("username：");
        usernameLabel.setFont(new Font("楷体",Font.BOLD,16));    //修改字体样式
        usernameText = new JTextField();
        panelCenter.add(usernameLabel);
        panelCenter.add(usernameText);

        JLabel passwordLabel= new JLabel("password：");
        passwordLabel.setFont(new Font("楷体",Font.BOLD,16));
        panelCenter.add(passwordLabel);
        passwordText = new JTextField();
        panelCenter.add(passwordText);

        JLabel roleJLabel = new JLabel("role：");
        roleJLabel.setFont(new Font("楷体",Font.BOLD,16));
        panelCenter.add(roleJLabel);
        panelCenter.add(roleComboBox);

        // south panel
        panelSouth = new JPanel();
        panelSouth.setLayout(new GridLayout(1, 2));
        saveButton = new JButton("保存");


        panelSouth.add(saveButton);
        exitButton = new JButton("退出");
        exitButton.addActionListener(e -> {
            dispose();

        });
        panelSouth.add(exitButton);

        this.add(panelCenter, BorderLayout.CENTER);
        this.add(panelSouth, BorderLayout.SOUTH);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(470, 200, 400, 270);
        setResizable(false);
        setVisible(true);
    }


    /**
     * 获取表单中的数据
     * @return
     */
    protected User entity() {
        User entity = new User();
        if (StringUtils.isNotBlank(usernameText.getText())){
            entity.setUsername(usernameText.getText());
        }
        if (StringUtils.isNotBlank(passwordText.getText())){
            entity.setPassword(passwordText.getText());
        }
        if (roleComboBox.getSelectedIndex()!=-1){
            entity.setRole(roleComboBox.getSelectedIndex()+1);
        }
        return entity;
    }

    /**
     * 在 保存/编辑 时检查数据是否合法 <br>
     * 参数是否符合格式
     * @return
     */
    protected boolean check() {
        return true;
    }
}
