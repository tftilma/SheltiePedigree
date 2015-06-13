/*
 * Created on Jul 9, 2006
 *
*/
package nl.home.ttilma.ped.dom.comparators;

import java.util.Comparator;

import nl.home.ttilma.ped.dom.Animal;

/**
 * @author ttilma
 */
public class AnimalNameComparator implements Comparator<Animal> {
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
	            String k1 = getKennelOrName(a1);
	            String k2 = getKennelOrName(a2); 
	            int knlOrNameCmp = k1.compareTo(k2); 
	            if (knlOrNameCmp != 0) {
	            	return knlOrNameCmp;
	            } else {
		            return a1.getName().compareTo(a2.getName());	         		            	
	            }
	         }
	    }
	}

	private String getKennelOrName(Animal a1) {
		if ("empty".equals(a1.getKennelid())) {
			return a1.getName().toLowerCase();
		} else {
			return a1.getKennelid();
		}
	}
}
