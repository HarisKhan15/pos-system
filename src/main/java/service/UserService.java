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
    public String[][] getALLReportOfUser(String userId,String month){
        return userRepository.getAllReportOfUserForJTable(userId,month);
    }
    public String[][] getALLTransactionReportOfMonthly(String month){
        return userRepository.getAllTransactionReportMonthlyForJTable(month);
    }

    public String[][] getAllusersforJtable(int columnSize){
        return userRepository.getAllUsersToDelete(columnSize);
    }
    public boolean deleteUser(String userName){
        return userRepository.deleteUserByName(userName);
    }

    public  boolean checkUserAvailibility(String userId){
        if(userRepository.getUserName(userId)){
            return true;
        }
        else{
            return false;
        }

    }
}
