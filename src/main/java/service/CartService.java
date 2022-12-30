package service;

import domain.Cart;
import repository.CartRepository;

public class CartService {
    public int checkCart(Cart cart,boolean argument){
        for (Cart c: CartRepository.getAll()) {
            if(c.equals(cart)){
                if(argument) {
                    if(c.getMaxQuantity()>c.getQuantity()){
                        c.increaseQuantity();
                        return CartRepository.getAll().indexOf(c);
                    }else {
                        return -3;
                    }
                }else {
                    if(c.getQuantity()==1){
                        return -2;
                    }
                    c.decreaseQuantity();
                }
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
        return CartRepository.getByIndex(x).getQuantity().toString();
    }
    public String getUpdatedAmount(int x){
        return CartRepository.getByIndex(x).getAmount().toString();
    }

    public void addItemsToDatabase(int transactionId){
        CartRepository cartRepository = new CartRepository();
        int productVariantId = -1;
        for (Cart c:CartRepository.getAll()) {
            productVariantId = cartRepository.getProductVariantId(c.getProductId(),c.getVariantId());
            int tempQuantity = c.getMaxQuantity() - c.getQuantity();
            cartRepository.updateQuantity(productVariantId,tempQuantity);
            cartRepository.itemIntoDatabase(transactionId,productVariantId,c.getQuantity(),c.getAmount());
        }
    }
}
