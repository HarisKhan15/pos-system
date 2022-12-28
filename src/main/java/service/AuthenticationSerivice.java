package service;

import repository.AuthenticationData;

import javax.swing.*;

public class AuthenticationSerivice {
    AuthenticationData authenticationData = new AuthenticationData();
    public String checkLogin(String username, String password) {
        return authenticationData.getDesignation(username,password);
    }
}
