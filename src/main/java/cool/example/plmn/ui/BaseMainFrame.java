package cool.example.plmn.ui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author wangshuo
 * @date 2023/5/20 19:37
 * 所有主窗口的基类，预定义了一些共同的行为 <hr>
 * 1. 定义一个统一的 loop 方法
 * 2. 定义共同的组件
 */

public class BaseMainFrame extends BaseFrame{

    protected JTable table;
    public Boolean isClosed = false;

    protected Integer selectedId;

    public BaseMainFrame() {
        // 默认布局
        setLayout(new BorderLayout());

        // 设置表格不能编辑
        table = new JTable() {
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

        // 当窗口正在关闭时，将一个名为 isClosed 的变量设置为 true
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                isClosed = true;
            }
        });

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
