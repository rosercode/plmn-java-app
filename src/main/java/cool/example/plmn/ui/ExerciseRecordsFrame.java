package cool.example.plmn.ui;

import cool.example.plmn.App;
import cool.example.plmn.dao.ExerciseRecordsDao;
import cool.example.plmn.entity.ExerciseRecords;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author wangshuo
 * @date 2023/5/16 16:38
 */

public class ExerciseRecordsFrame extends JFrame {

    private JTable table;
    private JButton addButton, deleteButton, updateButton, flashButton;

    private Integer selectedId;
    private ExerciseRecordsDao exerciseRecordsDao = ExerciseRecordsDao.getInstance();

    public Boolean isClosed = false;

    String[] columnNames = {"ID", "运动类型", "强度级别", "消耗的卡路里值", "开始时间", "结束时间"};

    public ExerciseRecordsFrame() {
        setTitle("运动消耗记录");
        setLayout(new BorderLayout());

        tableInit();
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
                JOptionPane.showMessageDialog(ExerciseRecordsFrame.this,
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
                JOptionPane.showMessageDialog(ExerciseRecordsFrame.this,
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

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                isClosed = true;
            }
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void tableInit() {
        // 创建表格模型和数据
        DefaultTableModel model = new DefaultTableModel(getTableData(), columnNames);
        table = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // 创建表格选择模型并添加行选择监听器
        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        selectedId = (Integer) table.getValueAt(selectedRow, 0);
                    }
                }
            }
        });
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

    public void loop() {
        while (true) {
            if (isClosed) {
                break;
            }
            try {
                Thread.sleep(1 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
