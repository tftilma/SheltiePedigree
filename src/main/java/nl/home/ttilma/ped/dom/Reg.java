/*
 * Created on Aug 1, 2006
 *
*/
package nl.home.ttilma.ped.dom;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author ttilma
 */
@XmlRootElement
public class Reg {
	private static Log log = LogFactory.getLog("Reg");
	
	private String year;
    
	private String month;
    
	private String tot;
	private String regId;

	
	public Reg() {
		//System.out.println("creating Reg");
		//log.info("creating Reg");
	}
	@XmlElement(name="regmonth")
	public String getRegMonth() {
		return month;
	}
	
	public void setRegMonth(String month) {
		//System.out.println("setting month=" + month);
		this.month = month;
	}
	@XmlElement(name="regyear")
	public String getRegYear() {
		return year;
	}
	
	public void setRegYear(String year) {
		//System.out.println("setting year="+year);
		this.year = year;
	}
	
	@XmlElement(name="regtot")
	public String getTot() {
		return tot;
	}
	public void setTot(String tot) {
		this.tot = tot;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
}
