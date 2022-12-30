package repository;

import domain.Transactions;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TransactionRepository extends BaseConnection{
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    public String[][] getAllValueForJtabel(int columnSize) {
        ArrayList<Transactions> transactionList = new ArrayList<>();
        try {
            LocalDate localDate = LocalDate.now();//For reference
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String date = localDate.format(formatter);

            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("select * from Transactions where transactionDate=(?);");
            stmt.setString(1, date);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                transactionList.add(new Transactions(rs.getInt("transactionId"), rs.getString("userId"), rs.getDate("transactionDate"), rs.getDouble("totalAmount")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        String[][] result = new String[transactionList.size()][4];
        for (int i = 0; i < transactionList.size(); i++) {
            result[i][0] = transactionList.get(i).getTransactionId().toString();
            result[i][1] = transactionList.get(i).getUserId();
            result[i][2] = formatter.format(transactionList.get(i).getTransactionDate());
            result[i][3] = transactionList.get(i).getAmount().toString();
        }
        return result;

    }
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
