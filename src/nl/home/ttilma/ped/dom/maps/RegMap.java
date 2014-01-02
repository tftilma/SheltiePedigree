/*
 * Created on Sep 10, 2006
 *
*/
package nl.home.ttilma.ped.dom.maps;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import nl.home.ttilma.ped.dom.Animal;
import nl.home.ttilma.ped.dom.Reg;

/**
 * @author ttilma
 */
public class RegMap {
	private static Log log = LogFactory.getLog("RegMap");
	private static int MONTHS_PER_YEAR = 12;
	private List<Animal>[][] regsPerYear; 
	private int startYear;
	//private int endYear;
	
	@SuppressWarnings("unchecked")
	public RegMap(int startYear, int endYear) {
		if (log.isDebugEnabled()) log.debug("RegMap");
		this.startYear = startYear;
		//this.endYear = endYear;
		regsPerYear = new List[endYear - startYear + 1][];
		for (int year=startYear; year<=endYear; year++) {
			regsPerYear[year - startYear] = new List[MONTHS_PER_YEAR];
			for (int month=0; month <  MONTHS_PER_YEAR; month++) {
				regsPerYear[year - startYear][month] = new ArrayList<Animal>();
			}
		}
	}
	
	public void addAnimal(Animal animal) {
		Reg reg = animal.getReg();
		if (reg != null) {
			try {
				if (reg.getYear() != null && reg.getMonth() != null) {
					int year = Integer.parseInt(reg.getYear());
					int month = Integer.parseInt(reg.getMonth());
					addRegToAnimal(year, month, animal);
				}
			} catch (RuntimeException e) {
				System.out.println("ERROR: " + animal.getId());
				throw e;
			}
			
		}
	}

	private void addRegToAnimal(int year, int month, Animal animal) {
		findAnimals(year, month).add(animal);
	}
	
	public List<Animal> findAnimals(int year, int month) {
		return regsPerYear[year - startYear][month - 1];
	}
}
