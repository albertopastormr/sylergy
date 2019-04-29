package com.example.sylergy.objects;

import android.app.Activity;

import com.example.sylergy.logs.Logs;
import com.example.sylergy.utils.Check;

import java.util.ArrayList;
import java.util.HashMap;


public class ApplicationForm {

    public Check checkApplicationForm(Product product){
        if(checkName(product.getName()).getCorrect()){
            if(checkBarcode(product.getBarcode()).getCorrect()){
                if(checkNutrients(product.getNutrients()).getCorrect()) {
                    if(checkIngredients(product.getIngredients()).getCorrect()) {
                        if (checkImage(product.getImage()).getCorrect())
                            return new Check(true, null);
                        return new Check(false, checkImage(product.getImage()).getLog());
                    }
                    return new Check(false, checkIngredients(product.getIngredients()).getLog());
                }
                return new Check(false, checkNutrients(product.getNutrients()).getLog());
            }
            return new Check(false, checkBarcode(product.getBarcode()).getLog());
        }
        return new Check(false, checkName(product.getName()).getLog());
    }

    public Check checkBarcode(String barcode){
        if(barcode != null && !barcode.isEmpty()){
            if(barcode.length() >= 8 && barcode.length() <= 20){
                try {
                    Long.parseLong(barcode);
                    return new Check(true, null);
                } catch (NumberFormatException excepcion) {
                    return new Check(false, Logs.INCORRECT_FORMAT);
                }
            }
            else
                return new Check(false, Logs.INCORRECT_BARCODE);
        }
        else
            return new Check(false, Logs.NO_BARCODE);
    }
    public Check checkName(String name){
        if(name != null && !name.isEmpty()){
            if(name.length() <= 50){
                try {
                    Long.parseLong(name);
                    return new Check(false, Logs.INCORRECT_FORMAT + "name");
                } catch (NumberFormatException excepcion) {
                    return new Check(true, null);
                }
            }
            else
                return new Check(false, Logs.INCORRECT_NAME);
        }
        else
            return new Check(false, Logs.NO_SEARCH_NAME);

    }
    public Check checkIngredients(ArrayList<HashMap<String, Object>> ingredients){

        if(ingredients != null && !ingredients.isEmpty() ){//&& ingredients.contains(0) != false){

            if(  ingredients.size() < 1000) {
                for(HashMap<String, Object> map :ingredients){
                    if(map.get("text").equals(" ") ) {
                        return new Check(false, Logs.INCORRECT_INGREDIENT);
                    }
                }
                return new Check(true, null);
            } else
                return new Check(false, Logs.INCORRECT_INGREDIENTS);
        }
        else
            return new Check(false, Logs.NO_INGREDIENTS);
    }

    public Check checkNutrients(HashMap<String, Object> nutrients){

        if(nutrients != null && !nutrients.isEmpty()){
            return new Check(true, null);
        }
        return new Check(false, Logs.NO_NUTRIENTS);
    }
    public Check checkImage(String image){
        /*if(this.product.getImage() != null && !this.product.getImage().isEmpty()){
                return true;
        }*/
        return new Check(true, null);
    }
}
