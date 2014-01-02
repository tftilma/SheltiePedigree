/*
 * Created on Jan 14, 2005
 *
*/
package nl.home.ttilma.ped.dom.comparators;

import java.util.Comparator;

import nl.home.ttilma.ped.dom.Animal;
import nl.home.ttilma.utils.Strings;

/**
 * @author ttilma
 */
public class AnimalChYearComparator implements Comparator
{

  /* (non-Javadoc)
   * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
   */
  public int compare(Object o1, Object o2)
  {
    if (o1 == null)
    {
      if (o2 == null)
      {
        return 0;
      }
      else 
      {
        return -1;
      }
    }
    else
    {
      if (o2 == null)
      {
        return 1;
      }
      
      Animal a1 = (Animal) o1;
      Animal a2 = (Animal) o2;
      if (Strings.isEmpty(a1.getChyear()))
      {
        if (Strings.isEmpty(a2.getChyear()))
        {
        	AnimalNameComparator ac = new AnimalNameComparator();
        	return ac.compare(a1, a2);
        }
        else
        {
          return 1;          
        } 
      }
      else
      {
        if (Strings.isEmpty(a2.getChyear()))
        {
          return -1;
        }
        else
        {
          int chyear1 = Integer.parseInt(a1.getChyear());
          int chyear2 = Integer.parseInt(a2.getChyear());
          
          if (chyear1 == chyear2)
          {
          	AnimalNameComparator ac = new AnimalNameComparator();
            return ac.compare(a1, a2);
          }
          else 
          {
            return chyear1 - chyear2;
          }
        } 
      }
    }
  }
}
