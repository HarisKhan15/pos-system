package repository;

import domain.Varient;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VarientRepository extends BaseConnection{


    public void insertVarient(String varientName){
        Varient varient =null;
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("Insert into Variant values (?,?)");
            stmt.setInt(1,0);
            stmt.setString(2,varientName);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public String[][] getAllValueForJtabel(int columnSize) {
        ArrayList<Varient> list = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("Select * from Variant");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new Varient(rs.getInt("variantId"),rs.getString("variantName")));
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
    public void updateVarient(Object previous,String updated){
        Varient varient =null;
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("UPDATE Variant SET variantName = (?) WHERE variantName=(?)");
            stmt.setString(1,updated.toString());
            stmt.setString(2,updated.toString());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}
