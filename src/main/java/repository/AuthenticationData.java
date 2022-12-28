package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AuthenticationData extends BaseConnection{

    public String getDesignation(String username,String password){
        try{
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("select userDesignation from users where userId = '"+username+"' and userPass = '"+password+"'");
//            stmt.setString(1,username);
//            stmt.setString(2,password);
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
}
