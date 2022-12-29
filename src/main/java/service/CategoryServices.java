package service;

import domain.Category;
import domain.Varient;
import repository.CategoryRepository;
import repository.VarientRepository;

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
}
