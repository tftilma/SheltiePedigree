/*
 * Created on Aug 1, 2006
 *
*/
package nl.home.ttilma.ped.dom.maps;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import nl.home.ttilma.ped.dom.Kennel;

/**
 * @author ttilma
 */
public class KennelMap {
	private static Log log = LogFactory.getLog("KennelMap");
	
	private Map<String, Kennel> kennelMap = new HashMap<String, Kennel>(); // Map<kennelid:String> --> Kennel
	
	public void addKennel(Kennel kennel) {
		log.debug("add kennel to kennelMap");
		kennelMap.put(kennel.getKennelId(), kennel);
	}
	
	public Kennel findKennel(String kennelId) {
		return (Kennel) kennelMap.get(kennelId);
	}
	
	public int getNKennels() {
		return kennelMap.size();
	}

	public Collection getAllKennels() {
		return kennelMap.values();
	}
}
