/*
 * Created on Aug 3, 2006
 *
 */
package nl.home.ttilma.ped.service;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import nl.home.ttilma.ped.dom.Animal;
import nl.home.ttilma.ped.dom.Kennel;
import nl.home.ttilma.ped.dom.comparators.AnimalChYearComparator;
import nl.home.ttilma.utils.Strings;
import nl.home.ttilma.xml.utils.DocumentUtils;
import nl.home.ttilma.xml.utils.Transform;
/**
 * @author ttilma
 */
public class GenPedigreesOld extends XmlWriterService {
	private static Log log = LogFactory.getLog("GenPedigreeOld");
	
	private static final boolean WITHCHILDREN = true;
	private static final int MAXLEVEL = 7;
	private Document srcDoc;
	
	public void generate() throws Exception {
		int i = 0;
		Iterator iter;
		for (iter = maps.getAllAnimals().iterator(); iter.hasNext();) {
			i++;
			Animal animal = (Animal) iter.next();
			String animalId = animal.getId();
			
			generatePedigreeDocument(animalId);
			String pedFileName = "html/" + animalId + ".html";
			Transform.transform(destDoc, "xml/ped2html.xslt", pedFileName);			
		}
	}
	
	public void generatePedigreeDocument(String animalId)
			throws ParserConfigurationException {
		//System.out.println("STEP 5a: Generating pedigree for " + animalId);
		if (animalId != null && !"".equals(animalId)) {
			destDoc = DocumentUtils.createDocument();
			Node rootNode = generatePedigree(animalId);
			destDoc.appendChild(rootNode);
		}
	}
	private Node generatePedigree(String animalId) {
		int nAnimalInPedigree = (int) Math.pow(2.0, MAXLEVEL);
		String[] namesArr = new String[nAnimalInPedigree];
		String[] idArr = new String[nAnimalInPedigree];
		Node rootNode = destDoc.createElement("root");
		Node childNode = destDoc.createElement("childNode");
		Node pedigreeNode = destDoc.createElement("pedigree");
		generatePedigree(pedigreeNode, animalId, 1, 1, namesArr, idArr);
		// Add the name and id of the child (for convenience)
		Animal animal = maps.findAnimalById(animalId);
		if (animal == null) {
			throw new NullPointerException("animal = null:  " + animalId);
		}
		String kennelId = animal.getKennelId();
		Kennel knl = maps.findKennelById(kennelId);
		String kennelName = knl.getName();
		String kennelId2 = animal.getKennelId2();
		Kennel knl2 = null;
		String kennelName2 = null;
		if (kennelId2 != null) {
			knl2 = maps.findKennelById(kennelId2);
			kennelName2 = knl2.getName();
		}
		createChildNode(destDoc, childNode, animal, idArr[1], 1);
		//createChampNode(destDoc, rootNode, animal, idArr[1], 1);
		rootNode.appendChild(childNode);
		// Add all names in the pedigree-array
		for (int idx = 1; idx < namesArr.length; idx++) {
			String totalName = namesArr[idx];
			String idVal = idArr[idx];
			if (!Strings.isEmpty(totalName)) {
				createAnimalNode(pedigreeNode, totalName, idVal, idx);
			}
		}
		rootNode.appendChild(pedigreeNode);
		if (WITHCHILDREN) {
			// Add descendants (nakomelingen)
			generateDescendants(rootNode, animalId);
		}
		return rootNode;
	}
	
	private void generateDescendants(Node rootNode, String animalId) {
		//System.out.println("generateDescendants for " + animalId);
		Node descendantsNode = destDoc.createElement("descendants");
		List list = maps.findChildrenByParent(animalId);
		if (list != null) {
			// Sort the list of animals alphabetic
			Collections.sort(list, new AnimalChYearComparator());
			int nr = 0;
			for (Iterator iter = list.iterator(); iter.hasNext();) {
				nr++;
				Animal descendant = (Animal) iter.next();
				createDescendant(descendantsNode, descendant, nr);
			}
		}
		rootNode.appendChild(descendantsNode);
	}
	
	private void createDescendant(Node descendantsNode, Animal childAnimal,
			int nr) {
		Node descNode = destDoc.createElement("descendant");
		descNode.appendChild(createTextNode("nr", Integer.toString(nr)));
		descNode.appendChild(createTextNode("id", childAnimal.getId()));
		descNode
				.appendChild(createTextNode("name", childAnimal.getTotalName()));
		descNode.appendChild(createTextNode("gender",
				childAnimal.getGender() == Animal.MALE ? "dog" : childAnimal
						.getGender() == Animal.FEMALE ? "bitch" : ""));
		descNode.appendChild(createTextNode("nchildren", Integer
				.toString(childAnimal.getNChildren())));
		Animal father = null;
		Animal mother = null;
		String fatherId = "?";
		String motherId = "?";
		String fatherStr = "?";
		String motherStr = "?";
		if (childAnimal.getFatherId() != null) {
			fatherId = childAnimal.getFatherId();
			father = maps.findAnimalById(fatherId);
			if (father != null) {
				fatherStr = father.getTotalName();
			}
		}
		if (childAnimal.getMotherId() != null) {
			motherId = childAnimal.getMotherId();
			mother = maps.findAnimalById(motherId);
			if (mother != null) {
				motherStr = mother.getTotalName();
			}
		}
		log.info("fatherid=" + fatherId);
		log.info("fathername=" + fatherStr);
		log.info("motherid=" + motherId);
		log.info("mothername=" + motherStr);
		descNode.appendChild(createTextNode("fatherid", fatherId));
		descNode.appendChild(createTextNode("fathername", fatherStr));
		descNode.appendChild(createTextNode("motherid", motherId));
		descNode.appendChild(createTextNode("mothername", motherStr));
		descendantsNode.appendChild(descNode);
	}
	
	private void createAnimalNode(Node pedigreeNode, String totalName,
			String idVal, int idx) {
		Node animalNode;
		animalNode = destDoc.createElement("animal");
		log.info("totalName=" + totalName);
		log.info("id=" + idVal);
		log.info("idx=" + Integer.toString(idx));
		animalNode.appendChild(createTextNode("name", totalName));
		animalNode.appendChild(createTextNode("id", idVal));
		animalNode.appendChild(createTextNode("idx", Integer.toString(idx)));
		pedigreeNode.appendChild(animalNode);
	}
	
	private void createChildNode(Document destDoc, Node rootNode,
			Animal animal, String idVal, int idx) {
		Node animalNode = destDoc.createElement("child");
		createChampNode(destDoc, animalNode, animal);
		String kennelId = animal.getKennelId();
		Kennel knl = maps.findKennelById(kennelId);
		String kennelName = knl.getName();
		String kennelId2 = animal.getKennelId2();
		Kennel knl2 = null;
		String kennelName2 = "";
		if (!Strings.isEmpty(kennelId2)) {
			knl2 = maps.findKennelById(kennelId2);
			kennelName2 = knl2.getName();
		}
		
		Node kennelIdNode = destDoc.createElement("kennelid");
		log.info("kennelId=" + kennelId);
		kennelIdNode.appendChild(destDoc.createTextNode(kennelId));
		animalNode.appendChild(kennelIdNode);
		
		Node kennelNameNode = destDoc.createElement("kennelname");
		log.info("kennelName=" + kennelName);
		kennelNameNode.appendChild(destDoc.createTextNode(kennelName));
		animalNode.appendChild(kennelNameNode);
		
		Node kennelIdNode2 = destDoc.createElement("kennelid2");
		kennelIdNode2.appendChild(destDoc.createTextNode(kennelId2));
		animalNode.appendChild(kennelIdNode2);
		
		Node kennelNameNode2 = destDoc.createElement("kennelname2");
		kennelNameNode2.appendChild(destDoc.createTextNode(kennelName2));
		animalNode.appendChild(kennelNameNode2);
		
		Node nameNode = destDoc.createElement("name");
		nameNode.appendChild(destDoc.createTextNode(animal.getTotalName()));
		animalNode.appendChild(nameNode);
		
		Node idText = destDoc.createTextNode(idVal);
		Node idNode = destDoc.createElement("id");
		idNode.appendChild(idText);
		animalNode.appendChild(idNode);
		
		Node idxText = destDoc.createTextNode(Integer.toString(idx));
		Node idxNode = destDoc.createElement("idx");
		idxNode.appendChild(idxText);
		animalNode.appendChild(idxNode);
		
		animalNode.appendChild(createTextNode("born", animal.getBorn()));
		//Node bornNode = destDoc.createElement("born");
		//bornNode.appendChild(destDoc.createTextNode(animal.getBorn()));
		//animalNode.appendChild(bornNode);
		Node colorNode = destDoc.createElement("color");
		colorNode.appendChild(destDoc.createTextNode(animal.getColor()));
		animalNode.appendChild(colorNode);
		
		Node lineNode = destDoc.createElement("line");
		lineNode.appendChild(destDoc.createTextNode(animal.getLine()));
		animalNode.appendChild(lineNode);
		
		Node familyNode = destDoc.createElement("family");
		familyNode.appendChild(destDoc.createTextNode(animal.getFam()));
		animalNode.appendChild(familyNode);
		/*
		 * if (animal.getPrevEngChId() != null) System.out.println("Prev Eng Ch =" +
		 * animal.getPrevEngChId()); if (animal.getNextEngChId() != null)
		 * System.out.println("Next Eng Ch =" + animal.getNextEngChId()); if
		 * (animal.getPrevNlChId() != null) System.out.println("Prev Nl Ch =" +
		 * animal.getPrevNlChId()); if (animal.getNextNlChId() != null)
		 * System.out.println("Next Nl Ch =" + animal.getNextNlChId());
		 */
		animalNode.appendChild(createTextNode("prevengch", animal
				.getPrevEngChId()));
		animalNode.appendChild(createTextNode("nextengch", animal
				.getNextEngChId()));
		animalNode.appendChild(createTextNode("prevnlch", animal
				.getPrevNlChId()));
		animalNode.appendChild(createTextNode("nextnlch", animal
				.getNextNlChId()));
		Node chYearNode = destDoc.createElement("chyear");
		chYearNode.appendChild(destDoc.createTextNode(animal.getChyear()));
		animalNode.appendChild(chYearNode);
		rootNode.appendChild(animalNode);
	}
	private void createChampNode(Document destDoc, Node rootNode, Animal animal) {
		if (animal.isChampion()) {
			//System.out.println("champion is" + animal);
			Node chsNode = destDoc.createElement("chs");
			Node chNode = null;
			for (Iterator iter = maps.getAllAnimals().iterator(); iter
					.hasNext();) {
				Animal chAnimal = (Animal) iter.next();
				String ch = chAnimal.getId();
				chNode = destDoc.createElement("ch");
				Node bornNode = destDoc.createElement("chtitle");
				bornNode.appendChild(destDoc.createTextNode(ch));
				chNode.appendChild(bornNode);
				Node colorNode = destDoc.createElement("chyear");
				colorNode.appendChild(destDoc
						.createTextNode(animal.getChyear()));
				chNode.appendChild(colorNode);
				chNode.appendChild(destDoc.createTextNode(ch));
				chsNode.appendChild(chNode);
			}
			rootNode.appendChild(chsNode);
		}
	}
	
	private void generatePedigree(Node pedigreeNode, String animalId,
			int level, int idx, String[] namesArr, String[] idArr) {
		//Node animalNode = null;
		if (srcDoc != null &&	animalId != null && level < MAXLEVEL) {
			Animal animal = maps.findAnimalById(animalId);
			if (animal == null) {
				throw new NullPointerException("animal = null:  " + animalId);
			}
			if (animal.getTotalName() == null) {
				throw new NullPointerException("TotalName = null" + animalId);
			}
			namesArr[idx] = animal.getTotalName();
			idArr[idx] = animalId;
			generatePedigree(pedigreeNode, animal.getFatherId(), level + 1,
					2 * idx, namesArr, idArr);
			generatePedigree(pedigreeNode, animal.getMotherId(), level + 1,
					2 * idx + 1, namesArr, idArr);
		}
	}
}
