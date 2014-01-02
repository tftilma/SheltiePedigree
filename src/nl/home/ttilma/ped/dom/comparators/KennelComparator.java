/*
 * Created on Sep 10, 2006
 *
*/
package nl.home.ttilma.ped.dom.comparators;

import java.util.Comparator;

import nl.home.ttilma.ped.dom.Kennel;

/**
 * @author ttilma
 */
public class KennelComparator implements Comparator {
		public int compare(Object o1, Object o2) {
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
		         	Kennel k1 = (Kennel) o1;
		            Kennel k2 = (Kennel) o2;
		            return k1.getName().compareTo(k2.getName());	         	
		         }
		    }
		}

}
