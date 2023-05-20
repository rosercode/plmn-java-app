package cool.example.plmn.ui;

import cool.example.plmn.App;
import cool.example.plmn.dao.ExerciseRecordsDao;
import cool.example.plmn.entity.ExerciseRecords;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/**
 * @author wangshuo
 * @date 2023/5/16 16:38
 */

public class ExerciseRecordsMainFrame extends BaseMainFrame {

    private JButton addButton, deleteButton, updateButton, flashButton;

    private ExerciseRecordsDao exerciseRecordsDao = ExerciseRecordsDao.getInstance();

    String[] columnNames = {"ID", "运动类型", "强度级别", "消耗的卡路里值", "开始时间", "结束时间"};

    public ExerciseRecordsMainFrame() {
        setTitle("运动消耗记录");

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
                new ExerciseRecordsAddFrame();
            }
        });

        // 处理删除按钮点击事件
        deleteButton.addActionListener(e -> {
            if (selectedId == null || selectedId == -1) {
                JOptionPane.showMessageDialog(ExerciseRecordsMainFrame.this,
                        "请选择行", "Fail", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                exerciseRecordsDao.deleteExerciseLog(selectedId);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        // 处理更新按钮点击事件
        updateButton.addActionListener(e -> {
            if (selectedId == null || selectedId == -1) {
                JOptionPane.showMessageDialog(ExerciseRecordsMainFrame.this,
                        "请选择行", "Fail", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                new ExerciseRecordsUpdateFrame(exerciseRecordsDao.selectExerciseRecordById(selectedId));
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
        java.util.List<ExerciseRecords> entities = null;
        try {
            entities = exerciseRecordsDao.selectAllByUserId(App.currentUser.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assert entities != null;
        Object[][] data = new Object[entities.size()][];
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < entities.size(); i++) {
            ExerciseRecords entity = entities.get(i);
            Object[] objects = new Object[0];
            objects = new Object[]{
                    entity.getId(), entity.getExerciseType(), entity.getExerciseIntensity(), entity.getCaloriesBurned(),
                    format.format(entity.getStartTime()), format.format(entity.getEndTime())
            };
            data[i] = objects;
        }
        return data;
    }
}
