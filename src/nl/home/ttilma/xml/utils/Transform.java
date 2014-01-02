/*
 * Created on Jul 4, 2004
 *
*/
package nl.home.ttilma.xml.utils;

import java.io.File;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;

/**
 * @author ttilma
 */
public class Transform
{
  public static void transform(
      String inputFileName, 
      String stylesheetFileName, 
      String outputFileName) 
  throws TransformerException
  {
    Source inputSrc = new StreamSource(new File(inputFileName));
    Source cssSrc = new StreamSource(new File(stylesheetFileName));
    Result outputRes = new StreamResult(new File(outputFileName));
    
    doTransform(inputSrc, cssSrc, outputRes);   
  }

  public static void transform(
      Document inputDoc, 
      String stylesheetFileName, 
      String outputFileName) 
  throws TransformerException
  {
    Source inputSrc = new DOMSource(inputDoc);
    Source xsltSrc = new StreamSource(new File(stylesheetFileName));
    Result outputRes = new StreamResult(new File(outputFileName));
    
    try
    {
    doTransform(inputSrc, xsltSrc, outputRes);
    }
    catch (TransformerException e) 
    {
  	  e.printStackTrace();
      System.err.println("outputFileName: " + outputFileName);
      System.err.println("Cause: " + e.getCause());
      System.err.println("Message and Location: " + e.getMessageAndLocation());
      //System.err.println("LineNr: " + e.getLocator().getLineNumber());
    }
  }
  
  public static void transform(
      String inputFileName, 
      String stylesheetFileName, 
      Document outputDoc) 
  throws TransformerException
  {
    Source inputSrc = new StreamSource(new File(inputFileName));
    Source xsltSrc = new StreamSource(new File(stylesheetFileName));
    Result outputRes = new DOMResult(outputDoc);
    
    doTransform(inputSrc, xsltSrc, outputRes);   
  }

  private static void doTransform(
      Source inputSrc, 
      Source xsltSrc,
      Result outputSrc)
  throws TransformerException {
    if (inputSrc == null) {
      throw new IllegalArgumentException("inputSrc == null");
    }
    if (xsltSrc == null) {
      throw new IllegalArgumentException("xsltSrc == null");
    }
    if (outputSrc == null) {
      throw new IllegalArgumentException("outputSrc == null");
    }
    
    TransformerFactory tf = TransformerFactory.newInstance();
    Transformer transformer = tf.newTransformer(xsltSrc);
    transformer.transform(inputSrc, outputSrc);
  }
  
  /**
   * Test program
   * @param args
   * @throws TransformerException
   */
  public static void main(String[] args)
  throws TransformerException {
    if (args.length != 3) {
      System.out.println("Usage: java Transform input.xml stylesheet.xslt output.html"); 
    }
    else {
      System.out.println("Transform");
      transform(args[0], args[1], args[2]);
    }
  }
}
