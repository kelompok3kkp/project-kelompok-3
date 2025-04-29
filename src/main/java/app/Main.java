/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.app;

import main.java.app.view.Login;

/**
 *
 * @author aliframadhan
 */
public class Main {
    public static void main(String[] args){
        Login login = new Login();
        login.setLocationRelativeTo(null);
        login.setVisible(true);
    }
}
