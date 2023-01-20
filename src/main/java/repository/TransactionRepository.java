package repository;

import domain.Transactions;
import domain.ViewTransaction;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TransactionRepository extends BaseConnection{
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    public String[][] getAllValueForJtabel() {
        ArrayList<Transactions> transactionList = new ArrayList<>();
        try {
            LocalDate localDate = LocalDate.now();//For reference
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String date = localDate.format(formatter);

            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("select * from transactions where transactionDate=(?);");
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
    public String[][] getAllTransactionForJTable(){
        ArrayList<Transactions> transactionList = new ArrayList<>();
        try {

            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("select * from transactions;");
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
            result[i][2] = String.valueOf(transactionList.get(i).getTransactionDate());
            result[i][3] = transactionList.get(i).getAmount().toString();
        }
        return result;
    }
    public String[][] getSpecificTransactionForJTable(int transactionId){
        ArrayList<ViewTransaction> list = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("select p.productName,v.variantName,c.categoryName,tp.productQuantity,tp.amount from transactionproduct as tp  inner Join transactions as t on t.transactionId =tp.transactionId inner join productvariant as pv  on pv.prodvariantId=tp.prodvariantId inner join products as p on p.productId=pv.productId  inner join variant as v on v.variantId = pv.variantId inner join category as c  on c.categoryId = p.categoryId where t.transactionId = (?)");
            stmt.setInt(1,transactionId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new ViewTransaction(rs.getString("productName"),rs.getString("variantName"),rs.getString("categoryName"),rs.getInt("productQuantity"),rs.getDouble("amount")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String[][] result = new String[list.size()][5];
        for (int i = 0; i < list.size(); i++) {
            result[i][0] = list.get(i).getProductName();
            result[i][1] = list.get(i).getVariantName();
            result[i][2] = list.get(i).getCategoryName();
            result[i][3] = list.get(i).getProductQuantity().toString();
            result[i][4] = list.get(i).getAmount().toString();
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
