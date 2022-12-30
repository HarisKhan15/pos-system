package repository;

import domain.Varient;
import domain.Users;

import java.sql.*;
import java.util.ArrayList;

public class UserRepository extends BaseConnection{

    public String getDesignation(String username,String password){
        try{
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("select userDesignation from Users where userId = '"+username+"' and userPass = '"+password+"'");
//            stmt.setString(1,username);
//            stmt.setString(2,password);h
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

    public Boolean getUserName(String userName){
        String username=null;
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("SELECT userName from Users where UserName=(?)");
            stmt.setString(1,userName);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                username=rs.getString("UserName");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(userName!=null){
            return true;
        }
        else {
            return false;
        }


    }

    public void insertUser(String userId,String userPassword,String UserName,String UserDesignation,String userEmail){
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Users VALUES (?,?,?,?,?)");
            stmt.setString(1,userId);
            stmt.setString(2,userPassword);
            stmt.setString(3,UserName);
            stmt.setString(4,UserDesignation);
            stmt.setString(5,userEmail);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public String[] getAllUserForDropDown() {
        ArrayList<Users> list = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("Select * from Users");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new Users(rs.getString("userName")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String[] result = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i]=list.get(i).getUserName();
        }
        return result;
    }


}
