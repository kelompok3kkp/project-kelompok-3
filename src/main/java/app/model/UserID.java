/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.app.model;

/**
 *
 * @author aliframadhan
 */
public class UserID {
    private static String id;

    public static void setuserLogin(String idKaryawan) {
        id = idKaryawan;
    }

    public static String getuserLogin() {
        return id;
    }
}
