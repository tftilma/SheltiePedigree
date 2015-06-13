/*
 * Created on Sep 10, 2006
 *
*/
package nl.home.ttilma.ped.dom.helpers;

/**
 * @author ttilma
 */
public class MonthInt2Str {
	private static String[] monthNames = new String[] {
			"January", "February", "March", "April",
			"May", "June", "July", "August",
			"September", "October", "November", "December"};
	
	public static String convert(int monthNum) {
		return monthNames[monthNum - 1];
	}
}
