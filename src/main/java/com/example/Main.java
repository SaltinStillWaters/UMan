package com.example;

import com.example.Controller.Database;
import com.example.Controller.FrameNavigator;
import com.example.Model.Session;
import com.example.View.Login.LoginFrame;
import com.example.View.SignupFrame;

public class Main {
    public static void main(String[] args) {
        Database.init();
        Session.init();
        
        System.setProperty("sun.java2d.uiScale", "1.0");
    
        FrameNavigator.init("LoginFrame", new LoginFrame());
        FrameNavigator.add("SignupFrame", new SignupFrame());
    }
}