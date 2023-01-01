package service;

import repository.AllProductsRepository;

public class AllProductService {
    AllProductsRepository allProductsRepository = new AllProductsRepository();

    public String[][] getDataForTable(int column){
        return allProductsRepository.getAllValueForJtabel(column);
    }
    public String[][] getBySearch(int column,String searchData){
        return allProductsRepository.getBySearch(column,searchData);
    }
}
