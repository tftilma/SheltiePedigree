/*
 * Created on Aug 1, 2006
 *
*/
package nl.home.ttilma.ped.service;

import java.beans.IntrospectionException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.commons.betwixt.XMLIntrospector;
import org.apache.commons.betwixt.io.BeanReader;
import org.xml.sax.SAXException;

import nl.home.ttilma.ped.dom.Pedigree;

/**
 * @author ttilma
 */
public class SheltieXmlToBeans {
	
	public Pedigree readSheltieXml(String filePath) 
	throws IntrospectionException, IOException, SAXException {
		//InputStream is =  
		Reader r = new FileReader(filePath);
		
		BeanReader br = new BeanReader();
		XMLIntrospector xi = br.getXMLIntrospector();
		xi.setWrapCollectionsInElement(false); // must be set to false !!
		br.registerBeanClass(Pedigree.class);
		
		 // br.getBindingConfiguration().setMapIDs(false);
		Pedigree ped = (Pedigree) br.parse(r);
		return ped;
	}
	
}
