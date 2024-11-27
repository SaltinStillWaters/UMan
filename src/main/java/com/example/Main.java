package com.example;

import com.example.Controller.Database;
import com.example.Controller.FrameNavigator;
import com.example.Model.Session;
import com.example.View.AdminFrame;
import com.example.View.Login.LoginFrame;
import com.example.View.Signup.SignupFrame;

public class Main {
    public static void main(String[] args) {
        Database.init();
        Session.init();
        
        System.setProperty("sun.java2d.uiScale", "1.0");
    
        FrameNavigator.init("LoginFrame", new LoginFrame());
        FrameNavigator.add("SignupFrame", new SignupFrame());
        FrameNavigator.add("AdminFrame", new AdminFrame());
    }
}