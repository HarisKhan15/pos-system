package service;

import domain.Cart;
import repository.CartRepository;

public class CartService {
    public int checkCart(Cart cart){
        for (Cart c: CartRepository.getAll()) {
            if(c.equals(cart)){
                c.increaseQuantity();
                return CartRepository.getAll().indexOf(c);
            }
        }
        return -1;
    }
    public void addIntoCart(Cart cart){
        CartRepository.addProductIntoCart(cart);
    }
    public String[] lastValue(){
        Cart cart = CartRepository.getLastValue();
        return new String[]{cart.getProductName(),cart.getVariantName(),cart.getProductCategory(),cart.getUnitPrice().toString(),cart.getQuantity().toString(),cart.getAmount().toString()};
    }
    public String getUpdatedQuantity(int x){
        return CartRepository.getAll().get(x).getQuantity().toString();
    }
    public String getUpdatedAmount(int x){
        return CartRepository.getAll().get(x).getAmount().toString();
    }
}
