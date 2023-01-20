package repository;

import domain.Cart;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
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
    public static void removeByObject(Cart cart){
        cartArrayList.remove(cart);
    }

    public static Cart getByIndex(int index) {
        return cartArrayList.get(index);
    }
    public static boolean isEmpty(){
        return cartArrayList.isEmpty();
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
    public String[][] getCartForRefund(int transactionId,int column){
        try{
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("select pv.prodVariantId,p.productId,p.productName,v.variantId,v.variantName,c.categoryName,pv.price,tp.productQuantity,tp.amount,pv.quantity from productvariant as pv inner join transactionproduct as tp on tp.prodVariantId = pv.prodVariantId inner join products as p on p.productId = pv.productId inner join variant as v on v.variantId = pv.variantId inner join category as c on c.categoryId = p.categoryId where tp.transactionId = ?;");
            stmt.setInt(1,transactionId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                cartArrayList.add(new Cart(rs.getInt("prodVariantId"),rs.getInt("productId"),rs.getString("productName"),rs.getInt("variantId"),rs.getString("variantName"),rs.getString("categoryName"),rs.getDouble("price"),rs.getInt("productQuantity"),rs.getDouble("amount"),rs.getInt("quantity")));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return CartRepository.getAllCartDataForJTable(column);
    }
    public void updateTransaction(String userId,int transactionId,Double amount){
        try{
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("update transactions set userId = ? ,transactionDate = ?,totalAmount = ? where transactionId = ?;");
            stmt.setString(1,userId);
            stmt.setDate(2,Date.valueOf(LocalDate.now()));
            stmt.setDouble(3,amount);
            stmt.setInt(4, transactionId);
            stmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void updateByHashMap(int quantity,double amount,int prodVariantId,int transactionId){
        try{
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("update transactionproduct set productQuantity = ?,amount = ? where prodVariantId = ? and transactionID = ?;");
            stmt.setInt(1, quantity);
            stmt.setDouble(2,amount);
            stmt.setInt(3, prodVariantId);
            stmt.setInt(4, transactionId);
            stmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void deleteItemFromTransactionProductTable(int transactionId,int prodVariantId){
        try{
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("delete from transactionproduct where transactionId = ? and prodVariantId = ?;");
            stmt.setInt(1, transactionId);
            stmt.setInt(2, prodVariantId);
            stmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
