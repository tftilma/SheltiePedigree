/*
 * Created on Aug 10, 2006
 *
*/
package nl.home.ttilma.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ttilma
 */
public class Dates {
	private static final String DD_MM_YYYY = "dd-MM-yyyy";
	private DateFormat ddMmYyyy = new SimpleDateFormat(DD_MM_YYYY);

	public Date calcDateDdMmYyyy(String dateString) throws ParseException {
		if (Strings.isEmpty(dateString)) {
			return null;			
		//} else if (dateString.length() != DD_MM_YYYY.length()) {
		//	throw new ParseException("DateString length problem in >"+dateString+"< !", 0);			
		} else {
			if (dateString.length() == 4) {
				dateString = "01-01-" + dateString;
			} else if (dateString.length() != DD_MM_YYYY.length()) {
				throw new ParseException("DateString length problem in >"+dateString+"< !", 0);
			}
			
			StringBuffer newDateSb = new StringBuffer();
			// CHAR 0
			if (dateString.charAt(0) == '?') {
				newDateSb.append('0');				
			} else {
				newDateSb.append(dateString.charAt(0));	
			}
			
			// CHAR 1
			if (dateString.charAt(1) == '?') {
				newDateSb.append('1');				
			} else {
				newDateSb.append(dateString.charAt(1));	
			}
			
			// CHAR 2
			newDateSb.append('-');
			
			// CHAR 3
			if (dateString.charAt(3) == '?') {
				newDateSb.append('0');				
			} else {
				newDateSb.append(dateString.charAt(3));	
			}
			
			// CHAR 4
			if (dateString.charAt(4) == '?') {
				newDateSb.append('1');				
			} else {
				newDateSb.append(dateString.charAt(4));	
			}
			
			// CHAR 5
			newDateSb.append('-');
			
			// CHAR 6
			newDateSb.append(dateString.substring(6));
			return ddMmYyyy.parse(newDateSb.toString());
		}
	}
}
