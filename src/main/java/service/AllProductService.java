package service;

import domain.AllProducts;
import repository.AllProductsRepository;

public class AllProductService {
    AllProductsRepository allProductsRepository = new AllProductsRepository();

    public AllProducts getDataByBarcode(String barcode){
        return allProductsRepository.getDataByBarcode(barcode);
    }
    public String[][] getAllValuesForJTable(int column){
        return allProductsRepository.getAllValueForJTable(column);
    }
    public String[][] getInventoryForTable(int column){
        return allProductsRepository.getAllInventoryValueForJtabel(column);
    }
    public String[][] getBySearch(int column,String searchData){
        return allProductsRepository.getBySearch(column,searchData);
    }
    public boolean updateInventory(String price,String quantity,String productId){
        if (allProductsRepository.updateProductInventory(price,quantity,productId)) {
            return true;
        }
        else {
            return false;
        }
    }

}

