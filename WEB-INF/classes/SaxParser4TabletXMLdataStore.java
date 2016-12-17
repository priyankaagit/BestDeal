
/*********


http://www.saxproject.org/

SAX is the Simple API for XML, originally a Java-only API. 
SAX was the first widely adopted API for XML in Java, and is a “de facto” standard. 
The current version is SAX 2.0.1, and there are versions for several programming language environments other than Java. 



The following URL from Oracle is the JAVA documentation for the API

https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html


*********/





import java.io.*;
import java.text.ParseException;
import java.util.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


////////////////////////////////////////////////////////////

/**************

SAX parser use callback function  to notify client object of the XML document structure. 
You should extend DefaultHandler and override the method when parsin the XML document

***************/

////////////////////////////////////////////////////////////

public class SaxParser4TabletXMLdataStore extends DefaultHandler {
    Tablet tablet;
	List<Tablet> tab;
    String tabletXmlFileName;
    String elementValueRead;
	HashMap<String,Tablet> tabs;
    
    public SaxParser4TabletXMLdataStore(String tabletXmlFileName) {
        this.tabletXmlFileName = tabletXmlFileName;
		tabs = new HashMap<String,Tablet>();
		tab= new ArrayList<Tablet>();
        parseDocument();
        prettyPrint();
    }


    private void parseDocument() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
			File f = new File(tabletXmlFileName);
			 parser.parse(f, this);
            //parser.parse(consoleXmlFileName, this);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }


    public HashMap<String,Tablet> prettyPrint() {
	
        for (Tablet tablet: tab) {
            	System.out.println("laptop #"+ tablet.id +":");
		System.out.println("\t\t retailer: " + tablet.retailer);
		System.out.println("\t\t name: " + tablet.name);
		for (String accessory: tablet.accessories) {
			System.out.println("\t\t accessory: " + accessory);
		}
		
		tabs.put(tablet.id,tablet);
        }
		
		return tabs;
		
    }





////////////////////////////////////////////////////////////

/*************

There are a number of methods to override in SAX handler  when parsing your XML document :

    Group 1. startDocument() and endDocument() :  Methods that are called at the start and end of an XML document. 
    Group 2. startElement() and endElement() :  Methods that are called  at the start and end of a document element.  
    Group 3. characters() : Method that is called with the text content in between the start and end tags of an XML document element.


There are few other methods that you could use for notification for different purposes, check the API at the following URL:

https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html

***************/

////////////////////////////////////////////////////////////

    @Override
    public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {

        if (elementName.equalsIgnoreCase("tablet")) {
            tablet = new Tablet();
            tablet.setId(attributes.getValue("id"));
            tablet.setRetailer(attributes.getValue("retailer"));
        }

    }

    @Override
    public void endElement(String str1, String str2, String element) throws SAXException {
 
        if (element.equals("tablet")) {
            tab.add(tablet);
	    return;
        }
        if (element.equalsIgnoreCase("image")) {
            tablet.setImage(elementValueRead);
	    return;
        }
        if (element.equalsIgnoreCase("name")) {
            tablet.setName(elementValueRead);
/*	    return;
		 if (element.equalsIgnoreCase("condition")) {
            tablet.setCondition(elementValueRead);
	    return;*/
        }
        if(element.equalsIgnoreCase("accessory")){
           tablet.getAccessories().add(elementValueRead);
	    return;
        }
        if(element.equalsIgnoreCase("price")){
            tablet.setPrice(Integer.parseInt(elementValueRead));
	    return;
        }

    }

    @Override
    public void characters(char[] content, int begin, int end) throws SAXException {
        elementValueRead = new String(content, begin, end);
    }


    /////////////////////////////////////////
    // 	     Kick-Start SAX in main       //
    ////////////////////////////////////////

    public static void main(String[] args) {
       SaxParser4TabletXMLdataStore sx = new SaxParser4TabletXMLdataStore("Tablet.xml");
		HashMap<String,Tablet> tabs = sx.prettyPrint();
		for(String s: tabs.keySet()){
			Tablet p = tabs.get(s);
			System.out.println(p.getId());
			System.out.println(p.getImage());
			//System.out.println(p.getCondition());
		}
		
    }

}
