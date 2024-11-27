package com.example.Controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;

import com.example.View.AdminFrame;

public class FrameNavigator {
    private static HashMap<String, JFrame> strToFrame = new HashMap<>();
    private static String currFrameName;

    public static void init(String name, JFrame frame) {
        add(name, frame);
        currFrameName = name;
        frame.setVisible(true);
    }

    public static void goToLoggedInFrame(String email) {
        ArrayList<ArrayList<String>> rows = Database.getAllRows();

        for (ArrayList<String> row : rows) {
            for (String x : row) {
                System.out.print(x + "\t");
            }
            System.out.println();
        }

        changeFrame("AdminFrame");
        ((AdminFrame) strToFrame.get("AdminFrame")).updateFilledTable(rows);
        
        switch (email) {
            case "ADMIN@ADMIN.ADMIN":
                break;
            case "SUPERADMIN@ADMIN.ADMIN":
                break;
            default:
                //normal users
        }
    }

    public static void add(String name, JFrame frame) {
        strToFrame.put(name, frame);
    }

    public static void changeFrame(String name) {
        strToFrame.get(currFrameName).setVisible(false);
        strToFrame.get(name).setVisible(true);
    }
}
