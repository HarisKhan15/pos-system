package service;

import UI.EditVariantsUI;
import domain.Varient;
import repository.VarientRepository;

import javax.swing.*;

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
            return true;
        }
        else {
            varientRepository.updateVarient(previous,updated);
            return false;}

    }
}
