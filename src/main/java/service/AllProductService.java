package service;

import domain.AllProducts;
import domain.Category;
import repository.AllProductsRepository;

public class AllProductService {
    AllProductsRepository allProductsRepository = new AllProductsRepository();

    public AllProducts getDataByBarcode(String barcode){
        return allProductsRepository.getDataByBarcode(barcode);
    }
    public String[][] getAllValuesForJTable(int column){
        return allProductsRepository.getAllValueForJTable(column);
    }
    public String[][] getValuesForJTable(int column){
        return allProductsRepository.getAllValueForProductJtabel(column);
    }
    public String[][] getInventoryForTable(int column){
        return allProductsRepository.getAllValueForInventoryJtabel(column);
    }

    public String[][]  InventoryJtabel(int column){
        return allProductsRepository.getAllValueForInventoryJtabel(column);
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
    public  boolean checkProductAvailibility(String productName,String categoryName,String varientName){
        if(allProductsRepository.getProductsAvailability(productName,categoryName,varientName)){
            return true;
        }
        else{
            return false;
        }

    }
  //  allProducts,categoryCb.getSelectedItem().toString(),varientCb.getSelectedItem().toString(),barCodeTf.getText(),Double.valueOf(priceTf.getText()),Double.valueOf(quantityTf.getText()))){
    public  boolean addProductService(AllProducts prd,String categoryName,String varientName,String barcode,Double price,Double quantity) {

        if(prd!=null){
            allProductsRepository.insertProducts(prd.getProdctName(),categoryName,varientName,barcode,price,quantity);
            return true;
        }
        else {return false;}

    }
    public String getBarcodeService(Integer productId){
       return allProductsRepository.getBarcodesname(productId);
    }

    public boolean updateProductService(Integer ProductID,String productName,String categoryName,String varientName,String barcode,Double price,Double quantity){
       return allProductsRepository.updateProduct( ProductID,productName, categoryName, varientName, barcode, price, quantity);
    }
    public boolean deleteProduct(String prodcutName,String varientName,String categoryName){
        return allProductsRepository.deleteProdcutByName(prodcutName,varientName,categoryName);
    }
}

