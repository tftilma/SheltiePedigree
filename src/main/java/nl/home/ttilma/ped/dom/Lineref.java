/*
 * Created on Aug 1, 2006
 *
*/
package nl.home.ttilma.ped.dom;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * @author ttilma
 */
@XmlRootElement
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

}
