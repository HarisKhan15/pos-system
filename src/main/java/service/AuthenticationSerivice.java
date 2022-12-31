package service;

import domain.Users;
import domain.Varient;
import repository.AllProductsRepository;
import repository.UserRepository;

public class AuthenticationSerivice {
    UserRepository userRepository = new UserRepository();
    public String checkLogin(String username, String password) {
        return userRepository.getDesignation(username,password);
    }
    public boolean chechUserNameAvaialbllity(String userName){

        return userRepository.getUserName(userName) == null;
    }
    public  boolean addUser(Users usr) {

        if(usr!=null){
            userRepository.insertUser(usr.getUserId(),usr.getUserPass(), usr.getUserName(),usr.getUserDesignation(),usr.getUserEmail());
            return true;
        }
        else {return false;}

    }
    public String[]getAllusers(){
        return userRepository.getAllUserForDropDown();
    }

    public String[][] getreportofUser(String userId){
        return userRepository.getAllValueOfUserForJtabel(userId);
    }
}
