package repository;

import domain.Category;
import domain.Varient;
//import jdk.jfr.Category;

import java.sql.*;
import java.util.ArrayList;

public class CategoryRepository extends BaseConnection{
    public void insertCategory(String CategoryName){
        Category category=null;
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("Insert into category values (?,?,'active')");
            stmt.setInt(1,0);
            stmt.setString(2,CategoryName);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public String[][] getAllValueForJtabel(int columnSize) {
        ArrayList<Varient> list = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("Select * from category where availabilty = 'active'");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new Varient(rs.getInt("categoryId"),rs.getString("categoryName")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String[][] result = new String[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            result[i][0]=list.get(i).getVariantID();
            result[i][1]=list.get(i).getVarientName();
        }
        return result;
    }

    public void updateCategory(Object previous,String updated){
        Category category=null;
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("UPDATE category SET categoryName = (?) WHERE categoryName=(?)");
            stmt.setString(1,updated);
            stmt.setString(2,previous.toString());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public void activeCategory(String name){
        try{
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("UPDATE category SET availabilty = 'active' WHERE categoryName=(?)");
            stmt.setString(1,name);
            stmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public boolean deleteCategoryByName(Object toDeleteName) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM category WHERE categoryName=(?)");
            stmt.setString(1, toDeleteName.toString());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            updateAvailibility(toDeleteName.toString());
            return false;
        }


    }

    public void updateAvailibility(String name){
        try{
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("UPDATE category SET availabilty = 'inactive' WHERE categoryName=(?)");
            stmt.setString(1,name);
            stmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public Boolean getCategoryname(String CategoryName){
        boolean flag=false;
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("SELECT categoryName from category where categoryName=(?)");
            stmt.setString(1,CategoryName);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                flag=true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(flag){
            activeCategory(CategoryName);
            return true;
        }
        else {
            return false;
        }


    }

    public String[] getAllValueforDropdown(){
        ArrayList<Varient> list = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("select categoryName from category");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new Varient(rs.getString("categoryName")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String[] result = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i]=list.get(i).getVarientName();
        }
        return result;

    }
}
