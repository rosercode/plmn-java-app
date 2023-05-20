package cool.example.plmn.ui;

import cool.example.plmn.utils.CommonUtils;

import javax.swing.*;

/**
 * @author wangshuo
 * @date 2023/5/20 19:37
 * 基础的 Frame，项目中的 Frame，直接或者间接继承这个类 <hr>
 * 该类扩展自JFrame类，用于创建一个基本的窗口应用程序
 * 工作：
 * 1. 设置统一的 Logo、标题
 * 2. 创建默认居中、默认显示
 */


public class BaseFrame extends JFrame {

    public BaseFrame(){
        setTitle("App");
        CommonUtils.setLogo(this, BaseFrame.class.getClassLoader().getResource("img/app.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * 添加完成组件后，设置可见性（必须在完成添加组件后在设置可见性）
     */
    public void showFrame() {
        setSize(400,400);
        setVisible(true);
    }
}
