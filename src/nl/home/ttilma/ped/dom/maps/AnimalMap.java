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

import nl.home.ttilma.ped.dom.Animal;

/**
 * @author ttilma
 */
public class AnimalMap {
	private static Log log = LogFactory.getLog("AnimalMap");
	
	private Map<String, Animal> animalMap = new HashMap<String, Animal>(); // Map<animalid:String> --> Animal
	
	public void addAnimal(Animal animal) {
		log.debug("addAnimal");
		if (animalMap.containsKey(animal.getId())) {
			log.error("Duplicate key animalid=" + animal.getId());
		} else {
			animalMap.put(animal.getId(), animal);
		}
	}
	
	public Animal findAnimal(String animalId) {
		return (Animal) animalMap.get(animalId);
	}
	
	public int getNAnimals() {
		return animalMap.size();
	}

	public Collection getAllAnimals() {
		return animalMap.values();
	}
}
