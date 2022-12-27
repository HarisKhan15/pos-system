package repository;

import domain.Varient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VarientRepository extends BaseConnection{
    private static List<Varient> varientlist = new ArrayList<>();
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
    public void  VarientList() {

        Varient varient =null;
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("Select * from Variant");

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                varient = new Varient(rs.getInt("variantId"),rs.getString("variantName"));
                varientlist.add(varient);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static String[][] getAllValueForJtabel(int columnSize){


        String [][]  result =new String[varientlist.size()][columnSize];

        for (int i = 0; i < varientlist.size() ; i++) {
            result[i][0]=varientlist.get(i).getVariantID();
            result[i][1]= varientlist.get(i).getVarientName();

        }
        return result;
    }
}
