package com.example.View;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.example.Model.Session;

public class FilledTablePanel extends JPanel {
    private ArrayList<ArrayList<String>> rows;
    private JTable table;
    private JScrollPane scrollPane;
    private DefaultTableModel tableModel;

    public FilledTablePanel() {
        tableModel = new DefaultTableModel(Arrays.copyOfRange(Session.signupKeys, 0, Session.signupKeys.length - 1), 0);
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);

        this.add(scrollPane);
    }

    public void update(ArrayList<ArrayList<String>> rows) {
        tableModel.setRowCount(0);
        for (ArrayList<String> row : rows) {
            tableModel.addRow(row.toArray());
        }
    }
}
