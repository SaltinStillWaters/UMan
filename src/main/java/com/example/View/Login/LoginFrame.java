package com.example.View.Login;

import javax.swing.JFrame;

import com.example.Model.Config;
import com.example.View.LogoPanel;

public class LoginFrame extends JFrame {
    LoginPanel loginPanel;

    public LoginFrame() {
        //Frame config
        this.setTitle("U-Man: User Management Software");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(Config.isResizable);
        this.setSize(Config.frameDimension);
        this.setPreferredSize(Config.frameDimension);
        this.getContentPane().setBackground(Config.frameBG);
        this.setIconImage(Config.logo.getImage());

        loginPanel = new LoginPanel();

        //Components
        this.add(new LogoPanel());  
        this.add(loginPanel);

        //Others
        this.setVisible(false);
        this.setLocationRelativeTo(null);
    }
    
    public void reload() {
        loginPanel.reload();
    }
}
