package com.example.Controller;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JTextField;

import com.example.Model.Session;

public class SessionController {
    public static void submitLoginHandler(JComponent[] components) {
        if (Session.loginKeys.length != components.length) {
            //todo: add exception
            System.out.println("error!");
            return;
        }

        String[] inputs = new String[components.length];
        int ctr = 0;

        for (JComponent component : components) {
            if (component instanceof JTextField textField) {
                inputs[ctr++] = textField.getText();
            } else if (component instanceof JCheckBox checkBox) {
                inputs[ctr++] = checkBox.isSelected() ? "selected" : "";
            } else {
                //todo: add exception
                System.out.println("error!");
                return;
            }
        }

        Session.updateLogin(inputs);
    }

    public static void submitSignupHandler(JComponent[] components) {
        if (Session.signupKeys.length != components.length) {
            //todo: add exception
            System.out.println("error!");
            return;
        }

        String[] inputs = new String[components.length];
        int ctr = 0;

        for (JComponent component : components) {
            if (component instanceof JTextField textField) {
                inputs[ctr++] = textField.getText();
            } else {
                //todo: add exception
                System.out.println("error!");
                return;
            }
        }

        Session.updateSignup(inputs);
    }
}
