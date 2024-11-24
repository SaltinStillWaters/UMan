package com.example.View;

import javax.swing.JFrame;

import com.example.Model.Config;

public class MainFrame extends JFrame {

    public MainFrame() {
        //Frame config
        this.setTitle("U-Man: User Management Software");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(Config.isResizable);
        this.setSize(Config.frameDimension);
        this.setPreferredSize(Config.frameDimension);
        this.getContentPane().setBackground(Config.frameBG);
        this.setIconImage(Config.logo.getImage());

        //Components
        this.add(new LogoPanel());  
        this.add(new LoginPanel());

        //Others
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
    
}
