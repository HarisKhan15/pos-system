package service;

import domain.Users;
import repository.UserRepository;

public class UserService {
    UserRepository userRepository = new UserRepository();
    public String checkLogin(String username, String password) {
        return userRepository.getDesignation(username,password);
    }
    public  boolean addUser(Users usr) {
        return userRepository.insertUser(usr.getUserId(),usr.getUserPass(), usr.getUserName(),usr.getUserDesignation(),usr.getUserEmail());
    }
    public String[]getAllusers(){
        return userRepository.getAllUserForDropDown();
    }

    public String[][] getreportofUser(String userId){
        return userRepository.getAllValueOfUserForJtabel(userId);
    }

    public String[][] getAllusersforJtable(int columnSize){
        return userRepository.getAllUsers(columnSize);
    }
    public boolean deleteUser(String userId){
        return userRepository.deleteUserById(userId);
    }

    public  boolean checkUserAvailibility(String userId){
        if(userRepository.getUserName(userId)){
            return true;
        }
        else{
            return false;
        }

    }

    public String getUserPassword(String userId) {
        return userRepository.getUserPassword(userId);
    }
    public boolean updateUser(String userId,String userPassword,String userName ,String email,String designation){
        if (userRepository.updateUser(userId,userPassword,userName,email,designation)) {
            return true;
        }
        else {
            return false;
        }
    }
}
