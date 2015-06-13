/*
 * Created on Mar 3, 2005
 *
*/
package nl.home.ttilma.ped.dom.comparators;

import java.util.Comparator;

import nl.home.ttilma.ped.dom.Kennel;

/**
 * @author ttilma
 */
public class KennelsWithMostAnimalsComparator implements Comparator<Kennel>
{
  /* (non-Javadoc)
   * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
   */
  public int compare(Kennel o1, Kennel o2)
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
      Kennel k1 = (Kennel) o1;
      Kennel k2 = (Kennel) o2;
      return k2.getNAnimals() - k1.getNAnimals(); 
    }
  }

}

