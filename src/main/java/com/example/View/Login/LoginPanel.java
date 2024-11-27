package com.example.View.Login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.example.Controller.FrameNavigator;
import com.example.Controller.SessionController;
import com.example.Model.Config;
import com.example.Model.Session;
import com.example.View.Custom.Button;
import com.example.View.Custom.PasswordField;
import com.example.View.Custom.SeperatorPanel;
import com.example.View.Custom.TextField;

public class LoginPanel extends JPanel implements ActionListener {
    TextField emailTextField;
    PasswordField passwordTextField;
    Button submitButton;
    JCheckBox rememberMeCheckBox;

    public LoginPanel() {
        this.setLayout(new FlowLayout());
        this.setBackground(Config.frameBG);

        //Login text
        JLabel login = new JLabel();
        login.setText("LOGIN");
        login.setFont(new Font("Helvetica", Font.BOLD, 40));
        login.setHorizontalAlignment(JLabel.CENTER);
        login.setForeground(new Color(0x0e3c5d));
        
        JPanel loginPanel = new JPanel(new BorderLayout());
        loginPanel.setBackground(Config.frameBG);
        loginPanel.setPreferredSize(new Dimension(Config.frameDimension.width / 2, 200));
        loginPanel.add(login, BorderLayout.CENTER);

        //Email
        emailTextField = new TextField();
        emailTextField.setLabelText("Email");
        emailTextField.setPreferredSize(new Dimension(Config.frameDimension.width / 2 - 50, 75));
        emailTextField.setText(Session.getLoginVal(0));

        //Password
        passwordTextField = new PasswordField();
        passwordTextField.setLabelText("Password");
        passwordTextField.setPreferredSize(new Dimension(Config.frameDimension.width / 2 - 50, 75));
        passwordTextField.setShowAndHide(true);
        passwordTextField.setText(Session.getLoginVal(1));

        //Submit
        submitButton = new Button();
        submitButton.setText("Submit");
        submitButton.setPreferredSize(new Dimension(100, 60));
        submitButton.setFont(getFont().deriveFont(21f));

        submitButton.addActionListener(this);

        //Remember me
        rememberMeCheckBox = new JCheckBox("Remember me");
        rememberMeCheckBox.setOpaque(false);
        rememberMeCheckBox.setFocusPainted(false);
        rememberMeCheckBox.setFont(new Font("Helvetica", Font.PLAIN, 15));
        rememberMeCheckBox.setSelected(!Session.getLoginVal(0).isEmpty());

        //Register here
        JLabel registerJLabel = new JLabel("Sign up here!");
        registerJLabel.setForeground(Color.blue);
        registerJLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        Font registerFont = new Font("Helvetica", Font.PLAIN, 15);
        Map<TextAttribute, Object> attributes = new HashMap<>(registerFont.getAttributes());
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        registerJLabel.setFont(registerFont.deriveFont(attributes));
        
        registerJLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FrameNavigator.changeFrame("SignupFrame");
            }
        });
        
        this.add(loginPanel);
        this.add(emailTextField);
        this.add(new SeperatorPanel(Config.frameBG, Config.frameDimension.width, 10));
        this.add(passwordTextField);
        this.add(rememberMeCheckBox);
        this.add(new SeperatorPanel(Config.frameBG, Config.frameDimension.width / 4 - 20, 0));
        this.add(registerJLabel);
        this.add(new SeperatorPanel(Config.frameBG, Config.frameDimension.width, 10));
        this.add(submitButton);
        this.setBounds(Config.frameDimension.width / 2, 0, Config.frameDimension.width / 2, Config.frameDimension.height);
    }

    public void reload() {
        emailTextField.setText(Session.getLoginVal(0));
        passwordTextField.setText(Session.getLoginVal(1));
        rememberMeCheckBox.setSelected(!Session.getLoginVal(0).isEmpty());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            JComponent[] components = {emailTextField, passwordTextField ,rememberMeCheckBox};
            SessionController.submitLoginHandler(components);
        }
    }
}