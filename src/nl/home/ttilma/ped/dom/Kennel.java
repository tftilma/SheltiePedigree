/*
 * Created on Jul 9, 2006
 *
 */
package nl.home.ttilma.ped.dom;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * @author ttilma
 */
public class Kennel implements Comparable {
	private String kennelid;
	private String name;
	private String breederId;
	private boolean nameBeforeKennel;
	private String breeder;
	private String comment;
	private String foundationDate;
	private String country;
	
	private int nAnimals;
	
	//public Kennel() {
	//	System.out.println("creating Kennel");	
	//}
	
	/**
	 * @return Returns the id.
	 */
	public String getKennelId()
	{
		return kennelid;
	}
	
	/**
	 * @param id The id to set.
	 */
	public void setKennelId(String kennelid)
	{
		//System.out.println("Kennel.setKennelId=" + kennelid);
		this.kennelid = kennelid;
	}
	
	/**
	 * @return Returns the name.
	 */
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
	public String getBreederId()
	{
		return breederId;
	}
	
	/**
	 * @param breederId The breederId to set.
	 */
	public void setBreederId(String ownerId)
	{
		this.breederId = ownerId;
	}
	
	/**
	 * @return Returns the nameBeforeKennel.
	 */
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
	public int compareTo(Object o)
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
	
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
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
	public String getBreeder() {
		return breeder;
	}
	public void setBreeder(String breeder) {
		this.breeder = breeder;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getFoundationDate() {
		return foundationDate;
	}
	public void setFoundationDate(String foundationDate) {
		this.foundationDate = foundationDate;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
}
