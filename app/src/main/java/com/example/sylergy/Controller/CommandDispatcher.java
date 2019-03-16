package com.example.sylergy.Controller;

import com.example.sylergy.Command.Command;
import com.example.sylergy.R;

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
    public Command dispatcher(int event){
        Command command=null;

        try {
            XPath xpath = XPathFactory.newInstance().newXPath();
            InputSource inputSource = new InputSource(new FileReader("activities.xml"));
            String regularExpression = "//*[@id='"+ event +"'][1]";

            Node element = (Node) xpath.evaluate(regularExpression,inputSource, XPathConstants.NODE);

            if(element != null){
                command = (Command) Class.forName(element.getTextContent().trim()).getConstructor().newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error when dispatch the command");
        }

        return command;
    }
}
