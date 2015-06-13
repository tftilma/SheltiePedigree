/*
 * Created on Jan 14, 2005
 *
*/
package nl.home.ttilma.ped.dom.comparators;

import java.util.Comparator;

import nl.home.ttilma.ped.dom.Animal;


/**
 * @author ttilma
 */
public class AnimalChComparator implements Comparator<Animal>
{

  /* (non-Javadoc)
   * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
   */
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
      if (a1.isChampion()) {
        if (a2.isChampion()) {
        	AnimalNameComparator ac = new AnimalNameComparator();
        	return ac.compare(a1, a2);
        } else {
          return -1;
        } 
      } else {
        if (a2.isChampion()) {
          return 1;
        } else {
          	AnimalNameComparator ac = new AnimalNameComparator();
            return ac.compare(a1, a2);
        } 
      }
    }
  }
}
