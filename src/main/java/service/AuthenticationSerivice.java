package service;

import repository.UserRepository;

public class AuthenticationSerivice {
    UserRepository authenticationData = new UserRepository();
    public String checkLogin(String username, String password) {
        return authenticationData.getDesignation(username,password);
    }
    public boolean chechUserNameAvaialbllity(String userName){
        return true;
    }
}
