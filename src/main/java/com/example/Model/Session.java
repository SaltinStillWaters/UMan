package com.example.Model;

import java.util.HashMap;

import com.example.Controller.Database;
import com.example.Controller.FrameNavigator;
import com.example.View.Custom.Toast;

public class Session {
    private static HashMap<String, String> login = new HashMap<>();
    private static HashMap<String, String> signup = new HashMap<>();

    public static final String[] loginKeys = {"EMAIL", "PASSWORD", "REMEMBER"};
    public static final String[] signupKeys = {"FIRST_NAME", "LAST_NAME", "EMAIL", "AGE", "BIRTHDAY", "PASSWORD", "CONFIRM PASSWORD"};

    public static void init() {
        for (String key : loginKeys) {
            login.put(key, "");
        }

        for (String key : signupKeys) {
            signup.put(key, "");
        }
    }
    
    public static String getLoginVal(String key) {
        return login.get(key);
    }

    public static String getLoginVal(int keyIndex) {
        return login.get(loginKeys[keyIndex]);
    }
    
    public static String getSignupVal(String key) {
        return signup.get(key);
    }
    
    public static String getSignupVal(int keyIndex) {
        return signup.get(signupKeys[keyIndex]);
    }

    //todo: add validator()

    public static void updateLogin(String[] vals) {
        if (loginKeys.length != vals.length) {
            //todo: add exception
            System.out.println("error!");
            return;
        }

        for (int x = 0; x < loginKeys.length; ++x) {
            login.put(loginKeys[x], vals[x]);
            System.out.println(vals[x]);
        }

        String validatorResult = Validator.checkCredentials();
        if (validatorResult.isEmpty()) {
            FrameNavigator.goToLoggedInFrame(login.get("EMAIL"));
        } else {
            Toast.showToast(validatorResult, 2000);
        }
    }
    
    public static void updatePass(String hashedPass) {
        signup.put("PASSWORD", hashedPass);
    }

    public static void updateSignup(String[] vals) {
        if (signupKeys.length != vals.length) {
            //todo: add exception
            System.out.println("error!");
            return;
        }

        for (int x = 0; x < signupKeys.length; ++x) {
            signup.put(signupKeys[x], vals[x]);
            System.out.println(vals[x]);
        }

        String errMessage = Validator.validateSignup();
        System.out.println(errMessage + "~no error?~");
        if (errMessage.isBlank() || errMessage.isEmpty()) {
            Database.insertUser();
            FrameNavigator.goToLoggedInFrame(signup.get("EMAIL"));
        } else {
            System.out.println("Err msg");
            Toast.showToast(errMessage, 2000);
        }
    }

    public static void resetLogin() {
        for (String key : loginKeys) {
            login.put(key, "");
        }
    }

    public static void resetSignup() {
        for (String key : signupKeys) {
            signup.put(key, "");
        }
    }
}
