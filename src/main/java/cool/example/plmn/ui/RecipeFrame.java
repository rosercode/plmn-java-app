package cool.example.plmn.ui;

import cool.example.plmn.dao.RecipeDao;
import cool.example.plmn.entity.Recipe;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * @author wangshuo
 * @date 2023/5/16 3:06
 */

public class RecipeFrame extends JFrame{

    private JTable table;
    private JButton addButton, deleteButton, updateButton, flashButton, detailsButton;

    private Integer selectedId;
    private RecipeDao recipeDao = RecipeDao.getInstance();

    String[] columnNames = {"ID", "食谱名称", "作者", "食谱适合的人数", "烹饪难度等级"};

    public RecipeFrame() {
        setTitle("食谱表");
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
        detailsButton = new JButton("Details");


        // 添加按钮点击事件监听器
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RecipeAddFrame();
            }
        });

        // 处理删除按钮点击事件
        deleteButton.addActionListener(e -> {
            if (selectedId == null || selectedId == -1) {
                JOptionPane.showMessageDialog(RecipeFrame.this,
                        "请选择行", "Fail", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                recipeDao.deleteById(selectedId);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        // 处理更新按钮点击事件
        updateButton.addActionListener(e -> {
            if (selectedId == null || selectedId == -1) {
                JOptionPane.showMessageDialog(RecipeFrame.this,
                        "请选择行", "Fail", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                new RecipeUpdateFrame(recipeDao.selectById(selectedId));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        flashButton.addActionListener(e -> {
            updateTableData(getTableData());
        });

        detailsButton.addActionListener(e -> {
            if (selectedId == null || selectedId == -1) {
                JOptionPane.showMessageDialog(RecipeFrame.this,
                        "请选择行", "Fail", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                new RecipeDetailFrame(recipeDao.selectById(selectedId));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        // 将按钮添加到面板
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(flashButton);
        buttonPanel.add(detailsButton);

        // 将按钮面板添加到窗口底部
        add(buttonPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        java.util.List<Recipe> entities = null;
        entities = recipeDao.selectAll();
        assert entities != null;
        Object[][] data = new Object[entities.size()][];
        for (int i = 0; i < entities.size(); i++) {
            Recipe entity = entities.get(i);
            Object[] objects = new Object[]{
                    entity.getId(), entity.getRecipeName(), entity.getAuthor(), entity.getServingSize(), entity.getDifficulty()
            };
            data[i] = objects;
        }
        return data;
    }

}


