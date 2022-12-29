package repository;

import domain.Varient;
import jdk.jfr.Category;

import java.sql.*;
import java.util.ArrayList;

public class CategoryRepository extends BaseConnection{
    public void insertCategory(String CategoryName){
        Category category=null;
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("Insert into Category values (?,?)");
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
            PreparedStatement stmt = conn.prepareStatement("Select * from Category");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new Varient(rs.getInt("categoryId"),rs.getString("categoryName")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
            PreparedStatement stmt = conn.prepareStatement("UPDATE Category SET categoryName = (?) WHERE categoryName=(?)");
            stmt.setString(1,updated);
            stmt.setString(2,previous.toString());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public void deleteCategoryByName(Object toDeleteName){
        Category category=null;
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Category WHERE categoryName=(?)");
            stmt.setString(1,toDeleteName.toString());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
