/*
 * Created on Apr 1, 2007
 *
*/
package nl.home.ttilma.ped.dom;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author ttilma
 */
@XmlRootElement
public class LinesFam {
	private int count=1;
	
	public int getCount() {
		return count;
	}
	public void addCount() {
		count++;
	}
}
