/*
 * Created on Aug 10, 2006
 *
*/
package nl.home.ttilma.ped.service;

import java.text.ParseException;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import nl.home.ttilma.ped.dom.Animal;
import nl.home.ttilma.ped.dom.list.ChampionList;
import nl.home.ttilma.xml.utils.DocumentUtils;
import nl.home.ttilma.xml.utils.Transform;


/**
 * @author ttilma
 */
public class GenRoms extends XmlWriterService {
	private static Log log = LogFactory.getLog(GenRoms.class.getName());
	private static String AMERICAN_ROM = "ROM";
	private static String CANADIAN_ROM = "ROMC";
	private static String AUSTRALIAN_ROM = "ROMA";
	private static String NEW_ZEALAND_ROM = "ROMNZ";
	
	private int position = 1;
	
	public void generate() throws Exception {
		//log.info("generate");
		addRoms();
		addRomcs();
		addRomas();
		addRomnzs();
	}

	/**
	 * @throws TransformerException
	 * @throws ParserConfigurationException
	 */
	private void addRomas() throws Exception {
		//log.info("addRomas 1900-2010");
		position = 1;
		List<Animal> sortedRomaAnimals = sortRom(maps.getRomaList());
		genChampionLists(AUSTRALIAN_ROM, sortedRomaAnimals, 1900, 2010);
	}
	
	/**
	 * @throws TransformerException
	 * @throws ParserConfigurationException
	 */
	private void addRomnzs() throws Exception {
		//log.info("addRomnzs 1900-2010");
		position = 1;
		List sortedRomnzAnimals = sortRom(maps.getRomnzList());
		genChampionLists(NEW_ZEALAND_ROM, sortedRomnzAnimals, 1900, 2010);
	}


	/**
	 * @throws TransformerException
	 * @throws ParserConfigurationException
	 */
	private void addRomcs() throws Exception {
		//log.info("addRomcs 1900-2010");
		position = 1;
		List sortedRomcAnimals = sortRom(maps.getRomcList());
		genChampionLists(CANADIAN_ROM, sortedRomcAnimals, 1900, 2010);
	}

	/**
	 * @throws TransformerException
	 * @throws ParserConfigurationException
	 */
	private void addRoms() throws Exception {
		//log.info("addRoms");
		position = 1;
		List sortedRomAnimals = sortRom(maps.getRomList());
		genChampionLists(AMERICAN_ROM, sortedRomAnimals, 1900, 1971);
		genChampionLists(AMERICAN_ROM, sortedRomAnimals, 1971, 1991);
		genChampionLists(AMERICAN_ROM, sortedRomAnimals, 1991, 2010);
	}

	private int genChampionLists(String romType, List sortedAnimals, int startyear, int endyear)
	  throws TransformerException, ParserConfigurationException, ParseException {
		//log.info("genChampionLists");
	    String championsFileName = "html//" + romType + Integer.toString(startyear)+ ".html";
	    Document statisticsDoc = genChampionListDoc(romType, sortedAnimals, startyear, endyear);
	    
	    Transform.transform(statisticsDoc, "src/main/resources/xml/roms.xslt", championsFileName);
	    return position;
	}
	

	private Document genChampionListDoc(String romType, List sortedAnimals, int startyear, int endyear)
	throws TransformerException, ParserConfigurationException, ParseException {
		//log.info("genChampionListDoc " + romType + " " + startyear + "-" + endyear);
		Date startDate = maps.calcDate("01-01-"+ startyear);
		Date endDate = maps.calcDate("01-01-"+ endyear);
		//log.info("gen list between " + startDate + " and " + endDate);
		
      destDoc = DocumentUtils.createDocument();
	    
	  Node rootNode = destDoc.createElement("animals");
	  rootNode.appendChild(createTextNode("romType", romType));
	    
	  Animal prevAnimal = null;
	  Animal animal = null;
	  for (Iterator iter = sortedAnimals.iterator(); iter.hasNext();) {
	  	
	    prevAnimal = animal;
	    animal = (Animal) iter.next();
	    if (animal == null) {
	      System.err.println("ERR: id is null");
	    }
	  	//log.info("Bekijken " + animal.getTotalName());
	  	
	    if (prevAnimal != null) {
	      /*if (AMERICAN_ROM.equals(romType)) { 
		  	//log.info("Toevoegen ROM " + animal.getTotalName());
	        prevAnimal.setNextRomId(animal.getId());
	        animal.setPrevRomId(prevAnimal.getId());
	      } else if (CANADIAN_ROM.equals(romType)) {
		  	//log.info("Toevoegen ROMC " + animal.getTotalName());
	        prevAnimal.setNextRomcId(animal.getId());
	        animal.setPrevRomcId(prevAnimal.getId());
	      } else if (AUSTRALIAN_ROM.equals(romType)) {
		  	//log.info("Toevoegen ROMA " + animal.getTotalName());
	        prevAnimal.setNextRomaId(animal.getId());
	        animal.setPrevRomaId(prevAnimal.getId());
	      } else if (NEW_ZEALAND_ROM.equals(romType)) {
		  	//log.info("Toevoegen ROMNZ " + animal.getTotalName());
	        prevAnimal.setNextRomnzId(animal.getId());
	        animal.setPrevRomnzId(prevAnimal.getId());
	      }
	      */
	    }
	    //addRom(rootNode, animal);
	    if (romType.equals(AMERICAN_ROM)) {
		    Date bornDate = animal.getBornDate();
		    if (bornDate == null) {
		    	//log.info("nulldate so 01-01-1900 for " + animal.getTotalName());
		    	bornDate = maps.getNulDate();
		    }
		    if ((startDate.compareTo(bornDate) <= 0) && 
		        (bornDate.compareTo(endDate) < 0)) {
		    	//log.info("found one for " + animal.getTotalName());
		    	addRom(rootNode, animal);
		    }
	    } else {
	    	addRom(rootNode, animal);
	    }
	  }
	    
	  destDoc.appendChild(rootNode);
	  return destDoc;
	}
	
	private void addRom(Node rootNode, Animal animal) {
      //log.info("addRom " + animal.getTotalName());

	  Node animalNode = destDoc.createElement("animal");
	  animalNode.appendChild(createTextNode("position", Integer.toString(position)));
	  animalNode.appendChild(createTextNode("animalid", animal.getAnimalid()));
	  animalNode.appendChild(createTextNode("animaltotalname", animal.getTotalName()));
	  //animalNode.appendChild(createTextNode("nickname", animal.getNickName()));
	  animalNode.appendChild(createTextNode("born", animal.getBorn()));
	  animalNode.appendChild(createTextNode("kennelid", animal.getKennelid()));
	  animalNode.appendChild(createTextNode("animalid", animal.getAnimalid()));
	  animalNode.appendChild(createTextNode("totalname", animal.getTotalName()));
	  //animalNode.appendChild(createTextNode("chyear", animal.getChyear()));
	    
	  String gender;
	  switch (animal.getGender()) {
        case Animal.MALE: 
	        gender = "dog"; 
	        break;
	    case Animal.FEMALE: 
	        gender = "bitch"; 
	        break;
	    default: 
	        gender = "unknown";
	  }
	  animalNode.appendChild(createTextNode("gender", gender));
	  animalNode.appendChild(createTextNode("line", animal.getLine()));
	  animalNode.appendChild(createTextNode("fam", animal.getFam()));
	  animalNode.appendChild(createTextNode("color", animal.getColor()));
	  rootNode.appendChild(animalNode);        
	  position++;
	}

	
	private List sortRom(ChampionList l) {
	  List roms = l.getChList();
      Collections.sort(roms, maps.getAnimalBornComparator());
      return roms;
	}	
}
