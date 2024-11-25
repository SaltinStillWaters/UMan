package com.example.View.Custom;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class SeperatorPanel extends JPanel {
    public SeperatorPanel(Color bgColor, int width, int height) {
        this.setLayout(null);
        this.setBackground(bgColor);
        this.setPreferredSize(new Dimension(width, height));
    }
}
