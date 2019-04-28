package com.example.sylergy.objects;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.sylergy.logs.Logs;
import com.example.sylergy.logs.LogsView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ApplicationForm {
   // private Product product;
   /* public ApplicationForm(Product product){
        this.product = product;
    }*/
    public boolean checkApplicationForm(Product product, Activity activity){
        if(checkBarcode(product.getBarcode(), activity) && checkName(product.getName(), activity) && checkIngredients(product.getIngredients(), activity) &&
                checkNutrients(product.getNutrients(), activity) && checkImage(product.getImage(), activity))
            return true;
        else
            return false;
    }

    public boolean checkBarcode(String barcode, Activity activity){
        LogsView logsView;
       if(barcode != null && !barcode.isEmpty()){
           if(barcode.length() >= 8 && barcode.length() <= 20){
               try {
                   Long.parseLong(barcode);
                   return true;
               } catch (NumberFormatException excepcion) {
                   logsView = new LogsView(Logs.INCORRECT_FORMAT);
                   logsView.showInfo(activity);
                   return false;
               }
           }
           else
               logsView = new LogsView(Logs.INCORRECT_BARCODE);
       }
       else
           logsView = new LogsView(Logs.NO_BARCODE);

       logsView.showInfo(activity);
       return false;
    }
    public boolean checkName(String name, Activity activity){
        LogsView logsView;
        if(name != null && !name.isEmpty()){
            if(name.length() <= 50){
                try {
                    Long.parseLong(name);
                    logsView = new LogsView(Logs.INCORRECT_FORMAT + "name");
                    logsView.showInfo(activity);
                    return false;
                } catch (NumberFormatException excepcion) {
                    return true;
                }
            }
            else
                logsView = new LogsView(Logs.INCORRECT_NAME);
        }
        else
            logsView = new LogsView(Logs.NO_SEARCH_NAME);

        logsView.showInfo(activity);
        return false;
    }
    public boolean checkIngredients(ArrayList<HashMap<String, Object>> ingredients, Activity activity){
        LogsView logsView;

        if(ingredients != null && !ingredients.isEmpty() && ingredients.size() > 0 ){//&& ingredients.contains(0) != false){

            if(  ingredients.size() < 1000) {
                for(HashMap<String, Object> map :ingredients){
                    if(map.get("text").equals(" ") ) {
                        logsView = new LogsView(Logs.INCORRECT_INGREDIENT);
                        logsView.showInfo(activity);
                        return false;
                    }
                }
                return true;
            } else
                logsView = new LogsView(Logs.INCORRECT_INGREDIENTS);
        }
        else
            logsView = new LogsView(Logs.NO_INGREDIENTS);
        logsView.showInfo(activity);
        return false;
    }

    public boolean checkNutrients(HashMap<String, Object> nutrients, Activity activity){
        LogsView logsView;
        if(nutrients != null && !nutrients.isEmpty()){
            return true;
        }
        logsView = new LogsView(Logs.NO_NUTRIENTS);
        logsView.showInfo(activity);
        return false;
    }
    public boolean checkImage(String image, Activity activity){
        /*if(this.product.getImage() != null && !this.product.getImage().isEmpty()){
                return true;
        }*/
        return false;
    }
}
