/*
 * Created on Aug 6, 2006
 *
*/
package nl.home.ttilma.ped.dom.list;

import java.util.ArrayList;
import java.util.List;

import nl.home.ttilma.ped.dom.Animal;

/**
 * @author ttilma
 */
public class ChampionList {
	private int maxChs;
	private int nChs;
	private List<Animal> chList;
	
	public ChampionList(int maxChs) {
		chList = new ArrayList<Animal>(maxChs);
		this.maxChs = maxChs;
		nChs = 0;
	}
	
	public List<Animal> getChList() {
		return chList;
	}
	
	public void addCh(Animal animal) {
		if (nChs < maxChs) {
			nChs++;
			chList.add(animal);
		} else {
			throw new IllegalArgumentException("Too many champions");
		}
	}
	
	public Animal findByIdx(int idx) {
		if (idx < 0) {
			throw new IllegalArgumentException("Cannot find Animal for negative index!");
		} else if (idx >= maxChs) {
			throw new IllegalArgumentException("Too large index to find Animal!");
		} else{
			return (Animal) chList.get(idx);
		}		
	}
	
	public int getNChs() {
		return nChs;
	}
}
