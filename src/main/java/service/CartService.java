package service;

import domain.Cart;
import repository.CartRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class CartService {
    CartRepository cartRepository = new CartRepository();
    HashMap<Integer,Integer> quantityMap = new HashMap<>();
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
    public void clearList(){
        CartRepository.clearList();
    }
    public boolean isEmpty(){
        return CartRepository.isEmpty();
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

    public String[][] getDataForRefund(int transactionId,int column){
        String[][] result = cartRepository.getCartForRefund(transactionId,column);
        for (Cart c:CartRepository.getAll()) {
            quantityMap.put(c.getProdVariantId(),c.getMaxQuantity());
        }
        return result;
    }
    public void addItemsToDatabase(int transactionId){
        CartRepository cartRepository = new CartRepository();
        for (Cart c:CartRepository.getAll()) {
            int productVariantId = cartRepository.getProductVariantId(c.getProductId(),c.getVariantId());
            int tempQuantity = c.getMaxQuantity() - c.getQuantity();
            cartRepository.updateQuantity(productVariantId,tempQuantity);
            cartRepository.itemIntoDatabase(transactionId,productVariantId,c.getQuantity(),c.getAmount());
        }
    }
    public Double getTotalBill(){
        Double sum = 0.0;
        for (Cart c:CartRepository.getAll()) {
            sum+=c.getAmount();
        }
        return sum;
    }
    ArrayList<Integer> deleteProducts = new ArrayList<>();
    public void deleteItemRefund(int row,int transactionId){
        Cart cart = CartRepository.getAll().get(row);
        for (int x:quantityMap.keySet()) {
            if(x == cart.getProdVariantId()){
                int updatedQuantity =1+quantityMap.get(cart.getProdVariantId());
                quantityMap.put(cart.getProdVariantId(),updatedQuantity);
                if(updatedQuantity==(cart.getQuantity()+cart.getMaxQuantity())){
                    deleteProducts.add(cart.getProdVariantId());
                }
            }
        }

        int quantity = cart.getQuantity();
        if(quantity==1){
            CartRepository.removeData(row);
        }else{
            cart.decreaseQuantity();
        }
    }
    public boolean addItemRefund(int row){
        Cart cart = CartRepository.getAll().get(row);
        for (int x:quantityMap.keySet()) {
            if(x == cart.getProdVariantId()){
                int quantity = quantityMap.get(cart.getProdVariantId());
                if(quantity>0){
                    cart.increaseQuantity();
                    quantityMap.put(cart.getProdVariantId(),quantity-1);
                    return true;
                }
            }
        }
        System.out.println(quantityMap);
        if(cart.getMaxQuantity()>cart.getQuantity()){
            cart.increaseQuantity();
            return true;
        }

        return false;

    }


    public void addRefundItemIntoDatabase(int transactionId,String userId,Double newBill){
        cartRepository.updateTransaction(userId,transactionId,newBill);

        if(!deleteProducts.isEmpty()){
            for (Integer deleteProduct : deleteProducts) {
                cartRepository.deleteItemFromTransactionProductTable(transactionId, deleteProduct);
                cartRepository.updateQuantity(deleteProduct, quantityMap.get(deleteProduct));
                quantityMap.remove(deleteProduct);
            }
        }

        for (Cart c:CartRepository.getAll()) {
            int flag = 0;
            for (int x:quantityMap.keySet()) {
                if(c.getProdVariantId()==x){
                    cartRepository.updateByHashMap(c.getQuantity(),c.getAmount(),x,transactionId);
                    cartRepository.updateQuantity(x,quantityMap.get(x));
                    flag = 1;
                    break;
                }
            }
            if(flag!=1){
                    int tempQuantity = c.getMaxQuantity() - c.getQuantity();
                    cartRepository.updateQuantity(c.getProdVariantId(),tempQuantity);
                    cartRepository.itemIntoDatabase(transactionId,c.getProdVariantId(),c.getQuantity(),c.getAmount());
            }


        }

//        for (int x:) {
//
//        }
//        addItemsToDatabase(transactionId);
    }
}
