/*
 * Created on Mar 3, 2005
 *
*/
package nl.home.ttilma.ped.dom.comparators;

import java.util.Comparator;

import nl.home.ttilma.ped.dom.Animal;

/**
 * @author ttilma
 */
public class AnimalsWithMostOffspringComparator implements Comparator<Animal>
{

  /* (non-Javadoc)
   * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
   */
  public int compare(Animal o1, Animal o2)
  {
    if (o1 == null)
    {
      if (o2 == null)
      {
        return 0;
      } 
      else
      {
        return 1;
      }
    }
    else if (o2 == null) 
    {
      return -1;
    }
    else
    {
    	Animal a1 = (Animal) o1;
    	Animal a2 = (Animal) o2;
    	return a2.getNChildren() - a1.getNChildren(); 
    }
  }

}
