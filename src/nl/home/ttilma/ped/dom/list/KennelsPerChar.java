/*
 * Created on Aug 2, 2006
 *
*/
package nl.home.ttilma.ped.dom.list;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import nl.home.ttilma.ped.dom.Kennel;

/**
 * @author ttilma
 */
public class KennelsPerChar {
	private static Log log = LogFactory.getLog("KennelsPerChar");
	
 	public static final char SMALL_CHAR = 'a';
 	public static final char START_CHAR = 'A';
	public static final char END_CHAR = 'Z';
	public static final int N_ALPHABETS = END_CHAR - START_CHAR + 1;
	  
	private List<Kennel>[] kennelsPerChar; // List<Kennel>
	
	@SuppressWarnings("unchecked")
	public KennelsPerChar() {
		kennelsPerChar = new List[N_ALPHABETS]; 
		for (int idx=0; idx<N_ALPHABETS; idx++) {
			kennelsPerChar[idx] = new LinkedList<Kennel>();
		}
	}
	
	public void addKennel(Kennel kennel) {
		char k = (char)(kennel.getKennelId().charAt(0) - SMALL_CHAR + START_CHAR);
		if (".".equals(kennel.getName())) {
			kennelsPerChar[N_ALPHABETS-1].add(kennel);
		} else {
			if (k < START_CHAR) {
				log.warn("te groot getKennelId = " + kennel.getKennelId() + " = " + k);
			} else if (k > END_CHAR) {
				log.warn("te groot getKennelId = " + kennel.getKennelId() + " = " + k);
			} else {
				kennelsPerChar[k - START_CHAR].add(kennel);
			}
		}
	}
	
	public List[] getKennelsPerChar() {
		return kennelsPerChar;
	}
}
