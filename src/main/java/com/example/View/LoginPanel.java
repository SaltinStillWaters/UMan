package com.example.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.example.Model.Config;
import com.example.View.Custom.Button;
import com.example.View.Custom.PasswordField;
import com.example.View.Custom.SeperatorPanel;
import com.example.View.Custom.TextField;

public class LoginPanel extends JPanel {

    public LoginPanel() {
        this.setLayout(new FlowLayout());
        this.setBackground(new Color(0xedfcff));

        //Login text
        JLabel login = new JLabel();
        login.setText("LOGIN");
        login.setFont(new Font("Helvetica", Font.BOLD, 40));
        login.setHorizontalAlignment(JLabel.CENTER);
        login.setForeground(new Color(0x0e3c5d));
        
        JPanel loginPanel = new JPanel(new BorderLayout());
        loginPanel.setBackground(new Color(0xedfcff));
        loginPanel.setPreferredSize(new Dimension(Config.frameDimension.width / 2, 200));
        loginPanel.add(login, BorderLayout.CENTER);

        //Email
        TextField emailTextField = new TextField();
        emailTextField.setLabelText("Email");
        emailTextField.setPreferredSize(new Dimension(Config.frameDimension.width / 2 - 50, 75));

        //Password
        PasswordField passwordTextField = new PasswordField();
        passwordTextField.setLabelText("Password");
        passwordTextField.setPreferredSize(new Dimension(Config.frameDimension.width / 2 - 50, 75));
        passwordTextField.setShowAndHide(true);

        //Submit
        Button submitButton = new Button();
        submitButton.setText("Submit");
        submitButton.setPreferredSize(new Dimension(100, 60));
        submitButton.setFont(getFont().deriveFont(21f));


        this.add(loginPanel);
        this.add(emailTextField);
        this.add(new SeperatorPanel(0xedfcff, Config.frameDimension.width, 300));
        this.add(passwordTextField);
        this.add(new SeperatorPanel(0xedfcff, Config.frameDimension.width, 10));
        this.add(submitButton);
        this.setBounds(Config.frameDimension.width / 2, 0, Config.frameDimension.width / 2, Config.frameDimension.height);
    }
}