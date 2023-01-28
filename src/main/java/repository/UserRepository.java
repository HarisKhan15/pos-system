package repository;

import domain.Transactions;
import domain.Varient;
import domain.Users;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class UserRepository extends BaseConnection{

    public String getDesignation(String username,String password){
        try{
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("select userDesignation from users where userId = ? and userPass = ? and avalaibilty = 'active';");
            stmt.setString(1,username);
            stmt.setString(2,password);
            ResultSet rs = stmt.executeQuery();
            String designation = null;
            while(rs.next()){
                designation= rs.getString("userDesignation");
            }
            return designation;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }


    public boolean insertUser(String userId,String userPassword,String UserName,String UserDesignation,String userEmail){
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO users VALUES (?,?,?,?,?,'active')");
            stmt.setString(1,userId);
            stmt.setString(2,userPassword);
            stmt.setString(3,UserName);
            stmt.setString(4,UserDesignation);
            stmt.setString(5,userEmail);
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
    public String[] getAllUserForDropDown() {
        ArrayList<Users> list = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("select userId from users");
            ResultSet rs = stmt.executeQuery();
            list.add(new Users("All"));

            while (rs.next()) {
                list.add(new Users(rs.getString("userId")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String[] result = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i]=list.get(i).getUserId();
        }
        return result;
    }

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    public String[][] getAllValueOfUserForJtabel(String userId) {
        ArrayList<Transactions> transactionListOfUSer = new ArrayList<>();
        try {
            LocalDate localDate = LocalDate.now();//For reference
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String date = localDate.format(formatter);

            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("select * from transactions where UserId=(?) and transactionDate =(?);");
            stmt.setString(1, userId);
            stmt.setString(2, date);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                transactionListOfUSer.add(new Transactions(rs.getInt("transactionId"), rs.getString("userId"), rs.getDate("transactionDate"), rs.getDouble("totalAmount")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        String[][] result = new String[transactionListOfUSer.size()][4];
        for (int i = 0; i < transactionListOfUSer.size(); i++) {
            result[i][0] = transactionListOfUSer.get(i).getTransactionId().toString();
            result[i][1] = transactionListOfUSer.get(i).getUserId();
            result[i][2] = formatter.format(transactionListOfUSer.get(i).getTransactionDate());
            result[i][3] = transactionListOfUSer.get(i).getAmount().toString();
        }
        return result;

    }
    public String[][] getAllUsers(int columnSize) {
        ArrayList<Users> list = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("Select * from users where avalaibilty='active'");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Users(rs.getString("UserId"),rs.getString("userName"),rs.getString("userDesignation"),rs.getString("userEmail")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String[][] result = new String[list.size()][4];
        for (int i = 0; i < list.size(); i++) {
            result[i][0]=list.get(i).getUserId();
            result[i][1]=list.get(i).getUserName();
            result[i][2]=list.get(i).getUserDesignation();
            result[i][3]=list.get(i).getUserEmail();
        }
        return result;

    }
    public boolean deleteUserById(String UserId) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("update users set avalaibilty ='inactive' WHERE userId=(?)");
            stmt.setString(1,UserId.toString());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public Boolean getUserName(String userId){
        boolean flag=false;
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("SELECT userId from users where userId=?;");
            stmt.setString(1,userId);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                flag=true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(flag){
            return true;
        }
        else {
            return false;
        }
    }

    public String getUserPassword(String userId) {
        try{
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("select userPass from users where userId = ?;");
            stmt.setString(1,userId);
            ResultSet rs = stmt.executeQuery();
            String userPassword = null;
            while(rs.next()){
                userPassword= rs.getString("userPass");
            }
            return userPassword;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
    public boolean updateUser(String userId,String userPassword,String userName,String userEmail, String userDesignation){
        try{
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("update users set userPass=(?),userName=(?),userDesignation=(?),userEmail=(?) where userId=(?)");
            stmt.setString(1,userPassword);
            stmt.setString(2,userName);
            stmt.setString(3,userDesignation);
            stmt.setString(4,userEmail);
            stmt.setString(5,userId);
            stmt.executeUpdate();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    }

