package com.example.View;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.example.Controller.FrameNavigator;
import com.example.Model.Config;
import com.example.View.Custom.SeperatorPanel;

public class UserViewFrame extends JFrame {
    String name;
    JLabel welcomeText;
    
    public UserViewFrame() {
        //Frame config
        this.setTitle("U-Man: User Management Software");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(Config.isResizable);
        this.setSize(Config.frameDimension);
        this.setPreferredSize(Config.frameDimension);
        this.getContentPane().setBackground(new Color(0xedfcff));
        this.setIconImage(Config.logo.getImage());
        this.setLayout(new FlowLayout());
        
        name = "";
        //Registration text
        welcomeText = new JLabel();
        welcomeText.setText("WELCOME, " + name + "!");
        welcomeText.setFont(new Font("Helvetica", Font.BOLD, 40));
        welcomeText.setHorizontalAlignment(JLabel.CENTER);
        welcomeText.setForeground(new Color(0x0e3c5d));
        welcomeText.setPreferredSize(new Dimension(Config.frameDimension.width, welcomeText.getPreferredSize().height));

        //Register here
        JLabel logoutText = new JLabel("Log out!");
        logoutText.setForeground(Color.red);
        logoutText.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoutText.setHorizontalAlignment(SwingConstants.CENTER);
        
        Font loginFont = new Font("Helvetica", Font.PLAIN, 25);
        Map<TextAttribute, Object> attributes = new HashMap<>(loginFont.getAttributes());
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        logoutText.setFont(loginFont.deriveFont(attributes));
        logoutText.setPreferredSize(new Dimension(this.getPreferredSize().width, logoutText.getPreferredSize().height));

        logoutText.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FrameNavigator.logout();
            }
        });
        
        this.add(new SeperatorPanel(getForeground(), WIDTH, 20));
        this.add(welcomeText);
        this.add(logoutText);


        //Others
        this.setVisible(false);
        this.setLocationRelativeTo(null);
    }

    public void reload(String fullname) {
        name = fullname;
        welcomeText.setText("WELCOME, " + name + "!");
    }
}
