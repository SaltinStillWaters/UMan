package com.example.View;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.example.Controller.FrameNavigator;
import com.example.Controller.SessionController;
import com.example.Model.Config;
import com.example.Model.Session;
import com.example.View.Custom.Button;
import com.example.View.Custom.PasswordField;
import com.example.View.Custom.TextField;

public class InputPanel extends JPanel implements ActionListener {
    TextField firstNameText;
    TextField lastNameText;
    TextField emailText;
    TextField ageText;
    TextField birthdayText;
    PasswordField passText;
    PasswordField confirmPassText;
    Button submitButton;

    public InputPanel() {
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 15));
        this.setPreferredSize(new Dimension(Config.frameDimension.width * 3 / 5, Config.frameDimension.height * 7 / 10));
        this.setBackground(new Color(0xa5e9fc));

        int widthBorder = 30;

        //First Name
        firstNameText = new TextField();
        firstNameText.setLabelText("First Name");
        firstNameText.setPreferredSize(new Dimension(this.getPreferredSize().width / 2 - widthBorder / 2, firstNameText.getPreferredSize().height));

        //Last Name
        lastNameText = new TextField();
        lastNameText.setLabelText("Last Name");
        lastNameText.setPreferredSize(new Dimension(this.getPreferredSize().width / 2 - widthBorder / 2, lastNameText.getPreferredSize().height));

        //Email
        emailText = new TextField();
        emailText.setLabelText("Email");
        emailText.setPreferredSize(new Dimension(this.getPreferredSize().width - widthBorder, lastNameText.getPreferredSize().height));
        
        //age
        ageText = new TextField();
        ageText.setLabelText("Age");
        ageText.setPreferredSize(new Dimension(this.getPreferredSize().width / 2 - widthBorder / 2, ageText.getPreferredSize().height));
        
        //age
        birthdayText = new TextField();
        birthdayText.setLabelText("Birthday (mm/dd/yyyy)");
        birthdayText.setPreferredSize(new Dimension(this.getPreferredSize().width / 2 - widthBorder / 2, birthdayText.getPreferredSize().height));
        
        //Password
        passText = new PasswordField();
        passText.setLabelText("Password");
        passText.setPreferredSize(new Dimension(this.getPreferredSize().width - widthBorder, passText.getPreferredSize().height));
        passText.setShowAndHide(true);

        //Confirm Password
        confirmPassText = new PasswordField();
        confirmPassText.setLabelText("Confirm Password");
        confirmPassText.setPreferredSize(new Dimension(this.getPreferredSize().width - widthBorder, confirmPassText.getPreferredSize().height));
        confirmPassText.setShowAndHide(true);

        //Register here
        JLabel loginText = new JLabel("Log in here!");
        loginText.setForeground(Color.blue);
        loginText.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginText.setPreferredSize(new Dimension(this.getPreferredSize().width - widthBorder, loginText.getPreferredSize().height));
        loginText.setHorizontalAlignment(SwingConstants.CENTER);

        Font loginFont = new Font("Helvetica", Font.PLAIN, 15);
        Map<TextAttribute, Object> attributes = new HashMap<>(loginFont.getAttributes());
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        loginText.setFont(loginFont.deriveFont(attributes));

        loginText.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FrameNavigator.changeFrame("LoginFrame");
            }
        });

        
        //Submit
        submitButton = new Button();
        submitButton.setText("Submit");
        submitButton.setPreferredSize(new Dimension(100, 60));
        submitButton.setFont(getFont().deriveFont(21f));

        submitButton.addActionListener(this);

        
        this.add(firstNameText);
        this.add(lastNameText);
        this.add(emailText);
        this.add(ageText);
        this.add(birthdayText);
        this.add(passText);
        this.add(confirmPassText);
        this.add(loginText);
        this.add(submitButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            JComponent[] components = {firstNameText, lastNameText, emailText, ageText, birthdayText, passText, confirmPassText};
            SessionController.submitSignupHandler(components);
        }
    }
    
    public void reload() {
        firstNameText.setText(Session.getSignupVal(0));
        lastNameText.setText(Session.getSignupVal(1));
        emailText.setText(Session.getSignupVal(2));
        ageText.setText(Session.getSignupVal(3));
        birthdayText.setText(Session.getSignupVal(4));
        passText.setText(Session.getSignupVal(5));
        confirmPassText.setText(Session.getSignupVal(6));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int width = getWidth();
        int height = getHeight();
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, width, height, 20, 20);
        super.paintComponent(g);
        g2.dispose();
    }

    @Override
    public void setOpaque(boolean isOpaque) {
        super.setOpaque(false);
    }
}
