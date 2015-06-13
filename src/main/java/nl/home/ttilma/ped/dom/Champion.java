/*
 * Created on Mar 3, 2007
 *
*/
package nl.home.ttilma.ped.dom;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author ttilma
 */
@XmlRootElement
public class Champion {
	private static Log log = LogFactory.getLog(Champion.class);
	
	private String country="";
	private String titled="";
	
	@XmlAttribute
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	@XmlAttribute
	public String getTitled() {
		return titled;
	}
	
	public void setTitled(String titled) {
		this.titled = titled;
	}
}
