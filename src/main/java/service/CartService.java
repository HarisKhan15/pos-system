package service;

import domain.Cart;
import repository.CartRepository;

public class CartService {
    public boolean checkCart(Cart cart){
        for (Cart c: CartRepository.getAll()) {
            if(c.equals(cart)){
                c.increaseQuantity();
                return true;
            }
        }
        return false;
    }
    public void addIntoCart(Cart cart){
        CartRepository.addProductIntoCart(cart);
    }
}
