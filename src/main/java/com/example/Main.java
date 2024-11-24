package com.example;

import com.example.View.MainFrame;

public class Main {
    public static void main(String[] args) {
        System.setProperty("sun.java2d.uiScale", "1.0");
        System.out.println("Hello worlds");

        MainFrame mainFrame = new MainFrame();
    }
}