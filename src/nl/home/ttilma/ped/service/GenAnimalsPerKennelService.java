/*
 * Created on Aug 2, 2006
 *
 */
package nl.home.ttilma.ped.service;
import java.util.ArrayList;
import java.util.Collection;
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
import nl.home.ttilma.ped.dom.Kennel;
import nl.home.ttilma.xml.utils.DocumentUtils;
import nl.home.ttilma.xml.utils.Transform;
/**
 * @author ttilma
 */
public class GenAnimalsPerKennelService extends XmlWriterService {
	private static Log log = LogFactory.getLog("GenAnimalsPerKennelService");
		
	public void generate() throws TransformerException,
			ParserConfigurationException {
		Collection kennels = maps.getAllKennels();
		List kennelList = new ArrayList();
		kennelList.addAll(kennels);
		Collections.sort(kennelList);
		for (Iterator kennelIdIter = kennelList.iterator(); kennelIdIter.hasNext();) {
			Kennel kennel = (Kennel) kennelIdIter.next();
			String kennelId = kennel.getKennelId();
			log.debug("generate: kennelId=" + kennelId);
			
			//System.out.println("GENERATING for " + kennelId);
			String kennelFileName = "html//" + kennelId + ".html";
			Document kennelWithAnimalsDoc = generateKennelWithAnimalsDoc(kennelId);
			if (kennelWithAnimalsDoc == null) {
				System.err.println("kennelWithAnimalsDoc == null");
			}
			Transform.transform(kennelWithAnimalsDoc,
					"xml//knlanimals2html.xslt", kennelFileName);
		}
	}
	
	private Document generateKennelWithAnimalsDoc(String kennelId)
			throws ParserConfigurationException {
		destDoc = DocumentUtils.createDocument();
		Node rootNode = generateKennelWithAnimals(kennelId);
		destDoc.appendChild(rootNode);
		return destDoc;
	}
	
	private Node generateKennelWithAnimals(String kennelId)
			throws ParserConfigurationException {
		Node rootNode = destDoc.createElement("root");
		Kennel knl = maps.findKennelById(kennelId);
		log.debug("kennel=" + knl);
		Node kennelNode = destDoc.createElement("kennel");
		kennelNode.appendChild(createTextNode("kennelid", kennelId));
		kennelNode.appendChild(createTextNode("kennelname", knl.getName()));
		rootNode.appendChild(kennelNode);
		//System.out.println("adding kennel:" + kennelId + " - "+
		// knl.getName()+".");
		Node animalsNode = destDoc.createElement("animals");
		List animals = maps.findAnimalsByKennelId(kennelId);
		if (animals != null && !animals.isEmpty()) {
			Collections.sort(animals, maps.getAnimalChComparator());
			for (Iterator iter = animals.iterator(); iter.hasNext();) {
				Animal animal = (Animal) iter.next();
				//System.out.println(animal);
				log.debug("animal=" +animal);
				Node animalNode = destDoc.createElement("animal");
				animalNode.appendChild(createTextNode("animalid", animal
						.getId()));
				animalNode.appendChild(createTextNode("animaltotalname", animal
						.getTotalName()));
				animalNode.appendChild(createTextNode("containsphoto", ""+animal.containsPhoto()));
				//animalNode.appendChild(createTextNode("nickname", animal
				//		.getNickName()));
				animalNode
						.appendChild(createTextNode("born", animal.getBorn()));
				animalNode.appendChild(createTextNode("kennelid", kennelId));
				animalNode.appendChild(createTextNode("nchildren", Integer
						.toString(animal.getNChildren())));
				animalNode
						.appendChild(createTextNode("line", animal.getLine()));
				animalNode
						.appendChild(createTextNode("fam", animal.getFam()));
				if (animal.getGender() == Animal.FEMALE) {
					// for bitches 5 children is already a lot
					animalNode.appendChild(createTextNode("manychildren",
							animal.getNChildren() >= 5 ? "1" : "0"));
				} else {
					// for males and unknowns it is 10
					animalNode.appendChild(createTextNode("manychildren",
							animal.getNChildren() >= 10 ? "1" : "0"));
				}
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
				animalsNode.appendChild(animalNode);
			}
			rootNode.appendChild(animalsNode);
		}
		return rootNode;
	}
}
