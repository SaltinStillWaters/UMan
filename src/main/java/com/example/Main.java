package com.example;

import com.example.Controller.Database;
import com.example.Model.Session;
import com.example.Model.Validator;
import com.example.View.SignupFrame;

public class Main {
    public static void main(String[] args) {
        Database.init();

    
        System.setProperty("sun.java2d.uiScale", "1.0");
        System.out.println("Hello worlds");

        //init
        Session.init();
        Validator.validateLogin();

        new SignupFrame();
    }
}