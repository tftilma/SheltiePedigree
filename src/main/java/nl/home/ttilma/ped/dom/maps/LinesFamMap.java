/*
 * Created on Apr 1, 2007
 *
*/
package nl.home.ttilma.ped.dom.maps;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import nl.home.ttilma.ped.dom.LinesFam;


/**
 * @author ttilma
 */
public class LinesFamMap {
	private static Log log = LogFactory.getLog(LinesFamMap.class);
	
	private Map linesMap = new HashMap(); // Map<animalid:String> --> LinesFam
	private int totalShelties = 0;
	

	public void addLine(String line) {
		if (linesMap.containsKey(line)) {
			LinesFam lineCounter = (LinesFam) linesMap.get(line);
			lineCounter.addCount();
		} else {
			linesMap.put(line, new LinesFam());
		}
	}
	
	public int getNLines() {
		return linesMap.size();
	}
	
	public int getCount(String lineName) {
		int lineSize = 0;
		if (linesMap.containsKey(lineName)) {
			LinesFam linesFam = (LinesFam) linesMap.get(lineName);
			lineSize = linesFam.getCount();
		}		
		return lineSize;
	}
	
	public double getPerc(String lineName) {
		double perc = 0.0d;
		if (linesMap.containsKey(lineName)) {
			LinesFam linesFam = (LinesFam) linesMap.get(lineName);
			int lineSize = linesFam.getCount();
			perc = 100*(lineSize / (double) totalShelties);
		}		
		return perc;
	}
	
	public double getNegPerc(String lineName) {
		double perc = 0.0d;
		if (linesMap.containsKey(lineName)) {
			LinesFam linesFam = (LinesFam) linesMap.get(lineName);
			int lineSize = linesFam.getCount();
			perc = 100.0d - (100*(lineSize / (double) totalShelties));
		}		
		return perc;
	}
	
	public void setTotalShelties(int totalShelties) {
		this.totalShelties = totalShelties;
	}
}
