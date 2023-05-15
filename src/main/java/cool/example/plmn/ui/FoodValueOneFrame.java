package cool.example.plmn.ui;

import cool.example.plmn.entity.FoodValue;
import cool.example.plmn.utils.StringUtils;

import javax.swing.*;
import java.awt.*;

/**
 * @author wangshuo
 * @date 2023/5/16 2:44
 */

public class FoodValueOneFrame extends JFrame {

    protected JPanel panelCenter, panelSouth;
    protected JButton saveButton, exitButton;
    protected JTextField nameText, valueText, calorieText;

    protected FoodValue entity;

    public FoodValueOneFrame(){
        init();
    }

    protected void init() {

        // center panel
        panelCenter = new JPanel();
        panelCenter.setLayout(new GridLayout(3, 2));

        JLabel nameLabel = new JLabel("name：");
        nameLabel.setFont(new Font("楷体",Font.BOLD,16));    //修改字体样式
        nameText = new JTextField();
        panelCenter.add(nameLabel);
        panelCenter.add(nameText);

        JLabel valueLabel= new JLabel("value：");
        valueLabel.setFont(new Font("楷体",Font.BOLD,16));
        panelCenter.add(valueLabel);
        valueText = new JTextField();
        panelCenter.add(valueText);

        JLabel calorieJLabel = new JLabel("热量含量：");
        calorieJLabel.setFont(new Font("楷体",Font.BOLD,16));
        panelCenter.add(calorieJLabel);
        calorieText = new JTextField();
        panelCenter.add(calorieText);

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
    protected FoodValue entity() {
        FoodValue entity = new FoodValue();
        if (StringUtils.isNotBlank(nameText.getText())){
            entity.setName(nameText.getText());
        }
        if (StringUtils.isNotBlank(valueText.getText())){
            entity.setNutritionValue(valueText.getText());
        }
        if (StringUtils.isNotBlank(calorieText.getText())){
            entity.setCalorie(Integer.parseInt(calorieText.getText()));
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
