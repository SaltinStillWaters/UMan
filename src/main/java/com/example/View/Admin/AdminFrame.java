package com.example.View.Admin;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.example.Controller.FrameNavigator;
import com.example.Model.Config;
import com.example.View.FilledTablePanel;

public class AdminFrame extends JFrame {
    private FilledTablePanel filledTablePanel;

    public AdminFrame() {
        //Frame config
        this.setTitle("U-Man: User Management Software");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(Config.isResizable);
        this.setSize(Config.frameDimension);
        this.setPreferredSize(Config.frameDimension);
        this.getContentPane().setBackground(new Color(0xcff5fe));
        this.setIconImage(Config.logo.getImage());
        this.setLayout(new FlowLayout());
        

        //Registration text
        JLabel adminViewText = new JLabel();
        adminViewText.setText("USERS INFO - ADMIN VIEW");
        adminViewText.setFont(new Font("Helvetica", Font.BOLD, 40));
        adminViewText.setHorizontalAlignment(JLabel.CENTER);
        adminViewText.setForeground(new Color(0x0e3c5d));
        adminViewText.setPreferredSize(new Dimension(Config.frameDimension.width, adminViewText.getPreferredSize().height));

        //Register here
        JLabel logoutText = new JLabel("Log out!");
        logoutText.setForeground(Color.red);
        logoutText.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoutText.setPreferredSize(new Dimension(this.getPreferredSize().width, logoutText.getPreferredSize().height));
        logoutText.setHorizontalAlignment(SwingConstants.CENTER);

        Font loginFont = new Font("Helvetica", Font.PLAIN, 15);
        Map<TextAttribute, Object> attributes = new HashMap<>(loginFont.getAttributes());
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        logoutText.setFont(loginFont.deriveFont(attributes));

        logoutText.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FrameNavigator.logout();
            }
        });


        filledTablePanel = new FilledTablePanel();

        this.add(adminViewText);
        this.add(logoutText);
        this.add(filledTablePanel);

        
        //Others
        this.setVisible(false);
        this.setLocationRelativeTo(null);
    }

    public void updateFilledTable(ArrayList<ArrayList<String>> rows) {
        filledTablePanel.update(rows);
    }
}
