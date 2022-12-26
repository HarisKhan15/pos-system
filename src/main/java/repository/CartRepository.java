package repository;

import domain.Cart;

import java.util.ArrayList;
import java.util.List;

public class CartRepository {
    private static List<Cart> cartArrayList = new ArrayList<>();

    public static void addProductIntoCart(Cart newProduct){
        cartArrayList.add(newProduct);
    }
}
