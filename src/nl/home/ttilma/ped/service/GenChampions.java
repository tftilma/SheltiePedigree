/*
 * Created on Aug 5, 2006
 *
 */
package nl.home.ttilma.ped.service;
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


/**
 * @author ttilma
 */
public class GenChampions extends XmlWriterService {
	private static Log log = LogFactory.getLog("GenChampions");
	private int position = 1;
	
	public void generate() throws Exception {
		position = 1;
		String country = "NL";
		List sortedAnimals = maps.getNlChList().getChList();
		genChampionLists(country, sortedAnimals, 1900, 1990);
		genChampionLists(country, sortedAnimals, 1991, 2010);
		country = "Eng";
		sortedAnimals = maps.getEngChList().getChList();
		position = 1;
		genChampionLists(country, sortedAnimals, 1900, 1950);
		genChampionLists(country, sortedAnimals, 1951, 1960);
		genChampionLists(country, sortedAnimals, 1961, 1970);
		genChampionLists(country, sortedAnimals, 1971, 1980);
		genChampionLists(country, sortedAnimals, 1981, 1990);
		genChampionLists(country, sortedAnimals, 1991, 2000);
		genChampionLists(country, sortedAnimals, 2001, 2010);
		country = "Aus";
		sortedAnimals = maps.getAusChList().getChList();
		position = 1;
		genChampionLists(country, sortedAnimals, 1900, 2006);
	}
	
	private void genChampionLists(String country, List sortedAnimals,
			int startyear, int endyear) 
	throws TransformerException, ParserConfigurationException {
		String championsFileName = "html//" + country
				+ Integer.toString(startyear) + "ch.html";
		Document statisticsDoc = genChampionListDoc(country, sortedAnimals,
				startyear, endyear);
		Transform.transform(statisticsDoc, "xml//champions.xslt",
				championsFileName);
	}
	
	private Document genChampionListDoc(String country, List sortedAnimals,
			int startyear, int endyear) 
	throws TransformerException, ParserConfigurationException {
		destDoc = DocumentUtils.createDocument();
		Node rootNode = destDoc.createElement("animals");
		rootNode.appendChild(createTextNode("country", country));
		Animal prevAnimal = null;
		Animal animal = null;
		for (Iterator iter = sortedAnimals.iterator(); iter.hasNext();) {
			prevAnimal = animal;
			animal = (Animal) iter.next();
			if (animal == null) {
				System.err.println("ERR: id is null");
			}
			if (prevAnimal != null) {
				if ("Eng".equals(country)) {
					prevAnimal.setNextEngChId(animal.getId());
					animal.setPrevEngChId(prevAnimal.getId());
				} else if ("NL".equals(country)) {
					prevAnimal.setNextNlChId(animal.getId());
					animal.setPrevNlChId(prevAnimal.getId());
				}
			}
			if (animal.getChyear() != null) {
				int chyear = Integer.parseInt(animal.getChyear());
				if (startyear == 0 || startyear <= chyear && chyear <= endyear) {
					addCh(rootNode, animal);
				}
			} else if (startyear == 0) {
				addCh(rootNode, animal);
			}
		}
		destDoc.appendChild(rootNode);
		return destDoc;
	}
	
	private void addCh(Node rootNode, Animal animal) {
		Node animalNode = destDoc.createElement("animal");
		animalNode.appendChild(createTextNode("position", Integer
				.toString(position)));
		animalNode.appendChild(createTextNode("animalid", animal.getId()));
		animalNode.appendChild(createTextNode("animaltotalname", animal.getTotalName()));
		//animalNode.appendChild(createTextNode("nickname", animal.getNickName()));
		animalNode.appendChild(createTextNode("containsphoto", ""+animal.containsPhoto()));
		animalNode.appendChild(createTextNode("born", animal.getBorn()));
		animalNode.appendChild(createTextNode("kennelid", animal.getKennelId()));
		animalNode.appendChild(createTextNode("animalid", animal.getId()));
		animalNode.appendChild(createTextNode("totalname", animal.getTotalName()));
		animalNode.appendChild(createTextNode("chyear", animal.getChyear()));
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
		animalNode.appendChild(createTextNode("line", animal.getLine()));
		animalNode.appendChild(createTextNode("fam", animal.getFam()));
		animalNode.appendChild(createTextNode("color", animal.getColor()));
		rootNode.appendChild(animalNode);
		position++;
	}
	
	/*
	 * private List sortCh(String country) { List sortedAnimals = new
	 * LinkedList(); for (Iterator iter = maps.getAllAnimals().iterator();
	 * iter.hasNext();) { Animal animal = (Animal) iter.next(); //Set animalIds =
	 * animalMap.keySet(); //List sortedAnimals = new LinkedList(); //for
	 * (Iterator iter = animalIds.iterator(); // iter.hasNext();) //{ //String
	 * id = (String) iter.next(); //if (id == null) //{ //
	 * System.err.println("ERR: id is null"); //} //Animal animal = (Animal)
	 * animalMap.get(id); if (animal.isChampion(country)) {
	 * sortedAnimals.add(animal); if
	 * ("svsmalldarknhandsome".equals(animal.getId())) { if
	 * ("Eng".equals(country)) { animal.setChyear("1974"); } else {
	 * animal.setChyear("1975"); } } } }
	 * Collections.sort(sortedAnimals,maps.getAnimalChYearComparator()); return
	 * sortedAnimals;
	 */
}
