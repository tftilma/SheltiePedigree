/*
 * Created on Aug 1, 2006
 *
*/
package nl.home.ttilma.ped.dom;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * @author ttilma
 */
public class Familyref {
	private String famId;
	private String famName;
	
	public String getFamId() {
		return famId;
	}
	
	public void setFamId(String famId) {
		this.famId = famId;
	}
	
	public String getFamName() {
		return famName;
	}
	
	public void setFamName(String famName) {
		this.famName = famName;
	}
	
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
