/*
 * Created on Sep 5, 2004
 *
*/
package nl.home.ttilma.utils;

/**
 * @author ttilma
 */
public class Strings
{

  public static boolean isEmpty(String s) {
    return s == null || s.length() == 0;
  }
  
  public static boolean isTrimEmpty(String s) {
	  if (isEmpty(s)) {
		  return true;
	  } else {
		  return s.trim().length() == 0;
	  }
  }
}
