package repository;

import domain.Users;
import domain.Varient;

import java.sql.*;
import java.util.ArrayList;


public class VarientRepository extends BaseConnection{


    public void insertVarient(String varientName){
        Varient varient =null;
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("Insert into variant values (?,?,'active')");
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
            PreparedStatement stmt = conn.prepareStatement("Select * from variant where availibilty = 'active' ");
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
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("UPDATE variant SET variantName = (?) WHERE variantName=(?)");
            stmt.setString(1,updated);
            stmt.setString(2,previous.toString());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public boolean deleteVariantByName(Object toDeleteName) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM variant WHERE variantName=(?)");
            stmt.setString(1,toDeleteName.toString());
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
            PreparedStatement stmt = conn.prepareStatement("UPDATE variant SET availibilty = 'inactive' WHERE variantName=(?)");
            stmt.setString(1,name);
            stmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public Boolean getVarientName(String variantName){
        boolean flag=false;
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("SELECT variantName from variant where variantName=(?)");
            stmt.setString(1,variantName);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                flag=true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(flag){
            activeVariant(variantName);
            return true;
        }
        else {
            return false;
        }


    }
    public void activeVariant(String name){
            try{
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement stmt = conn.prepareStatement("UPDATE variant SET availibilty = 'active' WHERE variantName=(?)");
                stmt.setString(1,name);
                stmt.executeUpdate();
            }catch (Exception e){
                e.printStackTrace();
            }

    }
    public String[] getAllValueforDropdown(){
        ArrayList<Varient> list = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("select variantName from variant where availibilty='active'");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new Varient(rs.getString("variantName")));
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
