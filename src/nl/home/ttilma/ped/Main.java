/*
 * Created on Jul 9, 2006
 *
*/
package nl.home.ttilma.ped;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import nl.home.ttilma.ped.dom.Pedigree;
import nl.home.ttilma.ped.dom.maps.PedigreeMaps;
import nl.home.ttilma.ped.service.*;
import nl.home.ttilma.ped.service.GenAnimalsPerKennelService;
import nl.home.ttilma.ped.service.GenChampions;
import nl.home.ttilma.ped.service.GenKennelAphabetsService;
import nl.home.ttilma.ped.service.GenPedigree;
import nl.home.ttilma.ped.service.GenStatistics;
import nl.home.ttilma.ped.service.SheltieXmlToBeans;
import nl.home.ttilma.ped.service.XmlWriterService;


/**
 * @author ttilma
 */
public class Main {
	private static Log log = LogFactory.getLog("Main");
	private SheltieXmlToBeans sxtb = new SheltieXmlToBeans();
	private PedigreeMaps maps = new PedigreeMaps();
	private Pedigree ped;
	//private int position=1;	
	
	private void generate() throws Exception {		
		// STEP 1
		log.info("STEP 1 reading...");
		ped = sxtb.readSheltieXml("xml//shelties.xml");
		log.debug("ped="  + ped);
		
        // STEP 2
		log.info("STEP 2 creating maps");
		
		maps.setPedigree(ped);
		maps.addAll();
		
		log.info("STEP 3 Parse all");
		maps.parseAll();
		
		log.info("Found " + maps.getNAnimals() + " animals");
		log.info("Found " + maps.getNKennels() + " kennels");
		log.info("Found " + maps.getNPhotos() + " photos");
		
		
	    // STEP 5G
	    genKennelGazettes();
	    
		// STEP 5B
		genKennelAlphabets();
		 
	    // STEP 5C
		genAnimalsPerKennel();	
	    
        // STEP 5D
	    // Generate statistics
	    genStatistics();
	    
	    // STEP 5E
	    // Generate Championlists
	    genChampions();
	    
	    // STEP 5F
	    genRoms();
	      
	    // STEP 5A
	    generatePedigrees();
	    
	}
	
	private void genStatistics() throws Exception {
	    log.info("STEP 5D: generating statistics");
		XmlWriterService genStatistics = new GenStatistics();
		genStatistics.setPedigreeMap(maps);
		genStatistics.generate();
	}

	private void generatePedigrees() throws Exception {
		log.info("STEP 5A: generating DOM and HTML pedigrees for each animal");
		XmlWriterService genPedigrees = new GenPedigree();
		genPedigrees.setPedigreeMap(maps);
		genPedigrees.generate();
	}

	private void genKennelAlphabets() throws Exception {
		log.info("STEP 5B: generating DOM and HTML kennel alphabet-listings for each char");
		XmlWriterService genKennelAphabets = new GenKennelAphabetsService();
		genKennelAphabets.setPedigreeMap(maps);
		genKennelAphabets.generate();
	}
	
	private void genAnimalsPerKennel() throws Exception {
		log.info("STEP 5C: generating DOM and HTML animals per kennel");
	    XmlWriterService genAnimalsPerKennel = new GenAnimalsPerKennelService();
		genAnimalsPerKennel.setPedigreeMap(maps);
		genAnimalsPerKennel.generate();
	}
	
	private void genChampions() throws Exception {
		log.info("STEP 5E: generating champions");
	    XmlWriterService genPedigrees = new GenChampions();
		genPedigrees.setPedigreeMap(maps);
		genPedigrees.generate();
	}

	private void genRoms() throws Exception {
		log.info("STEP 5F: generating rom, romc, roma");
	    XmlWriterService roms = new GenRoms();
		roms.setPedigreeMap(maps);
		roms.generate();
	}
	
	private void genKennelGazettes() throws Exception {
		log.info("STEP 5G: generating kennel gazette");
	    XmlWriterService kgs = new GenKennelGazettes();
		kgs.setPedigreeMap(maps);
		kgs.generate();
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println("Main");
		
		Main main = new Main();
		log.info("START");
		main.generate();
		log.info("EXITING");	 
	}
}
