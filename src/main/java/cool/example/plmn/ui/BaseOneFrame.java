package cool.example.plmn.ui;

import javax.swing.*;

/**
 * @author wangshuo
 * @date 2023/5/20 19:38
 */

public class BaseOneFrame extends BaseFrame{

    protected JPanel panelCenter, panelSouth;
    protected JButton saveButton, exitButton;

    public BaseOneFrame() {
    
    }

    @Override
    public void showFrame() {
        setBounds(470, 200, 400, 270);
        setResizable(false);
        setVisible(true);
    }
}
