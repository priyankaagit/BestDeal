
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

public class SaxParser4TelevisionXMLdataStore extends DefaultHandler {
    Television television;
	List<Television> tv;
    String televisionXmlFileName;
    String elementValueRead;
	HashMap<String,Television> tvs;
    
    public SaxParser4TelevisionXMLdataStore(String televisionXmlFileName) {
        this.televisionXmlFileName = televisionXmlFileName;
		tvs = new HashMap<String,Television>();
		tv= new ArrayList<Television>();
        parseDocument();
        prettyPrint();
    }


    private void parseDocument() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
			File f = new File(televisionXmlFileName);
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


    public HashMap<String,Television> prettyPrint() {
	
        for (Television television: tv) {
            	System.out.println("laptop #"+ television.id +":");
		System.out.println("\t\t retailer: " + television.retailer);
		System.out.println("\t\t name: " + television.name);
		for (String accessory: television.accessories) {
			System.out.println("\t\t accessory: " + accessory);
		}
		
		tvs.put(television.id,television);
        }
		
		return tvs;
		
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

        if (elementName.equalsIgnoreCase("television")) {
            television = new Television();
            television.setId(attributes.getValue("id"));
            television.setRetailer(attributes.getValue("retailer"));
        }

    }

    @Override
    public void endElement(String str1, String str2, String element) throws SAXException {
 
        if (element.equals("television")) {
            tv.add(television);
	    return;
        }
        if (element.equalsIgnoreCase("image")) {
            television.setImage(elementValueRead);
	    return;
        }
        if (element.equalsIgnoreCase("name")) {
            television.setName(elementValueRead);
	    return;
        }
        if(element.equalsIgnoreCase("accessory")){
           television.getAccessories().add(elementValueRead);
	    return;
        }
        if(element.equalsIgnoreCase("price")){
            television.setPrice(Integer.parseInt(elementValueRead));
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
       SaxParser4TelevisionXMLdataStore sx = new SaxParser4TelevisionXMLdataStore("Television.xml");
		HashMap<String,Television> tvs = sx.prettyPrint();
		for(String s: tvs.keySet()){
			Television p = tvs.get(s);
			System.out.println(p.getId());
			System.out.println(p.getImage());
		}
		
    }

}
