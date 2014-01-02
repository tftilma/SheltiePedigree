/*
 * Created on Jul 27, 2004
 *
*/
package nl.home.ttilma.xml.utils;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * @author ttilma
 */
public class DocumentUtils
{
  /**
   * Creates an empty DOM-DocumentBuilder for generating multiple DOM-Documents
   * @return
   * @throws ParserConfigurationException
   */
  public static DocumentBuilder createDocumentBuilder()
  throws ParserConfigurationException {
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    dbf.setIgnoringComments(true);
    dbf.setCoalescing(false);
    dbf.setNamespaceAware(true);
    dbf.setValidating(true);
    DocumentBuilder db = dbf.newDocumentBuilder();    
    return db;
  }
  
  /**
   * Creates an empty DOM-Document
   * @return
   * @throws ParserConfigurationException
   */
  public static Document createDocument()
  throws ParserConfigurationException {
    DocumentBuilder db = createDocumentBuilder();
    Document doc = db.newDocument();
    return doc;
  }
  
  /**
   * Creates a DOM-Document based upon a xmlfile.
   * It parses xml-file and creates a Document 
   * @param xmlfile
   * @return
   * @throws ParserConfigurationException
   * @throws SAXException
   * @throws IOException
   */
  public static Document createDocument(String xmlfile)
  throws ParserConfigurationException, SAXException, IOException {
    DocumentBuilder db = createDocumentBuilder();
    return db.parse(new File(xmlfile));    
  }
}
