package com.example.sylergy.controller;

import com.example.sylergy.activities.BarcodeProductActivity;
import com.example.sylergy.activities.UpdateActivity;
import com.example.sylergy.objects.Context;

import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

public class ActivityDispatcher {
    private static ActivityDispatcher instance;
    public static ActivityDispatcher getInstance(){
        if(instance==null)
            instance=new ActivityDispatcher();
        return instance;
    }

    public void dispatcher(Context context){

        try {
            /*XPath xpath = XPathFactory.newInstance().newXPath();
            InputSource inputSource = new InputSource("activities.xml");
            String regularExpression = "//*[@id='"+ context.getEvent() +"'][1]";
            Node element = (Node) xpath.evaluate(regularExpression,inputSource, XPathConstants.NODE);*/
            int id = BarcodeProductActivity.context.getResources().getIdentifier(context.getEvent(),"string",BarcodeProductActivity.context.getPackageName());
            String element = BarcodeProductActivity.context.getString(id);

            UpdateActivity activity = (UpdateActivity) Class.forName(element.trim()).getMethod("newInstance").invoke(null);
            activity.updateWithCommandResult(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
