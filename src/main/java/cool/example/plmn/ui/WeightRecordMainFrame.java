package cool.example.plmn.ui;

import cool.example.plmn.App;
import cool.example.plmn.dao.WeightRecordDao;
import cool.example.plmn.entity.WeightRecord;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

/**
 * @author wangshuo
 * @date 2023/5/16 5:11
 */

public class WeightRecordMainFrame extends BaseMainFrame {
   
    private JButton addButton, deleteButton, updateButton, flashButton;
    
    private WeightRecordDao weightRecordDao = WeightRecordDao.getInstance();

    String[] columnNames = {"ID", "记录时间", "体重", "备注"};

    public WeightRecordMainFrame() {
        setTitle("体重记录信息");
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
                new WeightRecordAddFrame();
            }
        });

        // 处理删除按钮点击事件
        deleteButton.addActionListener(e -> {
            if (selectedId == null || selectedId == -1) {
                JOptionPane.showMessageDialog(WeightRecordMainFrame.this,
                        "请选择行", "Fail", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                weightRecordDao.deleteById(selectedId);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        // 处理更新按钮点击事件
        updateButton.addActionListener(e -> {
            if (selectedId == null || selectedId == -1) {
                JOptionPane.showMessageDialog(WeightRecordMainFrame.this,
                        "请选择行", "Fail", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                new WeightRecordUpdateFrame(weightRecordDao.selectById(selectedId));
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
        java.util.List<WeightRecord> entities = null;
        try {
            entities = weightRecordDao.selectWeightRecordsByUserId(App.currentUser.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assert entities != null;
        Object[][] data = new Object[entities.size()][];
        for (int i = 0; i < entities.size(); i++) {
            WeightRecord entity = entities.get(i);
            Object[] objects = new Object[]{
                    entity.getId(), entity.getRecordTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), entity.getWeight(), entity.getNote()
            };
            data[i] = objects;
        }
        return data;
    }
}
