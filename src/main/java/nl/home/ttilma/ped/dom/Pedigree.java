/*
 * Created on Aug 1, 2006
 *
*/
package nl.home.ttilma.ped.dom;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * @author ttilma
 */
@XmlRootElement (name="Pedigree")
public class Pedigree {
	private static Log log = LogFactory.getLog("Pedigree");
	
	private List<Lineref> linerefs = new LinkedList<Lineref>();
	private List<FamilyRef> familyrefs = new LinkedList<FamilyRef>();
	private List<Kennel> kennels = new LinkedList<Kennel>();
	private List<Animal> animals = new LinkedList<Animal>();
	private List<Reg> regs = new LinkedList<Reg>();
	
	public Pedigree() {
		log.debug("creating Pedigree");
	}
	@XmlElement
	public List<Animal> getAnimal() {
		return animals;
	}
	
	public void addAnimal(Animal animal) {
		log.debug("adding animal" + animal);
		animals.add(animal);
	}
	
	public void setAnimal(List<Animal> animals) {
		this.animals = animals;
	}
	@XmlElement
	public List<Kennel> getKennel() {
		return kennels;
	}
	
	public void setKennel(List<Kennel> kennels) {
	    log.debug("setting kennels size=" + kennels.size());
		this.kennels = kennels;
	}
	
	public void addKennel(Kennel kennel) {
		log.debug("adding kennel " + kennel);
	    kennels.add(kennel);
	}
	@XmlElement
	public List<Reg> getReg() {
		return regs;
	}
	
	public void setReg(List<Reg> regs) {
		log.debug("setting regs");
		this.regs = regs;
	}
	
	public void addReg(Reg reg) {
		log.debug("adding reg=" + reg);
		regs.add(reg);
	}
	
    public List<FamilyRef> getFamilyrefs() {
		return familyrefs;
	}
	
	public void setFamilyrefs(List<FamilyRef> familyrefs) {
		this.familyrefs = familyrefs;
	}
	
	public void addFamilyref(FamilyRef familyref) {
		//System.out.println("adding family="+ familyref);
		log.debug("adding family="+ familyref);
		familyrefs.add(familyref);
	}
	
	public List getLinerefs() {
		return linerefs;
	}
	
	public void addLineref(Lineref line) {
		log.debug("adding line=" + line);
		linerefs.add(line);
	}
	
	public void setLinerefs(List<Lineref> linerefs) {
		this.linerefs = linerefs;
	}
}
