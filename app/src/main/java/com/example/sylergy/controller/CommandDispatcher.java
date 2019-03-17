package com.example.sylergy.controller;

import com.example.sylergy.activities.BarcodeProductActivity;
import com.example.sylergy.command.Command;

import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import java.io.FileReader;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

public class CommandDispatcher {
    private static CommandDispatcher instance;
    public static CommandDispatcher getInstance(){
        if(instance==null)
            instance=new CommandDispatcher();
        return instance;
    }
    public Command dispatcher(String event){
        Command command=null;

        try {
            /*XPath xpath = XPathFactory.newInstance().newXPath();
            InputSource inputSource = new InputSource(new FileReader("activities.xml"));
            String regularExpression = "//*[@id='"+ event +"'][1]";

            Node element = (Node) xpath.evaluate(regularExpression,inputSource, XPathConstants.NODE);*/
            int id = BarcodeProductActivity.context.getResources().getIdentifier(event,"string",BarcodeProductActivity.context.getPackageName());
            String element = BarcodeProductActivity.context.getString(id);
            if(element != null){
                command = (Command) Class.forName(element.trim()).getConstructor().newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error when dispatch the command");
        }

        return command;
    }
}
