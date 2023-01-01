package service;

import domain.Category;
import repository.CategoryRepository;

public class CategoryServices {
    CategoryRepository categoryRepository = new CategoryRepository();
    public  boolean addService(Category cat) {

        if(cat!=null){
            categoryRepository.insertCategory(cat.getCategoryName());
            return true;
        }
        else {return false;}

    }

    public  boolean update(Object previous,String updated) {

        if(updated.equals(previous)){
            System.out.println("Already equal");
            return false;
        }
        else {
            categoryRepository.updateCategory(previous,updated);
            return true;}

    }
    public  boolean checkCategoryAvailibility(String CategoryName){
        if(categoryRepository.getCategoryname(CategoryName)){
            return true;
        }
        else{
            return false;
        }

    }
}
