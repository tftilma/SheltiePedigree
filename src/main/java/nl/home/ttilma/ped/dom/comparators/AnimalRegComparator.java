/*
 * Created on Sep 10, 2006
 *
*/
package nl.home.ttilma.ped.dom.comparators;

import java.util.Comparator;

import nl.home.ttilma.ped.dom.Animal;
import nl.home.ttilma.ped.dom.Kennel;
import nl.home.ttilma.ped.dom.maps.PedigreeMaps;

/**
 * @author ttilma
 */
public class AnimalRegComparator implements Comparator<Animal> {
	private PedigreeMaps maps;
	
	public AnimalRegComparator(PedigreeMaps maps) {
		this.maps = maps;	
	}
	
	public int compare(Animal o1, Animal o2) {
		if (o1 == null) {
			if (o2 == null) {
				return 0;
	        } else {
	        	return -1;
	        }
	    } else {
	    	 if (o2 == null) {
	           return 1;
	         } else {
	         	Animal a1 = (Animal) o1;
	            Animal a2 = (Animal) o2;
	            Kennel k1 = maps.findKennelById(a1.getKennelid());
	            Kennel k2 = maps.findKennelById(a2.getKennelid());
	            
	            int kennelComp = maps.getKennelComparator().compare(k1, k2);
	            if (kennelComp != 0) {
	            	return kennelComp;
	            } else {
	            	return a1.getName().compareTo(a2.getName());
	            }
	         }
	    }
	}

	

}
