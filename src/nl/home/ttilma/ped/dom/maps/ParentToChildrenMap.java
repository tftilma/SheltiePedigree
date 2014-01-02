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
public class ParentToChildrenMap {
	private static Log log = LogFactory.getLog("ParentToChildrenMap");
	
	private Map<String, List<Animal>> parentToChildrenMap = new HashMap<String, List<Animal>> (); 
	private AnimalMap animalMap;
	
	public void setAnimalMap(AnimalMap animalMap) {
		this.animalMap = animalMap;
	}
	
	public void addAnimal(Animal animal) {
		if (!Strings.isEmpty(animal.getFatherId())) {
			List<Animal> children = findChildren(animal.getFatherId());
			children.add(animal);
		}
		if (!Strings.isEmpty(animal.getMotherId())) {
			List<Animal> children = findChildren(animal.getMotherId());
			children.add(animal);
		}
	}
	
	private List<Animal> findChildren(String parentId) { 
		if (Strings.isEmpty(parentId)) {
			return null;
		} else {
			List<Animal> children;
			if (parentToChildrenMap.containsKey(parentId)) {
				children = findChildrenByParentId(parentId);
			} else {
				children = new LinkedList<Animal>();
				parentToChildrenMap.put(parentId, children);
			}
			Animal parent = animalMap.findAnimal(parentId);
			if (parent != null) {
				parent.addChild();
			} else {
				log.error("No parent for:" + parentId+".");
			}
			return children;
		}
	}
	
	public List<Animal> findChildrenByParentId(String parentId) {
		return (List<Animal>) parentToChildrenMap.get(parentId);
	}
}
