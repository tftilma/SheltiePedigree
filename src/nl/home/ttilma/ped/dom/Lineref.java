/*
 * Created on Aug 1, 2006
 *
*/
package nl.home.ttilma.ped.dom;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * @author ttilma
 */
public class Lineref {
	private String id;
	private String name;
	
	//public Lineref() {
	//	System.out.println("creating Lineref");
	//}
	
	public String getLineid() {
		return id;
	}
	
	public void setLineid(String id) {
		this.id = id;
		//System.out.println("setting id=" + id);
	}
	
	public String getLinname() {
		return name;
	}
	public void setLinname(String name) {
		this.name = name;
		//System.out.println("setting name=" + name);
	}

	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
