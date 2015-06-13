/*
 * Created on Sep 9, 2006
 *
*/
package nl.home.ttilma.ped.service;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import nl.home.ttilma.ped.dom.Animal;
import nl.home.ttilma.xml.utils.DocumentUtils;
import nl.home.ttilma.xml.utils.Transform;

import nl.home.ttilma.ped.dom.helpers.MonthInt2Str;
import nl.home.ttilma.ped.dom.maps.PedigreeMaps;

/**
 * @author ttilma
 */
public class GenKennelGazettes extends XmlWriterService {
	private static Log log = LogFactory.getLog("GenKennelGazette");

	public void generate() throws Exception {
		for (int year=PedigreeMaps.FIRST_REG_YEAR; year <= PedigreeMaps.LAST_REG_YEAR; year++) {
			//log.debug("generate year=" +year);
			genKennelGazettes(year);
		}
		
	}

	private void genKennelGazettes(int year) throws TransformerException, ParserConfigurationException {
	    String fileName = "html//_" + Integer.toString(year)+ ".html";
	    Document statisticsDoc = genKGListDoc(year);
	    
	    Transform.transform(statisticsDoc, "src/main/resources/xml/kennelgazette.xslt", fileName);
	}

	private Document genKGListDoc(int year) throws ParserConfigurationException {
		destDoc = DocumentUtils.createDocument();
		Node rootNode = destDoc.createElement("root");
		rootNode.appendChild(createTextNode("year", Integer.toString(year)));
		for (int month=1; month<=12; month++) {
			List<Animal> animals = maps.findAnimalsPerRegPeriod(year, month);
			Collections.sort(animals, maps.getAnimalRegComparator());
			Node monthNode = destDoc.createElement("animals");
			monthNode.appendChild(createTextNode("month", MonthInt2Str.convert(month)));
			int total = maps.getTotRegsMap().getTotal(year, month);
			monthNode.appendChild(createTextNode("total", Integer.toString(total)));
			int position = 0;
			for (Iterator animalsIter=animals.iterator();			
				animalsIter.hasNext(); ) {
				position++;
				Animal animal = (Animal) animalsIter.next();
				addAnimslToKG(monthNode, position, animal);
				//log.info("adding " + animal.getId() + " to " + year + " " + month);
			}
			monthNode.appendChild(createTextNode("mytotal", Integer.toString(position)));
			rootNode.appendChild(monthNode);
		}
		destDoc.appendChild(rootNode);
		
		return destDoc; 
	}

	private void addAnimslToKG(Node animalsNode, int position, Animal animal) {
		Node animalNode = destDoc.createElement("animal");
		animalNode.appendChild(createTextNode("position", Integer.toString(position)));
		animalNode.appendChild(createTextNode("animalid", animal.getAnimalid()));
		animalNode.appendChild(createTextNode("animaltotalname", animal.getTotalName()));
		String gender;
		switch (animal.getGender()) {
			case Animal.MALE :
				gender = "dog";
				break;
			case Animal.FEMALE :
				gender = "bitch";
				break;
			default :
				gender = "unknown";
		}
		animalNode.appendChild(createTextNode("gender", gender));
		animalNode.appendChild(createTextNode("born", animal.getBorn()));
		animalNode.appendChild(createTextNode("line", animal.getLine()));
		animalNode.appendChild(createTextNode("fam", animal.getFam()));
		
		if (animal.getFatherId() != null) {
			Animal father = maps.findAnimalById(animal.getFatherId());
			if (father == null) {
				//log.error("Could not find father of " + animal.getId());
			} else {
				animalNode.appendChild(createTextNode("fatherid", father.getAnimalid()));
				animalNode.appendChild(createTextNode("fathername", father.getTotalName()));
			}
		}
		
		if (animal.getMotherId() != null) {
			Animal mother = maps.findAnimalById(animal.getMotherId());
			if (mother == null) {
				//log.error("Could not find mother of " + animal.getId());
			} else {
				animalNode.appendChild(createTextNode("motherid", mother.getAnimalid()));
				animalNode.appendChild(createTextNode("mothername", mother.getTotalName()));
			}
		}
		
		animalsNode.appendChild(animalNode);
	}
}
