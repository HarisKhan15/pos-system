package repository;

import domain.Cart;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class CartRepository {
    private static ArrayList<Cart> cartArrayList = new ArrayList<>();

    public static void addProductIntoCart(Cart newProduct){
        cartArrayList.add(newProduct);
    }
    public static ArrayList<Cart> getAll(){
        return cartArrayList;
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


}
