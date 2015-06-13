/*
 * Created on Aug 10, 2006
 *
*/
package nl.home.ttilma.ped.dom.comparators;

import java.util.Comparator;

import nl.home.ttilma.ped.dom.Animal;

/**
 * @author ttilma
 */
public class AnimalBornComparator implements Comparator<Animal> {
	public int compare(Animal o1, Animal o2)
	  {
	    if (o1 == null) {
	      if (o2 == null) {
	        return 0;
	      } else {
	        return -1;
	      }
	    } else {
	      if (o2 == null) {
	        return 1;
	      }
	      
	      Animal a1 = (Animal) o1;
	      Animal a2 = (Animal) o2;
	      if (a1.getBornDate() == null) {
	        if (a2.getBornDate() == null) {
	        	AnimalNameComparator ac = new AnimalNameComparator();
	        	return ac.compare(a1, a2);
	        } else {
	          return -1;          
	        } 
	      } else {
	        if (a2.getBornDate() == null) {
	          return 1;
	        } else {	          	
	            return a1.getBornDate().compareTo(a2.getBornDate());
	        } 
	      }
	    }
	  }
}
