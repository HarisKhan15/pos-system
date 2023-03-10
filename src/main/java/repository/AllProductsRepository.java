package repository;

import domain.AllProducts;

import java.sql.*;
import java.util.ArrayList;

public class AllProductsRepository extends BaseConnection{

    public String[][] getAllValueForJTable(int column){
        ArrayList<AllProducts> list = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("select pv.prodVariantId,p.productId,p.productName,v.variantId,v.variantName,c.categoryName,pv.price,pv.quantity from products as p inner join Category as c on c.categoryId=p.categoryId inner join productVariant as pv on p.productId = pv.productId inner join variant as v on v.variantId = pv.variantId ; ");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new AllProducts(rs.getInt("prodVariantId"),rs.getInt("productId"),rs.getString("productName"),rs.getInt("variantId"),rs.getString("variantName"),rs.getString("categoryName"),rs.getDouble("price"),rs.getInt("quantity")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String[][] result = new String[list.size()][column+1];
        for (int i = 0; i < list.size(); i++) {
            result[i][0]=list.get(i).getProdVariantId();
            result[i][1]=list.get(i).getProductId();
            result[i][2]=list.get(i).getProdctName();
            result[i][3]=list.get(i).getVariantId();
            result[i][4]=list.get(i).getVariantName();
            result[i][5]=list.get(i).getCategoryName();
            result[i][6]=list.get(i).getPrice();
            result[i][7]=list.get(i).getQuantity();
        }
        return result;
    }

    public String[][] getAllInventoryValueForJtabel(int columnSize) {
        ArrayList<AllProducts> list = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("select p.productId,p.productName,v.variantId,v.variantName,c.categoryName,pv.price,pv.quantity from products as p inner join category as c on c.categoryId=p.categoryId inner join productvariant as pv on p.productId = pv.productId inner join variant as v on v.variantId = pv.variantId;");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new AllProducts(rs.getInt("productId"),rs.getString("productName"),rs.getInt("variantId"),rs.getString("variantName"),rs.getString("categoryName"),rs.getDouble("price"),rs.getInt("quantity")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String[][] result = new String[list.size()][7];
        for (int i = 0; i < list.size(); i++) {
            result[i][0]=list.get(i).getProductId();
            result[i][1]=list.get(i).getProdctName();
            result[i][2]=list.get(i).getVariantId();
            result[i][3]=list.get(i).getVariantName();
            result[i][4]=list.get(i).getCategoryName();
            result[i][5]=list.get(i).getPrice();
            result[i][6]=list.get(i).getQuantity();
        }
        return result;
    }
    public String[][] getBySearch(int columnSize,String searchedData) {
        ArrayList<AllProducts> list = new ArrayList<>();
        try {

            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("select pv.prodVariantId,p.productId,p.productName,v.variantId,v.variantName,c.categoryName,pv.price,pv.quantity from products as p inner join Category as c on c.categoryId=p.categoryId inner join productVariant as pv on p.productId = pv.productId inner join variant as v on v.variantId = pv.variantId where p.productName like ? having pv.quantity > 0");
            stmt.setString(1,"%"+searchedData+"%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new AllProducts(rs.getInt("prodVariantId"),rs.getInt("productId"),rs.getString("productName"),rs.getInt("variantId"),rs.getString("variantName"),rs.getString("categoryName"),rs.getDouble("price"),rs.getInt("quantity")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String[][] result = new String[list.size()][columnSize+1];
        for (int i = 0; i < list.size(); i++) {
            result[i][0]=list.get(i).getProdVariantId();
            result[i][1]=list.get(i).getProductId();
            result[i][2]=list.get(i).getProdctName();
            result[i][3]=list.get(i).getVariantId();
            result[i][4]=list.get(i).getVariantName();
            result[i][5]=list.get(i).getCategoryName();
            result[i][6]=list.get(i).getPrice();
            result[i][7]=list.get(i).getQuantity();
        }
        return result;
    }

    public AllProducts getDataByBarcode(String barcode) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("select pv.prodVariantId,p.productId,p.productName,v.variantId,v.variantName,c.categoryName,pv.price,pv.quantity from products as p inner join category as c on c.categoryId=p.categoryId inner join productVariant as pv on p.productId = pv.productId inner join variant as v on v.variantId = pv.variantId where pv.barcode = ? ; ");
            stmt.setString(1,barcode);
            ResultSet rs = stmt.executeQuery();
            AllProducts allProducts = null;
            while (rs.next()) {
                allProducts = new AllProducts(rs.getInt("prodVariantId"),rs.getInt("productId"),rs.getString("productName"),rs.getInt("variantId"),rs.getString("variantName"),rs.getString("categoryName"),rs.getDouble("price"),rs.getInt("quantity"));
            }
            return allProducts;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean updateProductInventory(String price, String quantity, String productId){
        try{
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("update productvariant set price=(?),quantity=quantity+(?) where productId=(?)");
            stmt.setString(1,price);
            stmt.setString(2,quantity);
            stmt.setString(3,productId);
            stmt.executeUpdate();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
