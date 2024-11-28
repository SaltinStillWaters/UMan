package com.example.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.example.Model.Session;
import com.example.View.Custom.RoundedBorder;

public class FilledTablePanel extends JPanel {
    private JTable table;
    private JScrollPane scrollPane;
    private DefaultTableModel tableModel;

    public FilledTablePanel() {
        
        this.setLayout(null); 
        this.setPreferredSize(new Dimension(800, 600));
        
        tableModel = new DefaultTableModel(
            Arrays.copyOfRange(Session.signupKeys, 0, Session.signupKeys.length - 1), 0
        );

        
        table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(780, 500)); 
        table.setRowHeight(25); 
        table.setBackground(new Color(0xedfcff));
        
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 780, 500); 
        scrollPane.getViewport().setBackground(new Color(0xa5e9fc)); 
        scrollPane.setBorder(new RoundedBorder(15)); 

        
        this.add(scrollPane);
    }

    public void update(ArrayList<ArrayList<String>> rows) {
        
        tableModel.setRowCount(0);
        for (ArrayList<String> row : rows) {
            tableModel.addRow(row.toArray());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int width = getWidth();
        int height = getHeight();
        g2.setColor(new Color(0xcff5fe));
        g2.fillRoundRect(0, 0, width, height, 20, 20);
        g2.dispose();
    }

    @Override
    public void setOpaque(boolean isOpaque) {
        super.setOpaque(false);
    }
}
