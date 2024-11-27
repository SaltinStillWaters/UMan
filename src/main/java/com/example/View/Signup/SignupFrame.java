package com.example.View.Signup;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.example.Model.Config;
import com.example.View.Custom.SeperatorPanel;

public class SignupFrame extends JFrame{
    public SignupFrame() {
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


        this.add(new SeperatorPanel(getForeground(), WIDTH, 20));
        this.add(signUpText);
        this.add(new InputPanel());


        //Others
        this.setVisible(false);
        this.setLocationRelativeTo(null);
    }
}
