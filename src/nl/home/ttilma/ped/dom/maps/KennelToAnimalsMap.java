/*
 * Created on Aug 2, 2006
 *
*/
package nl.home.ttilma.ped.dom.maps;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import nl.home.ttilma.ped.dom.Animal;
import nl.home.ttilma.utils.Strings;

/**
 * @author ttilma
 */
public class KennelToAnimalsMap {
	private static Log log = LogFactory.getLog("KennelToAnimalsMap");
	public Map<String, List> kennelToAnimalsMap = new HashMap<String, List>(); // Map<kennelid:String> --> List<Animal>

	private void addAnimalByKennelId(Animal animal, String kennelId) {
		if (!Strings.isEmpty(kennelId)) {
			List<Animal> animals;
			if (log.isDebugEnabled()) log.info("addAnimalByKennelId " + animal.getId() + " - " + kennelId);
			if (kennelToAnimalsMap.containsKey(kennelId)) {
				// kennelId bestond al
				animals = findAnimalsByKennelId(kennelId);
			} else {
				// kennelId bestaat nog niet
				animals = new LinkedList<Animal>();
				kennelToAnimalsMap.put(kennelId, animals);
			}
			animals.add(animal);
		}
	}
	
	public void addAnimal(Animal animal) {
		addAnimalByKennelId(animal, animal.getKennelId());
		addAnimalByKennelId(animal, animal.getKennelId2());
	}
	
	@SuppressWarnings("unchecked")
	public List<Animal> findAnimalsByKennelId(String kennelId) {
		if (Strings.isEmpty(kennelId)) {
			return null;
		} else {
			List<Animal> list =  kennelToAnimalsMap.get(kennelId);
			return list;
		}
	}
}
