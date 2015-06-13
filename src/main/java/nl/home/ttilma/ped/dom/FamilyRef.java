/*
 * Created on Aug 1, 2006
 *
*/
package nl.home.ttilma.ped.dom;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author ttilma
 */
@XmlRootElement(name="familyref")
public class FamilyRef {
	private String famId;
	private String famName;
	
	@XmlAttribute(name="famid")
	public String getFamId() {
		return famId;
	}
	
	public void setFamId(String famId) {
		this.famId = famId;
	}
	
	@XmlElement(name="famname")
	public String getFamName() {
		return famName;
	}
	
	public void setFamName(String famName) {
		this.famName = famName;
	}
}
