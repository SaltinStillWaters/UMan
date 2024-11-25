package com.example.Model;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;

public class Config {
    public static Dimension frameDimension = new Dimension(1000, 700);
    public static boolean isResizable = false;
    public static Color frameBG = new Color(0xedfcff);
    public static ImageIcon logo = new ImageIcon(Config.class.getClassLoader().getResource("logo.png"));
    public static ImageIcon bigLogo = new ImageIcon(Config.class.getClassLoader().getResource("bigLogo.png"));
    /**
     * Private constructor to prevent instantiation
     */
    private Config() {}
}
