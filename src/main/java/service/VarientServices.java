package service;

import domain.Varient;
import repository.VarientRepository;

public class VarientServices {
    VarientRepository varientRepository = new VarientRepository();
    public  boolean addVarient(Varient vrt) {

        if(vrt!=null){
         varientRepository.insertVarient(vrt.getVarientName());
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
            varientRepository.updateVarient(previous,updated);
            return true;}

    }

    public  boolean checkVariantAvailibility(String variantName){
        if(varientRepository.getVarientName(variantName)){
            return true;
        }
        else{
            return false;
        }
    }
    public  String[] getAllVarientforDropDown(){
      return   varientRepository.getAllValueforDropdown();
    }
}
