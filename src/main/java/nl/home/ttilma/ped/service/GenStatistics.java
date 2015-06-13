/*
 * Created on Aug 3, 2006
 *
*/
package nl.home.ttilma.ped.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import nl.home.ttilma.ped.dom.Animal;
import nl.home.ttilma.ped.dom.Kennel;
import nl.home.ttilma.ped.dom.comparators.AnimalsWithMostOffspringComparator;
import nl.home.ttilma.ped.dom.comparators.KennelsWithMostAnimalsComparator;
import nl.home.ttilma.ped.dom.maps.LinesFamMap;
import nl.home.ttilma.xml.utils.DocumentUtils;
import nl.home.ttilma.xml.utils.Transform;

/**
 * @author ttilma
 */
public class GenStatistics extends XmlWriterService {
	private static Log log = LogFactory.getLog("GenStatistics");
	private final static String STATISTICS_FILE_NAME = "html//statistics.html";
	
	public void generate()
	throws Exception {
	    Document statisticsDoc = genStatisticsDoc();
	    Transform.transform(statisticsDoc, "src/main/resources/xml/statistics.xslt", STATISTICS_FILE_NAME);
	}

	private Document genStatisticsDoc()
	  throws TransformerException, ParserConfigurationException
	  {
	    destDoc = DocumentUtils.createDocument();
	    
	    Node rootNode = destDoc.createElement("root");	    
	    genTotals(rootNode);
	    genFams(rootNode, maps.getFamMap());
	    genColors(rootNode, maps.getColorMap());
	    genLines(rootNode, maps.getLinesMap());
	    
	    
	    
	    System.out.println("Calculating top40 of animals with most offspring");
	    //Set animals = animalMap.keySet();
	    Collection<Animal> animals = maps.getAllAnimals();
	    Animal[] animalsWithMostOffspring = calcTopMostOffspring(animals);
	    
	    //System.out.println("Adding top40 with most offspring");
	    Node mostOffspringsNode = destDoc.createElement("mostoffsprings");
	    for (int idx=0; idx < 50; idx++)
	    {
	    	//log.info("mostoffspring:" + animalsWithMostOffspring[idx]);
	      Node mostOffspringNode = destDoc.createElement("mostoffspring");
	      mostOffspringNode.appendChild(createTextNode("animalidx", Integer.toString(idx+1)));
	      mostOffspringNode.appendChild(createTextNode("topperid", animalsWithMostOffspring[idx].getAnimalid()));
	      mostOffspringNode.appendChild(createTextNode("toppername", animalsWithMostOffspring[idx].getTotalName()));
	      mostOffspringNode.appendChild(createTextNode("nchildren", Integer.toString(animalsWithMostOffspring[idx].getNChildren())));
	      mostOffspringNode.appendChild(createTextNode("born", animalsWithMostOffspring[idx].getBorn()));
	      mostOffspringsNode.appendChild(mostOffspringNode);
	    }
	    rootNode.appendChild(mostOffspringsNode);
	    

	    //System.out.println("Calculating top40 kennel with most animal");
	    //Set kennels = kennelMap.keySet();
	    Collection<Kennel> kennels = maps.getAllKennels();
	    Kennel[] largestKennels = calcTopLargestKennels(kennels);
	    
	    //System.out.println("Adding top40 kennels");
	    Node largestKennelsNode = destDoc.createElement("largestkennels");
	    for (int idx=0; idx < 50; idx++) {
	      Node largeKennelNode = destDoc.createElement("largekennel");
	      largeKennelNode.appendChild(createTextNode("kennelidx", Integer.toString(idx+1)));
	      
	      largeKennelNode.appendChild(createTextNode("kennelid", largestKennels[idx].getKennelid()));
	      largeKennelNode.appendChild(createTextNode("kennelname", largestKennels[idx].getName()));
	      largeKennelNode.appendChild(createTextNode("nanimals", Integer.toString(largestKennels[idx].getNAnimals())));
	      largestKennelsNode.appendChild(largeKennelNode);
	    }
	    rootNode.appendChild(largestKennelsNode);
	    
	    destDoc.appendChild(rootNode);
	    return destDoc;
    }
 
	private void genTotals(Node rootNode) {
		Node totalNode = destDoc.createElement("totals");
	    totalNode.appendChild(createTextNode("nanimals", Integer.toString(maps.getNAnimals())));
	    totalNode.appendChild(createTextNode("nkennels", Integer.toString(maps.getNKennels())));
	    totalNode.appendChild(createTextNode("nphotos", Integer.toString(maps.getNPhotos())));
	    rootNode.appendChild(totalNode);
	}

	private void genLines(Node rootNode, LinesFamMap percMaps) {
		Node linesNode = destDoc.createElement("percLines");
		genPerc(linesNode, percMaps, "BB", "percLine");
		genPerc(linesNode, percMaps, "CHE", "percLine");
		genPerc(linesNode, percMaps, "LJA", "percLine");
		genPerc(linesNode, percMaps, "IH", "percLine");
		genPerc(linesNode, percMaps, "TPR", "percLine");
		genPerc(linesNode, percMaps, "DL", "percLine");
		genPerc(linesNode, percMaps, "LWW", "percLine");
		genPerc(linesNode, percMaps, "OL", "percLine");
	   
	    rootNode.appendChild(linesNode);
	}
	
	private void genColors(Node rootNode, LinesFamMap percMaps) {
		Node linesNode = destDoc.createElement("percColors");
		genPerc(linesNode, percMaps, "sw",  "percColor");
		genPerc(linesNode, percMaps, "tri", "percColor");
		genPerc(linesNode, percMaps, "bm",  "percColor");
		genPerc(linesNode, percMaps, "bw",  "percColor");
		genPerc(linesNode, percMaps, "bb",  "percColor");
		genPerc(linesNode, percMaps, "sm",  "percColor");
		genPerc(linesNode, percMaps, "dm",  "percColor");
		genPerc(linesNode, percMaps, "chw", "percColor");
		genPerc(linesNode, percMaps, "bt",  "percColor");
	   
	    rootNode.appendChild(linesNode);
	}

	private void genFams(Node rootNode, LinesFamMap percMaps) {
		Node linesNode = destDoc.createElement("percFams");
		genPerc(linesNode, percMaps, "F1", "percFam");
		genPerc(linesNode, percMaps, "F2", "percFam");
		genPerc(linesNode, percMaps, "F3", "percFam");
		genPerc(linesNode, percMaps, "F4", "percFam");
		genPerc(linesNode, percMaps, "F5", "percFam");
		genPerc(linesNode, percMaps, "F6", "percFam");
		genPerc(linesNode, percMaps, "F7", "percFam");
		genPerc(linesNode, percMaps, "F7A", "percFam");
		genPerc(linesNode, percMaps, "F8", "percFam");
		genPerc(linesNode, percMaps, "F9", "percFam");
		genPerc(linesNode, percMaps, "F10", "percFam");
		genPerc(linesNode, percMaps, "F11", "percFam");
		genPerc(linesNode, percMaps, "F12", "percFam");
		genPerc(linesNode, percMaps, "F13", "percFam");
		genPerc(linesNode, percMaps, "F14", "percFam");
		genPerc(linesNode, percMaps, "F15", "percFam");
		genPerc(linesNode, percMaps, "F16", "percFam");
		genPerc(linesNode, percMaps, "F17", "percFam");
		genPerc(linesNode, percMaps, "F18", "percFam");
		genPerc(linesNode, percMaps, "F19", "percFam");
		genPerc(linesNode, percMaps, "F20", "percFam");
		genPerc(linesNode, percMaps, "F21", "percFam");
		genPerc(linesNode, percMaps, "F22", "percFam");
		genPerc(linesNode, percMaps, "F23", "percFam");
		genPerc(linesNode, percMaps, "F24", "percFam");
		genPerc(linesNode, percMaps, "F25", "percFam");
		genPerc(linesNode, percMaps, "F26", "percFam");
		genPerc(linesNode, percMaps, "OF", "percFam");
	   
	    rootNode.appendChild(linesNode);
	}
	
	private void genPerc(Node rootNode, LinesFamMap percMaps, String lineName, String elName) {
		Node lineNode = destDoc.createElement(elName);
		
		String negPercLine = Double.toString(percMaps.getNegPerc(lineName));
		String negPercLineChopped = negPercLine;
		if (negPercLine.length() >= 6) {
			negPercLineChopped = negPercLine.substring(0, 6); 
		}
		lineNode.appendChild(createTextNode("negPerc", negPercLineChopped));
		lineNode.appendChild(createTextNode("key", lineName));
		lineNode.appendChild(createTextNode("count", Integer.toString(percMaps.getCount(lineName))));
		String percLine = Double.toString(percMaps.getPerc(lineName));
		String percLineChopped = percLine;
		if (percLine.length() >= 5) {
			percLineChopped = percLine.substring(0, 5); 
		}
		lineNode.appendChild(createTextNode("perc", percLineChopped));
	    rootNode.appendChild(lineNode);
	    //log.info("lineName=" + lineName + 
	    //		  " count=" + Integer.toString(percMaps.getCount(lineName)) +
	    //		  " perc=" + Double.toString(percMaps.getPerc(lineName)) +
	    // 		  " negPerc=" + Double.toString(percMaps.getNegPerc(lineName))
	    //		  );
	}
	
	

	private Animal []calcTopMostOffspring(Collection<Animal> animals)
	  {
	    Animal []animalsWithMostOffspring = new Animal[100];
	    // Filling of empty animals
	    for (int i=0; i<animalsWithMostOffspring.length; i++)
	    {
	      animalsWithMostOffspring[i] = new Animal();
	    }
	    
	    int i=0;
	    for (Iterator<Animal> animalIter = animals.iterator();animalIter.hasNext();)
	    {
	      //String animalId = (String) animalIter.next();
	      Animal animal = animalIter.next();
	      	//maps.findAnimalById(animalId); 
	      	//(Animal) animalMap.get(animalId);
	      if (animal.getNChildren() >= 20)
	      {
	        animalsWithMostOffspring[i] = animal;
	        i++;
	      }
	    }
	    
	    Comparator<Animal> comp = new AnimalsWithMostOffspringComparator();
	    Arrays.sort(animalsWithMostOffspring, comp);
	    return animalsWithMostOffspring;
	  }

	  // TODO
	  private Kennel []calcTopLargestKennels(Collection<Kennel> kennels)
	  {
	    Kennel []largestKennels = new Kennel[100];
	    // Filling of empty animals
	    for (int i=0; i<largestKennels.length; i++)
	    {
	      largestKennels[i] = new Kennel();
	    }
	    
	    int i=0;
	    for (Iterator kennelIter = kennels.iterator(); kennelIter.hasNext();)
	    {
	      //String kennelId = (String) kennelIter.next();
	      Kennel kennel = (Kennel) kennelIter.next();
	      	//maps.findKennelById(kennelId); 
	      	//(Kennel) kennelMap.get(kennelId);
	      if (!"empty".equals(kennel.getKennelid()) && kennel.getNAnimals() >= 30)
	      {
	      	//log.info("Found large kennel:" + kennel.getKennelId() + ":" + kennel.getNAnimals());
	        largestKennels[i] = kennel;
	        i++;
	      }
	    }
	    
	    Comparator<Kennel> comp = new KennelsWithMostAnimalsComparator();
	    Arrays.sort(largestKennels, comp);
	    return largestKennels;
	  }
}
