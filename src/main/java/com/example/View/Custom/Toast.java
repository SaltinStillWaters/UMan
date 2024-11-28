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
    private static Toast currentToast;

    private static JLabel label;
    private Timer timer;

    private Toast(String message, int duration) {
        setUndecorated(true);
        setAlwaysOnTop(true);
        setLayout(new GridBagLayout());
        setBackground(new Color(0, 0, 0, 0));

        
        label = new JLabel(message);
        label.setOpaque(true);
        label.setBackground(new Color(244, 63, 81, 200));
        label.setForeground(Color.WHITE);
        label.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        label.setFont(new Font("Arial", Font.BOLD, 14));
        
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 100, 100));
        
        add(label);
        pack();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = screenSize.height / 2 - Config.frameDimension.height / 2 + 20;
        setLocation(x, y);

        timer = new Timer(duration, e -> disposeToast());
    }

    public static void showToast(String message, int duration) {
        if (currentToast != null && currentToast.isVisible()) {
            label.setText(message);
            currentToast.timer.restart();
        } else {
            currentToast = new Toast(message, duration);
            currentToast.setVisible(true);
            currentToast.timer.start();
        }
    }

    private void disposeToast() {
        timer.stop();
        setVisible(false);
        dispose();
        currentToast = null;
    }
}
