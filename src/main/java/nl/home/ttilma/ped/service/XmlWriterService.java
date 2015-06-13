/*
 * Created on Aug 2, 2006
 *
*/
package nl.home.ttilma.ped.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import nl.home.ttilma.ped.dom.maps.PedigreeMaps;

/**
 * @author ttilma
 */
public abstract class XmlWriterService {
	private static Log log = LogFactory.getLog("XmlWriterService");

	protected Document destDoc;
	protected PedigreeMaps maps;
	
	public abstract void generate() throws Exception;
	
	
	public void setPedigreeMap(PedigreeMaps maps) {
		this.maps = maps;
	}
	
	public Node createTextNode(String xmlTag, String value) {
		Node tagNode = null;
		if (xmlTag == null) {
			throw new NullPointerException("no xmltag");
		}
		if (value == null) {
			//throw new NullPointerException("no value for no xmltag=" + xmlTag);
			log.error("null value for no xmltag=" + xmlTag + ".");
		} else {
			if ("".equals(value)) {
				//log.warn("Empty value for xmltag=" + xmlTag + ".");
			} 
			Node valueTextNode = destDoc.createTextNode(value);
			tagNode = destDoc.createElement(xmlTag);
			tagNode.appendChild(valueTextNode);
		}
		return tagNode;
	}
}
