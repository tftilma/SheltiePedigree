package nl.home.ttilma.xml.sax;


import java.io.IOException;

import nl.home.ttilma.xml.SaxDemo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;


public class SaxFactory<Model extends SaxModel>{
	private static Log log = LogFactory.getLog("SaxFactory");
	//private Map mapping = new HashMap(); 
	
	public SaxFactory(Model mapping, String fileName) 
	throws SAXException, IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		log.debug("SaxFactory fileName=" + fileName);
		String uri = SaxDemo.class.getResource(fileName).toExternalForm();
		SaxHandler handler = new SaxHandler<Model>(mapping);
		InputSource input = new InputSource(uri);
		XMLReader xr = XMLReaderFactory.createXMLReader();
		xr.setContentHandler(handler);
		xr.setErrorHandler(handler);
		xr.parse(input);
		SaxModel model = (SaxModel) handler.getModel();
		System.out.println("model = " + model);
	}
	
}
