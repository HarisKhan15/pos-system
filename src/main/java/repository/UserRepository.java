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
            PreparedStatement stmt = conn.prepareStatement("select userId from Users");
            ResultSet rs = stmt.executeQuery();

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
            PreparedStatement stmt = conn.prepareStatement("select * from Transactions where UserId=(?);");
            stmt.setString(1, userId);
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
}
