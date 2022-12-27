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
}
