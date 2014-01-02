/*
 * Created on Aug 1, 2006
 *
*/
package nl.home.ttilma.ped.dom;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * @author ttilma
 */
public class Pedigree {
	private static Log log = LogFactory.getLog("Pedigree");
	
	private List<Lineref> linerefs = new ArrayList<Lineref>(10);
	private List<Familyref> familyrefs = new ArrayList<Familyref>(30);
	private List<Kennel> kennels = new ArrayList<Kennel>(2700);
	private List<Animal> animals = new ArrayList<Animal>(14000);
	private List<Reg> regs = new ArrayList<Reg>();
	
	public Pedigree() {
		log.debug("creating Pedigree");
	}
	
	public List getAnimals() {
		return animals;
	}
	
	public void addAnimal(Animal animal) {
		log.debug("adding animal" + animal);
		animals.add(animal);
	}
	
	public void setAnimals(List<Animal> animals) {
		this.animals = animals;
	}
	
	public List getKennels() {
		return kennels;
	}
	
	public void setKennels(List<Kennel> kennels) {
		this.kennels = kennels;
	}
	
	public void addKennel(Kennel kennel) {
		log.debug("adding kennel" + kennel);
	    kennels.add(kennel);
	}
	
	public List<Reg> getRegs() {
		return regs;
	}
	
	public void setRegs(List<Reg> regs) {
		log.debug("setting regs");
		this.regs = regs;
	}
	
	public void addReg(Reg reg) {
		log.debug("adding reg=" + reg);
		regs.add(reg);
	}
	
    public List<Familyref> getFamilyrefs() {
		return familyrefs;
	}
	
	public void setFamilyrefs(List<Familyref> familyrefs) {
		this.familyrefs = familyrefs;
	}
	
	public void addFamilyref(Familyref familyref) {
		//System.out.println("adding family="+ familyref);
		log.debug("adding family="+ familyref);
		familyrefs.add(familyref);
	}
	
	public List<Lineref> getLinerefs() {
		return linerefs;
	}
	
	public void addLineref(Lineref line) {
		log.debug("adding line=" + line);
		linerefs.add(line);
	}
	
	public void setLinerefs(List<Lineref> linerefs) {
		this.linerefs = linerefs;
	}
	
	public String toString() {
		return ""; //ReflectionToStringBuilder.toString(this);
	}
	
	
}
