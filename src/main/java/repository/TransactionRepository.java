package repository;

import java.sql.*;
import java.time.LocalDate;

public class TransactionRepository extends BaseConnection{
    public boolean enterTransactionIntoDatabase(String userId,Double totalAmount){
        try{
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("insert into transactions values(?,?,?,?);");
            stmt.setInt(1,0);
            stmt.setString(2,userId);
            stmt.setDate(3, Date.valueOf(LocalDate.now()));
            stmt.setDouble(4,totalAmount);
            stmt.executeUpdate();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public int getTransactionId(){
        int i =-1;
        try{
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("select transactionId from transactions order by transactionId desc limit 1;");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                i = rs.getInt("transactionId");
            }
            return i;
        }catch (Exception e){
            e.printStackTrace();
        }
        return i;
    }

}
