/*
 * Created on Aug 1, 2006
 *
*/
package nl.home.ttilma.ped.dom;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

/**
 * @author ttilma
 */
public class Reg {
	//private static Log log = LogFactory.getLog("Reg");
	
	private String year;
	private String month;
	private String tot;
	//private String renameYear;
	//private String renameMonth;
	private String regId;

	
	public Reg() {
		//System.out.println("creating Reg");
		//if (log.isDebugEnabled()) log.debug("creating Reg");
	}
	
	public String getMonth() {
		return month;
	}
	
	public void setMonth(String month) {
		//System.out.println("setting month=" + month);
		this.month = month;
	}
	
	public String getYear() {
		return year;
	}
	
	public void setYear(String year) {
		//System.out.println("setting year="+year);
		this.year = year;
	}
	
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
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
