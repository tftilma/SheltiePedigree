package nl.home.ttilma.ped;

import java.io.File;
import java.text.ParseException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import nl.home.ttilma.ped.dom.Pedigree;
import nl.home.ttilma.ped.dom.maps.PedigreeMaps;
import nl.home.ttilma.ped.service.GenAnimalsPerKennelService;
import nl.home.ttilma.ped.service.GenChampions;
import nl.home.ttilma.ped.service.GenKennelAphabetsService;
import nl.home.ttilma.ped.service.GenKennelGazettes;
import nl.home.ttilma.ped.service.GenPedigree;
import nl.home.ttilma.ped.service.GenRoms;
import nl.home.ttilma.ped.service.GenStatistics;
import nl.home.ttilma.ped.service.XmlWriterService;

public class Main {
    private static Log log = LogFactory.getLog("Main");
    private PedigreeMaps maps = new PedigreeMaps();
    
    public static void main(String[] args) throws Exception {

        Main main = new Main();
        main.generate();
        System.out.println("end");
    }
    
    
    private Pedigree readShelties() throws JAXBException {
            File file = new File("src/main/resources/xml/shelties.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Pedigree.class);
            
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Pedigree pedigree = (Pedigree) jaxbUnmarshaller.unmarshal(file);
            System.out.println("geladen");
            return pedigree;
    }
    
    public void generate() throws Exception {
        Pedigree pedigree = readShelties();
    
        generateMaps(pedigree);
        
        genKennelGazettes(pedigree);
        genKennelAlphabets(pedigree);
        genAnimalsPerKennel();
        genStatistics(pedigree);
        genChampions(pedigree);
        genRoms(pedigree);
        generatePedigrees(pedigree);
    }


    private void generateMaps(Pedigree pedigree) throws ParseException {
        // STEP 2
        log.info("STEP 2 creating maps");
        
        maps.setPedigree(pedigree);
        maps.addAll();
        
        log.info("STEP 3 Parse all");
        maps.parseAll();
        
        log.info("Found " + maps.getNAnimals() + " animals");
        log.info("Found " + maps.getNKennels() + " kennels");
        log.info("Found " + maps.getNPhotos() + " photos");
    }

    private void genKennelGazettes(Pedigree pedigree) throws Exception {
        log.info("STEP 5G: generating kennel gazette");
        XmlWriterService kgs = new GenKennelGazettes();
        kgs.setPedigreeMap(maps);
        kgs.generate();
    }

    private void genKennelAlphabets(Pedigree pedigree) throws Exception {
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

    private void genStatistics(Pedigree pedigree) throws Exception {
        log.info("STEP 5D: generating statistics");
        XmlWriterService genStatistics = new GenStatistics();
        genStatistics.setPedigreeMap(maps);
        genStatistics.generate();
    }

    private void genChampions(Pedigree pedigree) throws Exception {
        log.info("STEP 5E: generating champions");
        XmlWriterService genPedigrees = new GenChampions();
        genPedigrees.setPedigreeMap(maps);
        genPedigrees.generate();
    }

    private void genRoms(Pedigree pedigree) throws Exception  {
        log.info("STEP 5F: generating rom, romc, roma");
        XmlWriterService roms = new GenRoms();
        roms.setPedigreeMap(maps);
        roms.generate();
    }

    private void generatePedigrees(Pedigree pedigree) throws Exception {
        log.info("STEP 5A: generating DOM and HTML pedigrees for each animal");
        XmlWriterService genPedigrees = new GenPedigree();
        genPedigrees.setPedigreeMap(maps);
        genPedigrees.generate();
    }
}