/*
 * Created on Aug 1, 2006
 *
 */
package nl.home.ttilma.ped.dom.maps;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import nl.home.ttilma.ped.dom.Animal;
import nl.home.ttilma.ped.dom.Kennel;
import nl.home.ttilma.utils.Strings;

/**
 * @author ttilma
 */
public class AnimalToKennelMap {
	private static Log log = LogFactory.getLog("AnimalToKennelMap");
	
	private KennelMap kennelMap;
	private Map<String, Kennel> animalToKennelMap = new HashMap<String, Kennel>();
	private Map<String, Kennel> animalToKennelMap2 = new HashMap<String, Kennel>();
	
	public void setKennelMap(KennelMap kennelMap) {
		this.kennelMap = kennelMap;
	}
	
	public Kennel findKennelByAnimalId1(String animalId) {
		return animalToKennelMap.get(animalId);
	}
	
	public Kennel findKennelByAnimalId2(String animalId) {
		return animalToKennelMap2.get(animalId);
	}
	
	public void addAnimal(Animal animal) {
		addAnimalForKennel(animal.getAnimalid(), animal.getKennelid(), animalToKennelMap);
		
		if (animal.getKennelId2() != null) {
			addAnimalForKennel(animal.getAnimalid(), animal.getKennelId2(), animalToKennelMap2);			
		}
	}
	
	private void addAnimalForKennel(String animalId, String kennelId, Map<String, Kennel> atokmap) {
		if (!Strings.isEmpty(kennelId)) {
			Kennel kennel = kennelMap.findKennel(kennelId);
			if (kennel == null) {
				log.error("Kennel >" + kennelId + "< not found");
			} else {
				atokmap.put(animalId, kennel);
				kennel.addAnimal();
			}
		}
	}
}
