package cool.example.plmn.ui;

import cool.example.plmn.entity.FoodValue;
import cool.example.plmn.entity.Recipe;
import cool.example.plmn.utils.StringUtils;

import javax.swing.*;
import java.awt.*;

/**
 * @author wangshuo
 * @date 2023/5/16 3:06
 */

public class RecipeOneFrame extends BaseOneFrame {

    protected JTextField nameText, descriptionText, authorText,
            prepTimeText, cookingTimeText, servingSizeText, ingredientsText, instructionsText;

    protected JComboBox<String> difficultyComboBox = new JComboBox<>(new String[]{
            "初级","中级","高级"
    });
    protected Recipe entity;

    public RecipeOneFrame(){
        init();
    }

    protected void init() {

        // center panel
        panelCenter = new JPanel();
        panelCenter.setLayout(new GridLayout(9, 2));

        JLabel nameLabel = new JLabel("名称：");
        nameLabel.setFont(new Font("楷体",Font.BOLD,16));    //修改字体样式
        nameText = new JTextField();
        panelCenter.add(nameLabel);
        panelCenter.add(nameText);

        JLabel valueLabel= new JLabel("介绍：");
        valueLabel.setFont(new Font("楷体",Font.BOLD,16));
        panelCenter.add(valueLabel);
        descriptionText = new JTextField();
        panelCenter.add(descriptionText);

        JLabel calorieJLabel = new JLabel("作者：");
        calorieJLabel.setFont(new Font("楷体",Font.BOLD,16));
        panelCenter.add(calorieJLabel);
        authorText = new JTextField();
        panelCenter.add(authorText);

        JLabel prepTimJLabel = new JLabel("准备所需的时间（分钟）：");
        prepTimJLabel.setFont(new Font("楷体",Font.BOLD,16));
        panelCenter.add(prepTimJLabel);
        prepTimeText = new JTextField();
        panelCenter.add(prepTimeText);

        JLabel cookingTimeJLabel = new JLabel("烹饪所需的时间（分钟）：");
        cookingTimeJLabel.setFont(new Font("楷体",Font.BOLD,16));
        panelCenter.add(cookingTimeJLabel);
        cookingTimeText = new JTextField();
        panelCenter.add(cookingTimeText);

        JLabel servingSizeJLabel = new JLabel("适合的人数：");
        servingSizeJLabel.setFont(new Font("楷体",Font.BOLD,16));
        panelCenter.add(servingSizeJLabel);
        servingSizeText = new JTextField();
        panelCenter.add(servingSizeText);


        JLabel difficultyTextJLabel = new JLabel("食谱的烹饪难度等级");
        difficultyTextJLabel.setFont(new Font("楷体",Font.BOLD,16));
        panelCenter.add(difficultyTextJLabel);
        panelCenter.add(difficultyComboBox);


        JLabel ingredientsLabel = new JLabel("原料：");
        ingredientsLabel.setFont(new Font("楷体",Font.BOLD,16));
        panelCenter.add(ingredientsLabel);
        ingredientsText = new JTextField();
        panelCenter.add(ingredientsText);


        JLabel instructionsJLabel = new JLabel("步骤说明：");
        instructionsJLabel.setFont(new Font("楷体",Font.BOLD,16));
        panelCenter.add(instructionsJLabel);
        instructionsText = new JTextField();
        panelCenter.add(instructionsText);

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
        showFrame();

    }


    /**
     * 获取表单中的数据
     * @return
     */
    protected Recipe entity() {
        Recipe entity = new Recipe();
        if (this.entity != null){
            entity.setId(this.entity.getId());
        }
        if (StringUtils.isNotBlank(nameText.getText())){
            entity.setRecipeName(nameText.getText());
        }
        if (StringUtils.isNotBlank(descriptionText.getText())){
            entity.setRecipeDescription(descriptionText.getText());
        }
        if (StringUtils.isNotBlank(authorText.getText())){
            entity.setAuthor(authorText.getText());
        }
        if (StringUtils.isNotBlank(prepTimeText.getText())){
            entity.setPrepTime(Integer.parseInt(prepTimeText.getText()));
        }
        if (StringUtils.isNotBlank(cookingTimeText.getText())){
            entity.setCookingTime(Integer.parseInt(cookingTimeText.getText()));
        }
        if (StringUtils.isNotBlank(servingSizeText.getText())){
            entity.setServingSize(Integer.parseInt(servingSizeText.getText()));
        }
        if (difficultyComboBox!=null && difficultyComboBox.getSelectedIndex()!=-1){
            entity.setDifficulty((String) difficultyComboBox.getSelectedItem());
        }
        if (StringUtils.isNotBlank(ingredientsText.getText())){
            entity.setIngredients(ingredientsText.getText());
        }
        if (StringUtils.isNotBlank(instructionsText.getText())){
            entity.setInstructions(instructionsText.getText());
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
