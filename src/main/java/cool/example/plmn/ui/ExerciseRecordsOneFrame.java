package cool.example.plmn.ui;

import cool.example.plmn.entity.ExerciseRecords;
import cool.example.plmn.utils.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author wangshuo
 * @date 2023/5/16 16:38
 */

public class ExerciseRecordsOneFrame extends JFrame {


    protected JPanel panelCenter, panelSouth;
    protected JButton saveButton, exitButton;
    protected JTextField typeText, intensityText, caloriesText, startTimeText, endTimeText;

    protected ExerciseRecords entity;

    public ExerciseRecordsOneFrame(){
        init();
    }

    protected void init() {

        // center panel
        panelCenter = new JPanel();
        panelCenter.setLayout(new GridLayout(5, 2));

        JLabel typeLabel = new JLabel("运动类型：");
        typeLabel.setFont(new Font("楷体",Font.BOLD,16));    //修改字体样式
        typeText = new JTextField();
        panelCenter.add(typeLabel);
        panelCenter.add(typeText);

        JLabel intensityLabel= new JLabel("强度级别：");
        intensityLabel.setFont(new Font("楷体",Font.BOLD,16));
        panelCenter.add(intensityLabel);
        intensityText = new JTextField();
        panelCenter.add(intensityText);

        JLabel caloriesLabel= new JLabel("消耗的卡路里值：");
        caloriesLabel.setFont(new Font("楷体",Font.BOLD,16));
        panelCenter.add(caloriesLabel);
        caloriesText = new JTextField();
        panelCenter.add(caloriesText);

        JLabel startTimeLabel= new JLabel("开始时间：");
        startTimeLabel.setFont(new Font("楷体",Font.BOLD,16));
        panelCenter.add(startTimeLabel);
        startTimeText = new JTextField();
        panelCenter.add(startTimeText);

        JLabel endTimeLabel= new JLabel("结束时间：");
        endTimeLabel.setFont(new Font("楷体",Font.BOLD,16));
        panelCenter.add(endTimeLabel);
        endTimeText = new JTextField();
        panelCenter.add(endTimeText);

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
    protected ExerciseRecords entity() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (entity == null){
            entity = new ExerciseRecords();
        }
        if (StringUtils.isNotBlank(typeText.getText())) {
            entity.setExerciseType(typeText.getText());
        }
        if (StringUtils.isNotBlank(intensityText.getText())) {
            entity.setExerciseIntensity(intensityText.getText());
        }
        if (StringUtils.isNotBlank(caloriesText.getText())) {
            entity.setCaloriesBurned(Float.parseFloat(caloriesText.getText()));
        }
        if (StringUtils.isNotBlank(startTimeText.getText())) {
            try {
                entity.setStartTime(format.parse(startTimeText.getText()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (StringUtils.isNotBlank(endTimeText.getText())) {
            try {
                entity.setEndTime(format.parse(endTimeText.getText()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
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
