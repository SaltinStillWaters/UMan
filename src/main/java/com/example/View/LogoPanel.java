package com.example.View;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.example.Model.Config;

public class LogoPanel extends JPanel {

    public LogoPanel() {
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(0xd6f7ff));

        JLabel bigLogo = new JLabel();
        bigLogo.setIcon(Config.bigLogo);
        bigLogo.setHorizontalAlignment(JLabel.CENTER);
        bigLogo.setVerticalAlignment(JLabel.CENTER);

        this.add(bigLogo);
        this.setBounds(0, 0, Config.frameDimension.width / 2, Config.frameDimension.height);
    }
}