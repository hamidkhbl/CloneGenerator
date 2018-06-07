package Util;
import Parser.*;

import java.io.File;

import javax.xml.bind.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class hXML {
	
	
    public static void main(String[] args) {
        Loop lp = new Loop();
        lp.setLoopText("for(int i=0 ; i > 0 ; i++)"); 
        lp.location=new Location("file","something",12,124);
        
        try {
            File file = new File("C:\\Clone\\userConfigData.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Loop.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //jaxbMarshaller.marshal(lp, file);
            jaxbMarshaller.marshal(lp, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }


}
