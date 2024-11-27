package com.example.Controller;

import java.util.HashMap;

import javax.swing.JFrame;

public class FrameNavigator {
    private static HashMap<String, JFrame> strToFrame = new HashMap<>();
    private static String currFrameName;

    public static void init(String name, JFrame frame) {
        add(name, frame);
        currFrameName = name;
        frame.setVisible(true);
    }

    public static void add(String name, JFrame frame) {
        strToFrame.put(name, frame);
    }

    public static void changeFrame(String name) {
        strToFrame.get(currFrameName).setVisible(false);
        strToFrame.get(name).setVisible(true);
    }
}
