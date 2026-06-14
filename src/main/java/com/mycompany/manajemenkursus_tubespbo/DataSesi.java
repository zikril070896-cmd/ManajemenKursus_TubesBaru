package com.mycompany.manajemenkursus_tubespbo;

public class DataSesi {
    private static String usernameLogin = "";
    private static String roleLogin     = "";

    public static void setLogin(String username, String role) {
        usernameLogin = username;
        roleLogin     = role;
    }

    public static String getUsername() { return usernameLogin; }
    public static String getRole()     { return roleLogin; }

    public static void logout() {
        usernameLogin = "";
        roleLogin     = "";
    }
}