/*
 * Created on Jul 9, 2006
 *
 */
package nl.home.ttilma.ped.dom;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * @author ttilma
 */
@XmlRootElement
public class Kennel implements Comparable<Kennel> {
  
	private String kennelid;
	private String name;
	private String breederId;
	private boolean nameBeforeKennel;
	private String breeder;
	private String comment;
	private String foundationDate;
	private String country;
	
	private int nAnimals;
	
	public Kennel() {
		//System.out.println("creating Kennel");	
	}
	
	/**
	 * @return Returns the id.
	 */
	@XmlAttribute
	public String getKennelid()
	{
		return kennelid;
	}
	
	/**
	 * @param id The id to set.
	 */
	public void setKennelid(String kennelid)
	{
		//System.out.println("Kennel.setKennelid=" + kennelid);
		this.kennelid = kennelid;
	}
	
	/**
	 * @return Returns the name.
	 */
	@XmlElement
	public String getName()
	{
		return name;
	}
	
	/**
	 * @param name The name to set.
	 */
	public void setName(String name)
	{
		//System.out.println("Kennel.setName=" + name);
		this.name = name;
	}
	
	/**
	 * @return Returns the breederId.
	 */
	@XmlElement
	public String getBreeder()
	{
		return breeder;
	}
	
	/**
	 * @param breederId The breederId to set.
	 */
	public void setBreeder(String ownerId)
	{
		this.breeder = ownerId;
	}
	
	/**
	 * @return Returns the nameBeforeKennel.
	 */
	@XmlElement (name="namebeforekennel")
	public boolean isNameBeforeKennel()
	{
		return nameBeforeKennel;
	}
	
	/**
	 * @param nameBeforeKennel The nameBeforeKennel to set.
	 */
	public void setNameBeforeKennel(boolean nameBeforeKennel)
	{
		//System.out.println("kennel.setNameBeforeKennel=" + nameBeforeKennel);
		this.nameBeforeKennel = nameBeforeKennel;
	}
	
	public void setNbk(boolean ignore)
	{
		this.nameBeforeKennel = true;
	}
	
	public void setKbn(boolean ignore)
	{
		this.nameBeforeKennel = false;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Kennel o)
	{
		Kennel other =(Kennel) o;
		return name.compareTo(other.name);
	}
	/**
	 * @return Returns the nAnimals.
	 */
	public int getNAnimals()
	{
		return nAnimals;
	}
	/**
	 * @param animals The nAnimals to set.
	 */
	public void setNAnimals(int animals)
	{
		nAnimals = animals;
	}
	
	public void addAnimal()
	{
		nAnimals++;
	}
	
	
	/*public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("Kennel(id=");          sb.append(id);
		sb.append(", name=");             sb.append(name);
		sb.append(", breederId=");        sb.append(breederId);
		sb.append(", nameBeforeKennel="); sb.append(nameBeforeKennel);
		sb.append(")");    
		return sb.toString();
	}*/
	@XmlElement
	public String getBreederId() {
		return breederId;
	}
	public void setBreederId(String breederId) {
		this.breederId = breederId;
	}
	@XmlElement
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@XmlElement
	public String getFoundationDate() {
		return foundationDate;
	}
	public void setFoundationDate(String foundationDate) {
		this.foundationDate = foundationDate;
	}
	@XmlElement
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
}
