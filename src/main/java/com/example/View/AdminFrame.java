package com.example.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.example.Model.Config;

public class AdminFrame extends JFrame {
    private FilledTablePanel filledTablePanel;

    public AdminFrame() {
        //Frame config
        this.setTitle("U-Man: User Management Software");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(Config.isResizable);
        this.setSize(Config.frameDimension);
        this.setPreferredSize(Config.frameDimension);
        this.getContentPane().setBackground(new Color(0xedfcff));
        this.setIconImage(Config.logo.getImage());
        this.setLayout(new FlowLayout());
        

        //Registration text
        JLabel signUpText = new JLabel();
        signUpText.setText("SIGN UP");
        signUpText.setFont(new Font("Helvetica", Font.BOLD, 40));
        signUpText.setHorizontalAlignment(JLabel.CENTER);
        signUpText.setForeground(new Color(0x0e3c5d));
        signUpText.setPreferredSize(new Dimension(Config.frameDimension.width, signUpText.getPreferredSize().height));


        filledTablePanel = new FilledTablePanel();
        this.add(filledTablePanel);

        //Others
        this.setVisible(false);
        this.setLocationRelativeTo(null);
    }

    public void updateFilledTable(ArrayList<ArrayList<String>> rows) {
        filledTablePanel.update(rows);
    }
}
