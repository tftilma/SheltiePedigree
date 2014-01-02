/*
 * Created on Mar 3, 2007
 *
*/
package nl.home.ttilma.ped.dom;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

/**
 * @author ttilma
 */
public class Champion {
	//private static Log log = LogFactory.getLog(Champion.class);
	
	private String country="";
	private String titled="";
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getTitled() {
		return titled;
	}
	
	public void setTitled(String titled) {
		this.titled = titled;
	}
}
