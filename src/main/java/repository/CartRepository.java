package repository;

import domain.Cart;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class CartRepository extends BaseConnection{
    private static ArrayList<Cart> cartArrayList = new ArrayList<>();

    public static void addProductIntoCart(Cart newProduct){
        cartArrayList.add(newProduct);
    }
    public static ArrayList<Cart> getAll(){
        return cartArrayList;
    }
    public static void removeAllData(){
        cartArrayList.removeAll(cartArrayList);
    }
    public static Cart getLastValue(){
        return cartArrayList.get(cartArrayList.size()-1);
    }
    public static Double totalAmount(){
        Double sum=0.0;
        for (Cart c:cartArrayList) {
            sum+=c.getAmount();
        }
        return sum;
    }
    public static void clearList(){
        cartArrayList.removeAll(cartArrayList);
    }
    public static String[][] getAllCartDataForJTable(int column){
        String[][] result = new String[cartArrayList.size()][column];
        for (int i = 0; i < cartArrayList.size(); i++) {
            result[i][0]=cartArrayList.get(i).getProductName();
            result[i][1]=cartArrayList.get(i).getVariantName();
            result[i][2]=cartArrayList.get(i).getProductCategory();
            result[i][3]=cartArrayList.get(i).getUnitPrice().toString();
            result[i][4]=cartArrayList.get(i).getQuantity().toString();
            result[i][5]=cartArrayList.get(i).getAmount().toString();
        }
        return result;
    }


    public static void removeData(int index) {
        cartArrayList.remove(index);
    }

    public static Cart getByIndex(int index) {
        return cartArrayList.get(index);
    }

    public int getProductVariantId(int productId,int variantId){
        int i =-1;
        try{
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("select prodVariantId from productvariant where variantId = ? and productId = ?;");
            stmt.setInt(1,variantId);
            stmt.setInt(2,productId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                i = rs.getInt("prodVariantId");
            }
            return i;
        }catch (Exception e){
            e.printStackTrace();
        }
        return i;
    }
    public void updateQuantity(int prodVariantId,int quantity){
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("update productvariant set quantity = ? where prodVariantId = ?;");
            stmt.setInt(1,quantity);
            stmt.setInt(2,prodVariantId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void itemIntoDatabase(int transactionId,int prodVariantId,int quantity,double amount){
        try{
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("insert into transactionProduct values(?,?,?,?);");
            stmt.setInt(1,transactionId);
            stmt.setInt(2,prodVariantId);
            stmt.setInt(3, quantity);
            stmt.setDouble(4,amount);
            stmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
