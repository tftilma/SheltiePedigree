/*
 * Created on Jul 9, 2006
 *
 */
package nl.home.ttilma.ped.dom.helpers;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import nl.home.ttilma.ped.dom.Animal;
import nl.home.ttilma.ped.dom.Kennel;


/**
 * @author ttilma
 */
public class TotalNameCreater {
	private static Log log = LogFactory.getLog("TotalNameCreater");

	public String calculateTotalName(Kennel kennel,
			Kennel kennel2, Animal animal) {
		if (kennel == null) {
			log.error("No kennel for " + animal);
		}
		StringBuffer nameSB = new StringBuffer();
		StringBuffer totalNameSB = new StringBuffer();
		addIfChampion(animal, totalNameSB);
		if (kennel2 != null) {
			boolean nameBeforeKennel2;
			if (animal.getNameBeforeKennel2() == null) {
				nameBeforeKennel2 = kennel2.isNameBeforeKennel();
			} else {
				nameBeforeKennel2 = "true".equals(animal.getNameBeforeKennel2());
			}
			/*if ("swwildways".equals(animal.getId())) {
				System.out.println("kennel2.nbk=" +kennel2.isNameBeforeKennel() +".");
				System.out.println("animal.nbk=" +animal.getNameBeforeKennel()+".");
				System.out.println("animal.nbk2=" +animal.getNameBeforeKennel2()+".");				
			}*/
			if (nameBeforeKennel2) {
				nameSB.append(animal.getName());
				nameSB.append(" ");
				nameSB.append(kennel2.getName());				
			} else {
				nameSB.append(kennel2.getName());
				nameSB.append(" ");
				nameSB.append(animal.getName());
			}
		} else {
			nameSB.append(animal.getName());
		}
		
		if (".".equals(kennel.getName())) {
			totalNameSB.append(nameSB);
		} else {		
			boolean nameBeforeKennel;
			if (animal.getNameBeforeKennel() == null) {
				nameBeforeKennel = kennel.isNameBeforeKennel();
			} else {
				nameBeforeKennel = "true".equals(animal.getNameBeforeKennel());
			}
			if (nameBeforeKennel) {
				totalNameSB.append(nameSB);
				totalNameSB.append(" ");
				totalNameSB.append(kennel.getName());
			} else {
				totalNameSB.append(kennel.getName());
				totalNameSB.append(" ");
				totalNameSB.append(nameSB);
				
			}
		}
		if (animal.getStartOfFamily() != null) {
			totalNameSB.append(" (start of ");
			totalNameSB.append(animal.getStartOfFamily());
			totalNameSB.append(")");
		}
		if (animal.isCdx()) {
			totalNameSB.append(" CDX");
		} else if (animal.isCd()) {
			totalNameSB.append(" CD");
		} else {
			// nothing
		}
		if (animal.isUdx()) {
			totalNameSB.append(" UDX");
		} else if (animal.isUd()) {
			totalNameSB.append(" UD");
		} else {
			// nothing
		}
		if (animal.isJw()) {
			totalNameSB.append(" JW");
		} else if (animal.isCw()) {
			totalNameSB.append(" CW ");
			if (animal.getCw() != null) {
				totalNameSB.append(animal.getCw());
			}
		}
		if (animal.isRom()) {
			totalNameSB.append(" ROM");
		}
		if (animal.isRomc()) {
			totalNameSB.append(" ROMC");
		}
		if (animal.isRoma()) {
			totalNameSB.append(" ROMA");
		}
		if (animal.isRomnz()) {
			totalNameSB.append(" ROMNZ");
		}
		if (animal.getSuffix() != null) {
			totalNameSB.append(" ");
			totalNameSB.append(animal.getSuffix());
		}
		return totalNameSB.toString();
	}

	/**
	 * @param animal
	 * @param totalNameSB
	 */
	private static void addIfChampion(Animal animal, StringBuffer totalNameSB) {
		if (animal.isChampion()) {
			totalNameSB.append("Ch. ");
		}
	}
}
