package repository;

import domain.AllProducts;

import java.sql.*;
import java.util.ArrayList;

public class AllProductsRepository extends BaseConnection{



//    public String[][] getAllValueForInventoryJtabel(int columnSize) {
//        ArrayList<AllProducts> list = new ArrayList<>();
//        try {
//            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//            PreparedStatement stmt = conn.prepareStatement("select p.productId,p.productName,v.variantId,v.variantName,c.categoryName,pv.price,pv.quantity,p.avalaibilty from products as p inner join category as c on c.categoryId=p.categoryId inner join productvariant as pv on p.productId = pv.productId inner join variant as v on v.variantId = pv.variantId ORDER BY productId ASC ");
//            ResultSet rs = stmt.executeQuery();
//
//            while (rs.next()) {
//                list.add(new AllProducts(rs.getInt("productId"),rs.getString("productName"),rs.getInt("variantId"),rs.getString("variantName"),rs.getString("categoryName"),rs.getDouble("price"),rs.getInt("quantity"),rs.getString("avalaibilty")));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        String[][] result = new String[list.size()][8];
//        for (int i = 0; i < list.size(); i++) {
//            result[i][0]=list.get(i).getProductId();
//            result[i][1]=list.get(i).getProdctName();
//            result[i][2]=list.get(i).getVariantId();
//            result[i][3]=list.get(i).getVariantName();
//            result[i][4]=list.get(i).getCategoryName();
//            result[i][5]=list.get(i).getPrice();
//            result[i][6]=list.get(i).getQuantity();
//            result[i][7]=list.get(i).getAvailabilty();
//        }
//        return result;
//    }
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

//    public String[][] getAllInventoryValueForJtabel(int columnSize) {
//        ArrayList<AllProducts> list = new ArrayList<>();
//        try {
//            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//            PreparedStatement stmt = conn.prepareStatement("select p.productId,p.productName,v.variantId,v.variantName,c.categoryName,pv.price,pv.quantity from products as p inner join category as c on c.categoryId=p.categoryId inner join productvariant as pv on p.productId = pv.productId inner join variant as v on v.variantId = pv.variantId;");
//
//
    public String[][] getAllValueForInventoryJtabel(int columnSize) {
        ArrayList<AllProducts> list = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("select p.productId,p.productName,v.variantId,v.variantName,c.categoryName,pv.price,pv.quantity,p.avalaibilty from products as p inner join category as c on c.categoryId=p.categoryId inner join productvariant as pv on p.productId = pv.productId inner join variant as v on v.variantId = pv.variantId ORDER BY productId ASC ");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new AllProducts(rs.getInt("productId"),
                        rs.getString("productName"),
                        rs.getInt("variantId"),
                        rs.getString("variantName"),
                        rs.getString("categoryName"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getString("avalaibilty")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String[][] result = new String[list.size()][8];
        for (int i = 0; i < list.size(); i++) {
            result[i][0]=list.get(i).getProductId();
            result[i][1]=list.get(i).getProdctName();
            result[i][2]=list.get(i).getVariantId();
            result[i][3]=list.get(i).getVariantName();
            result[i][4]=list.get(i).getCategoryName();
            result[i][5]=list.get(i).getPrice();
            result[i][6]=list.get(i).getQuantity();
            result[i][7]=list.get(i).getAvailabilty();
        }
        return result;
    }
    public String[][] getAllValueForJtabel(int columnSize) {
        ArrayList<AllProducts> list = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("select p.productId,p.productName,v.variantId,v.variantName,c.categoryName,pv.price,pv.quantity from products as p inner join category as c on c.categoryId=p.categoryId inner join productvariant as pv on p.productId = pv.productId inner join variant as v on v.variantId = pv.variantId ORDER BY productId ASC ");
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
    public void insertProducts(String productName,String categoryName,String vareintName,String barcode,Double price,Double quantity){
       Integer categoryId=getCategoryIdbyCatName(categoryName);
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("Insert into products values (?,?,?,'active')");
            stmt.setInt(1,0);//productID
            stmt.setInt(2,categoryId);//categoryID
            stmt.setString(3,productName);
            stmt.executeUpdate();
            Integer productID=getProcductIdbyProductName(productName);
            Integer varientID=getVarientIdbyVarientName(vareintName);
            insertIntoProductVariant(productID,varientID,barcode,price,quantity);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void insertIntoProductVariant(Integer productId,Integer varientID,String barcode,Double price,Double quantity){
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("Insert into productvariant values (?,?,?,?,?,?)");
            stmt.setInt(1,0);//productvariantID
            stmt.setInt(2,productId);//productId
            stmt.setInt(3,varientID);//varientID
            stmt.setString(4,barcode);//barcode
            stmt.setDouble(5,price);//price
            stmt.setDouble(6,quantity);//
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Integer getProcductIdbyProductName(String productName) {
        Integer productID=null;
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("SELECT productId from products where productName=(?)");
            stmt.setString(1, productName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                productID=rs.getInt("productId");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (productID!=null) {
            return productID;
        }
        else {
            return null;
        }
    }
    public Integer getVarientIdbyVarientName(String VareintName) {
        Integer VarientId=null;
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("SELECT variantId from variant where variantName=(?)");
            stmt.setString(1, VareintName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                VarientId=rs.getInt("variantId");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (VarientId!=null) {
            return VarientId;
        }
        else {
            return null;
        }
    }


    public Integer getCategoryIdbyCatName(String CategoryName) {
        Integer categoryID=null;
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("SELECT categoryId from category where categoryName=(?)");
            stmt.setString(1, CategoryName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
            categoryID=rs.getInt("categoryId");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (categoryID!=null) {
            return categoryID;
        }
        else {
            return null;
        }


    }
    public String getBarcodesname(Integer productId){
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("SELECT barcode from productvariant where productId=(?)");
            stmt.setInt(1,productId);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
              return rs.getString("barcode");
            }
            else {
                return "Barcode Not available";
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public Boolean getProductsname(String ProductsName){
        boolean flag=false;
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("SELECT productName from products where productName=(?)");
            stmt.setString(1,ProductsName);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                flag=true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(flag){
            activeProduct(ProductsName);
            return true;
        }
        else {
            return false;
        }


    }
    public void activeProduct(String name){
        try{
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("UPDATE products SET avalaibilty = 'active' WHERE productName=(?)");
            stmt.setString(1,name);
            stmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public boolean updateProduct(Integer productId,String productName,String categoryName,String varientName,String barcode,Double price,Double quantity){
        try{
            Integer catID=getCategoryIdbyCatName(categoryName);
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt1 = conn.prepareStatement("update products set productName=(?),categoryId=(?) where productId=(?)");
            stmt1.setString(1,productName);
            stmt1.setInt(2,catID);
            stmt1.setInt(3,productId);
            stmt1.executeUpdate();

            Integer vareintID=getVarientIdbyVarientName(varientName);
                PreparedStatement stmt2 = conn.prepareStatement("update productvariant set variantId=(?),barcode=(?),price=(?),quantity=(?) where productId=(?);");
            stmt2.setInt(1,vareintID);
            stmt2.setString(2,barcode);
            stmt2.setDouble(3,price);
            stmt2.setDouble(4,quantity);
            stmt2.setInt(5,productId);
            stmt2.executeUpdate();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteProdcutByName(Object toDeleteName) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM products WHERE productName=(?)");
            stmt.setString(1,toDeleteName.toString());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            updateProductAvailibility(toDeleteName.toString());
            return false;
        }

    }
    public void updateProductAvailibility(String name){
        try{
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("UPDATE products SET avalaibilty = 'inactive' WHERE productName=(?)");
            stmt.setString(1,name);
            stmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
