package cool.example.plmn.ui;

import cool.example.plmn.entity.WeightRecord;
import cool.example.plmn.utils.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author wangshuo
 * @date 2023/5/16 8:37
 */

public class WeightRecordOneFrame extends JFrame {


    protected JPanel panelCenter, panelSouth;
    protected JButton saveButton, exitButton;
    protected JTextField recordTimeText, weightText, noteText;

    protected WeightRecord entity;

    public WeightRecordOneFrame(){
        init();
    }

    protected void init() {

        // center panel
        panelCenter = new JPanel();
        panelCenter.setLayout(new GridLayout(3, 2));

        JLabel recordTimeLabel = new JLabel("记录时间：");
        recordTimeLabel.setFont(new Font("楷体",Font.BOLD,16));    //修改字体样式
        recordTimeText = new JTextField();
        panelCenter.add(recordTimeLabel);
        panelCenter.add(recordTimeText);

        JLabel weightLabel= new JLabel("体重：");
        weightLabel.setFont(new Font("楷体",Font.BOLD,16));
        panelCenter.add(weightLabel);
        weightText = new JTextField();
        panelCenter.add(weightText);

        JLabel noteLabel= new JLabel("备注：");
        noteLabel.setFont(new Font("楷体",Font.BOLD,16));
        panelCenter.add(noteLabel);
        noteText = new JTextField();
        panelCenter.add(noteText);

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
    protected WeightRecord entity() {
        WeightRecord entity = new WeightRecord();
        if (this.entity!=null){
            entity.setId(this.entity.getId());
            entity.setUserId(this.entity.getUserId());
        }
        if (StringUtils.isNotBlank(recordTimeText.getText())){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            entity.setRecordTime(LocalDateTime.parse(recordTimeText.getText(), formatter));
        }
        if (StringUtils.isNotBlank(weightText.getText())){
            entity.setWeight(Double.parseDouble(weightText.getText()));
        }
        if (StringUtils.isNotBlank(noteText.getText())){
            entity.setNote(noteText.getText());
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
