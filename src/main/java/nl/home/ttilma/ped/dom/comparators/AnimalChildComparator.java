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
public class AnimalChildComparator implements Comparator<Animal> {
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
			}
			Animal a1 = (Animal) o1;
			Animal a2 = (Animal) o2;
			if (a1.isChampion()) {
		        if (a2.isChampion()) {
					return parentCompare(a1, a2);
		        } else {
		        	return -1;
		        }
		    } else {
		        if (a2.isChampion()) {
		        	return 1;
		        } else {
					return parentCompare(a1, a2);
		        } 
		    	
		    }
		}
	}
	
	/**
	 * @param a1
	 * @param a2
	 * @return
	 */
	private int parentCompare(Animal a1, Animal a2) {
		int fatherResult = stringComparator(a1.getFatherId(), a2.getFatherId());
		if (fatherResult != 0) {
			return fatherResult;
		} else {
			int motherResult = stringComparator(a1.getMotherId(), a2.getMotherId());
			if (motherResult != 0) {
				return motherResult;
			} else {
				return  stringComparator(a1.getAnimalid(), a2.getAnimalid());
			}
		}
	}

	private int stringComparator(String id1, String id2) {
		if (id1 == null) {
			if (id2 == null) {
				return 0;
			} else {
				return -1;
			}
		} else {
			if (id2 == null) {
				return 1;
			} else {
				return id1.compareTo(id2);
			}
		}
	}
}
