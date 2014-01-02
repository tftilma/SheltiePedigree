/*
 * Created on Sep 10, 2006
 *
*/
package nl.home.ttilma.ped.dom.maps;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author ttilma
 */
public class TotRegsMap {
	private static Log log = LogFactory.getLog("TotRegsMap");
	
	private int[][] totals;
	private int startYear;
	//private int endYear;
	
	public TotRegsMap(int startYear, int endYear) {
		if (log.isDebugEnabled()) log.debug("");
		totals = new int[endYear - startYear+1][];
		this.startYear = startYear;
		//this.endYear = endYear;
		for (int year = startYear; year<= endYear; year++) {
			totals[year - startYear] = new int[12];
		}
	}
	
	public void setTotal(int year, int month, int total) {
		totals[year - startYear][month - 1] = total;
	}
	
	public int getTotal(int year, int month) {
		return totals[year - startYear][month - 1];
	}
	
}
