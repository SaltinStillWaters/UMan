package com.example.View.Custom;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.Timer;

import com.example.Model.Config;

public class Toast extends JDialog {
    public Toast(String message, int duration) {
        setUndecorated(true);
        setAlwaysOnTop(true);
        setLayout(new GridBagLayout());

        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 100, 100));

        var label = new JLabel(message);
        label.setOpaque(true);
        label.setBackground(new Color(244, 63, 81, 200));
        label.setForeground(Color.WHITE);
        label.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        label.setFont(new Font("Arial", Font.BOLD, 14));
        
        add(label);

        // Size the dialog based on the label
        pack();

        // Center the toast on the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = screenSize.height / 2 - Config.frameDimension.height / 2 + 20;
        setLocation(x, y);

        // Use a timer to dismiss the toast after a set duration
        new Timer(duration, e -> dispose()).start();
    }

    public static void showToast(String message, int duration) {
        // Create and show the toast
        new Thread(() -> {
            Toast toast = new Toast(message, duration);
            toast.setVisible(true);
        }).start();
    }
}
