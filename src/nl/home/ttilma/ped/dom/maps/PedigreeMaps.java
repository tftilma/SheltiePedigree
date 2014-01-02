/*
 * Created on Aug 1, 2006
 *
*/
package nl.home.ttilma.ped.dom.maps;

import java.text.ParseException;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import nl.home.ttilma.ped.dom.Animal;
import nl.home.ttilma.ped.dom.Kennel;
import nl.home.ttilma.ped.dom.Pedigree;
import nl.home.ttilma.ped.dom.Reg;
import nl.home.ttilma.ped.dom.comparators.*;
import nl.home.ttilma.ped.dom.helpers.TotalNameCreater;
import nl.home.ttilma.ped.dom.list.ChampionList;
import nl.home.ttilma.ped.dom.list.KennelsPerChar;
import nl.home.ttilma.ped.service.Files;
import nl.home.ttilma.utils.*;

/**
 * @author ttilma
 */
public class PedigreeMaps {
	private static Log log = LogFactory.getLog("PedigreeMaps");
	public static int FIRST_REG_YEAR = 1909;
	public static int LAST_REG_YEAR = 1980;

	private Dates dates = new Dates();
	
	private Files jpgs = new Files();
	
	private Pedigree ped;
	private KennelMap kennelMap =  new KennelMap();
	private KennelsPerChar kennelsPerChar = new KennelsPerChar();
	private AnimalMap animalMap = new AnimalMap();
	private Set animalNamesSet = new HashSet();
	
	private AnimalToKennelMap animalToKennelMap = new AnimalToKennelMap();
	private KennelToAnimalsMap kennelToAnimalsMap = new KennelToAnimalsMap();
	private ParentToChildrenMap parentToChildrenMap = new ParentToChildrenMap();
	private RegMap regMap = new RegMap(FIRST_REG_YEAR, LAST_REG_YEAR);
	private TotRegsMap totRegsMap = new TotRegsMap(FIRST_REG_YEAR, LAST_REG_YEAR);
	private TotalNameCreater totalNameCreater = new TotalNameCreater();
	
	private LinesFamMap colorMap = new LinesFamMap();
	private LinesFamMap linesMap = new LinesFamMap();
	private LinesFamMap famMap = new LinesFamMap();
	
	private Comparator kennelComparator = new KennelComparator();
	private Comparator animalChComparator = new AnimalChComparator();
	private Comparator animalChYearComparator = new AnimalChYearComparator();
	private Comparator animalBornComparator = new AnimalBornComparator();
	private Comparator animalChildComparator = new AnimalChildComparator(); 
	private Comparator animalNameComparator = new AnimalNameComparator();
	private Comparator animalRegComparator = new AnimalRegComparator(this);
	
	private ChampionList nlChList = new ChampionList(170);
	private ChampionList engChList = new ChampionList(800);
	private ChampionList ausChList = new ChampionList(500);
	private ChampionList nzChList = new ChampionList(100);
	private ChampionList romList = new ChampionList(800);
	private ChampionList romcList = new ChampionList(100);
	private ChampionList romaList = new ChampionList(150);
	private ChampionList romnzList = new ChampionList(20);
	
	private Date nulDate;
	
	
	public ChampionList getNlChList() {
		return nlChList;
	}
	
	public ChampionList getEngChList() {
		return engChList;
	}
	
	public ChampionList getAusChList() {
		return ausChList;
	}
	
	public ChampionList getNzChList() {
		return nzChList;
	}
	
	public List[] getKennelsPerChar() {
		return kennelsPerChar.getKennelsPerChar();
	}
	  
	
	//private Map childFatherMap = new HashMap();
	//private Map childMotherMap = new HashMap();
	//private Map parentChildrenMap = new HashMap();
	 
	public PedigreeMaps() {
	  log.debug("creating PedigreeMaps");
	  try {
	    nulDate = calcDate("01-01-1900");
	  } catch (Exception e) {
	    e.printStackTrace();
      }
	}
	
	public void setPedigree(Pedigree ped) {
		log.debug("setPedigree ped=" + ped);
		this.ped = ped;
	}
	
	public void addAll() throws ParseException {
		// STEP 5H
		
		jpgs.detectAllPhotos("./photo");
		//log.info("contains photo of Helensdale Ace? " + jpgs.hasJpg("heace"));
		
		
		addAllKennels(ped);
		addAllAnimals(ped);
	}
	
	public void addAllKennels(Pedigree ped) {
		for (Iterator iter=ped.getKennels().iterator(); iter.hasNext();) {
			Kennel kennel = (Kennel) iter.next();
			kennelMap.addKennel(kennel);
			//log.info("adding kennel" + kennel);
			
			kennelsPerChar.addKennel(kennel);
		}		
	}
	
	public void addAllAnimals(Pedigree ped) throws ParseException {
		for (Iterator iter=ped.getAnimals().iterator(); iter.hasNext();) {
			Animal animal = (Animal) iter.next();
			animalMap.addAnimal(animal);
			animal.setBornDate(dates.calcDateDdMmYyyy(animal.getBorn()));

			
			regMap.addAnimal(animal);
			animal.setHasPhoto(jpgs.hasJpg(animal.getId()));
			
			if (animal.isChampion()) {
				for (Iterator chIter = animal.getChampions().iterator(); chIter.hasNext();) {
					String country = (String) chIter.next();
					if ("NL".equals(country)) {
						nlChList.addCh(animal);
					} else if ("Eng".equals(country)) {
						engChList.addCh(animal);
					} else if ("Aus".equals(country)) {
						ausChList.addCh(animal);
					} else if ("NZ".equals(country)) {
						nzChList.addCh(animal);
					} else {
						// champion in some other country
					}
				}
			}
			//animal.setBornDate(dates.calcDateDdMmYyyy(animal.getBorn()));
			if (animal.isRom()) { 
				romList.addCh(animal);
			}
			if (animal.isRomc()) { 
				animal.setBornDate(dates.calcDateDdMmYyyy(animal.getBorn()));								
				romcList.addCh(animal);
			}
			if (animal.isRoma()) { 
				animal.setBornDate(dates.calcDateDdMmYyyy(animal.getBorn()));								
				romaList.addCh(animal);
			}
			if (animal.isRomnz()) { 
				animal.setBornDate(dates.calcDateDdMmYyyy(animal.getBorn()));								
				romnzList.addCh(animal);
			}
			//log.info("adding animal=" +animal.getId() + 
			//		", kennelid=" + animal.getKennelId() +
			//		", kennelid2=" + animal.getKennelId2());
		}	
		
		// the champions are now add UNORDERED, so we fix this now...
		fixNlChList();
		fixEngChList();
	}
	
	@SuppressWarnings("unchecked")
	private void fixNlChList() {
		Collections.sort(nlChList.getChList(), this.animalChYearComparator);
		//List<Animal> chList = nlChList.getChList();
		for (int chIdx=0; chIdx < nlChList.getNChs()-1; chIdx++) {
			Animal animalCh = nlChList.findByIdx(chIdx);
			if (chIdx > 0) {
				animalCh.setPrevNlChId(engChList.findByIdx(chIdx-1).getId());
			} else {
				animalCh.setPrevNlChId("");
			}
			if (chIdx < nlChList.getNChs()) {
				animalCh.setNextNlChId(nlChList.findByIdx(chIdx+1).getId());
			} else {
				animalCh.setNextNlChId("");
			}
		}		
	}
	
	@SuppressWarnings("unchecked")
	private void fixEngChList() {
		Collections.sort(engChList.getChList(), this.animalChYearComparator);
		//List<Animal> chList = engChList.getChList();
		for (int chIdx=0; chIdx < engChList.getNChs()-1; chIdx++) {
			Animal animalCh = engChList.findByIdx(chIdx);
			if (chIdx > 0) {
				animalCh.setPrevEngChId(engChList.findByIdx(chIdx-1).getId());
			} else {
				animalCh.setPrevEngChId("");
			}
			if (chIdx < engChList.getNChs()) {
				animalCh.setNextEngChId(engChList.findByIdx(chIdx+1).getId());
			} else {
				animalCh.setNextEngChId("");
			}
		}		
	}
	
	public void parseAll() {
		// set dependencies
		animalToKennelMap.setKennelMap(kennelMap);
		parentToChildrenMap.setAnimalMap(animalMap);
		parseTotRegs();
		
		for (Iterator iter=ped.getAnimals().iterator(); iter.hasNext();) {
			Animal animal = (Animal) iter.next();
			animal.setTotalName(totalNameCreater.calculateTotalName(
					findKennelById(animal.getKennelId()),
					findKennelById(animal.getKennelId2()),
					animal));
			String totalAnimalName = animal.getTotalName();
			if (animalNamesSet.contains(totalAnimalName)) {
				//throw new IllegalStateException("Refused adding " + animal.getId() + " for " + totalAnimalName + " was already found");
				log.warn("Warning: adding " + animal.getId() + " for " + totalAnimalName + " was already found");
			} else {
				animalNamesSet.add(totalAnimalName);
			}
			
			animalToKennelMap.addAnimal(animal);
			kennelToAnimalsMap.addAnimal(animal);
			
			parentToChildrenMap.addAnimal(animal);
			Animal father = findAnimalById(animal.getFatherId());
			if (father != null) {
				if (!father.getLine().equals(animal.getLine())) {
					log.error(animal.getId() + " animal.line >" + animal.getLine() +  
							"< != father.line >" + father.getLine() + "<");
				}
				if (father.getGender() != Animal.MALE) {
					log.error("the father of " + animal.getId() + " (" + 
				      father.getId() + ") is not MALE");					
				}
			}
			Animal mother = findAnimalById(animal.getMotherId());
			if (mother != null) {
				if (!mother.getFam().equals(animal.getFam())) {
					log.error(animal.getId() + " animal.fam >" + animal.getFam() +  
							"< != mother.fam >" + mother.getFam() + "<");
				}
				if (mother.getGender() != Animal.FEMALE) {
					log.error("the mother of " + animal.getId() + " (" + 
				      mother.getId() + ") is not FEMALE");					
				}
			}
			linesMap.addLine(animal.getLine());
			famMap.addLine(animal.getFam());
			colorMap.addLine(animal.getColor());
		}
		int nAnimals = ped.getAnimals().size();
		linesMap.setTotalShelties(nAnimals);
		famMap.setTotalShelties(nAnimals);
		colorMap.setTotalShelties(nAnimals);
		/*
		log.info("Number of BB="  + linesMap.getCount("BB")  + " - " + linesMap.getCount("BB")/ nAnimals);
		log.info("Number of CHE=" + linesMap.getCount("CHE") + " - " + linesMap.getCount("CHE")/ nAnimals);
		log.info("Number of OL="  + linesMap.getCount("OL")  + " - " + linesMap.getCount("OL")/ nAnimals);
		log.info("Number of LJA=" + linesMap.getCount("LJA") + " - " + linesMap.getCount("LJA") / nAnimals);
		log.info("Number of IH="  + linesMap.getCount("IH")  + " - " + linesMap.getCount("IH")/ nAnimals);
		log.info("Number of TPR=" + linesMap.getCount("TPR") + " - " + linesMap.getCount("TPR")/ nAnimals);
		log.info("Number of DL="  + linesMap.getCount("DL")  + " - " + linesMap.getCount("DL")/ nAnimals);
		log.info("Number of LWW=" + linesMap.getCount("LWW") + " - " + linesMap.getCount("LWW")/ nAnimals);		
		
		log.info("Number of F1="  + famMap.getCount("F1")  + " - " + famMap.getCount("F1")/ nAnimals);
		log.info("Number of F2="  + famMap.getCount("F2")  + " - " + famMap.getCount("F2") / nAnimals);
		log.info("Number of F3="  + famMap.getCount("F3")  + " - " + famMap.getCount("F3") / nAnimals);
		log.info("Number of F4="  + famMap.getCount("F4")  + " - " + famMap.getCount("F4") / nAnimals);
		log.info("Number of F5="  + famMap.getCount("F5")  + " - " + famMap.getCount("F5") / nAnimals);
		log.info("Number of F6="  + famMap.getCount("F6")  + " - " + famMap.getCount("F6") / nAnimals);
		log.info("Number of F7="  + famMap.getCount("F7")  + " - " + famMap.getCount("F7") / nAnimals);
		log.info("Number of F7A=" + famMap.getCount("F7A") + " - " + famMap.getCount("F7A") / nAnimals);
		log.info("Number of F8="  + famMap.getCount("F8")  + " - " + famMap.getCount("F8") / nAnimals);		
		log.info("Number of F9="  + famMap.getCount("F9")  + " - " + famMap.getCount("F9") / nAnimals);
		log.info("Number of F10=" + famMap.getCount("F10") + " - " + famMap.getCount("F10")  / nAnimals);		
		log.info("Number of F11=" + famMap.getCount("F11") + " - " + famMap.getCount("F11")  / nAnimals);
		log.info("Number of F12=" + famMap.getCount("F12") + " - " + famMap.getCount("F12")  / nAnimals);
		log.info("Number of F13=" + famMap.getCount("F13") + " - " + famMap.getCount("F13")  / nAnimals);
		log.info("Number of F14=" + famMap.getCount("F14") + " - " + famMap.getCount("F14")  / nAnimals);
		log.info("Number of F15=" + famMap.getCount("F15") + " - " + famMap.getCount("F15")  / nAnimals);
		log.info("Number of F16=" + famMap.getCount("F16") + " - " + famMap.getCount("F16")  / nAnimals);
		log.info("Number of F17=" + famMap.getCount("F17") + " - " + famMap.getCount("F17")  / nAnimals);
		log.info("Number of F18=" + famMap.getCount("F18") + " - " + famMap.getCount("F18")  / nAnimals);		
		log.info("Number of F19=" + famMap.getCount("F19") + " - " + famMap.getCount("F19")  / nAnimals);
		log.info("Number of F20=" + famMap.getCount("F20") + " - " + famMap.getCount("F20")  / nAnimals);		
		log.info("Number of F21=" + famMap.getCount("F21") + " - " + famMap.getCount("F21")  / nAnimals);
		log.info("Number of F22=" + famMap.getCount("F22") + " - " + famMap.getCount("F22")  / nAnimals);
		log.info("Number of F23=" + famMap.getCount("F23") + " - " + famMap.getCount("F23")  / nAnimals);
		log.info("Number of F24=" + famMap.getCount("F24") + " - " + famMap.getCount("F24")  / nAnimals);
		log.info("Number of F25=" + famMap.getCount("F25") + " - " + famMap.getCount("F25")  / nAnimals);
		log.info("Number of F26=" + famMap.getCount("F26") + " - " + famMap.getCount("F26")  / nAnimals);
		log.info("Number of OF="  + famMap.getCount("OF")  + " - " + famMap.getCount("OF") / nAnimals);
		
		log.info("Number of sw="  + colorMap.getCount("sw")  + " - " + colorMap.getCount("sw")/ nAnimals);
		log.info("Number of tri="  + colorMap.getCount("tri")  + " - " + colorMap.getCount("tri")/ nAnimals);
		log.info("Number of bm="  + colorMap.getCount("bm")  + " - " + colorMap.getCount("bm")/ nAnimals);
		log.info("Number of bw="  + colorMap.getCount("bw")  + " - " + colorMap.getCount("bw")/ nAnimals);
		log.info("Number of bb="  + colorMap.getCount("bb")  + " - " + colorMap.getCount("bb")/ nAnimals);
		log.info("Number of dm="  + colorMap.getCount("dm")  + " - " + colorMap.getCount("dm")/ nAnimals);
		log.info("Number of sm="  + colorMap.getCount("sm")  + " - " + colorMap.getCount("sm")/ nAnimals);
		log.info("Number of bt="  + colorMap.getCount("bt")  + " - " + colorMap.getCount("bt")/ nAnimals);*/
	}
	
	private void parseTotRegs() {
		List regs = ped.getRegs();
		for (Iterator regsIter=regs.iterator(); regsIter.hasNext(); ) {
			Reg reg = (Reg) regsIter.next();
			int year = Integer.parseInt(reg.getYear());
			int month = Integer.parseInt(reg.getMonth());
			int tot = Integer.parseInt(reg.getTot());
			//log.info("parseTotRegs " + year + " " + month + " -> " + total);
			totRegsMap.setTotal(year, month, tot);
		}
	}

	public Kennel findKennelById(String kennelId) {
		return kennelMap.findKennel(kennelId);
	}
	
	public Animal findAnimalById(String animalId) {
		return animalMap.findAnimal(animalId);
	}
	
	public Kennel findKennelByAnimalId1(String animalId) {
		return animalToKennelMap.findKennelByAnimalId1(animalId);
	}
	
	public Kennel findKennelByAnimalId2(String animalId) {
		return animalToKennelMap.findKennelByAnimalId2(animalId);
	}
	
	public List findAnimalsByKennelId(String kennelId) {
		return kennelToAnimalsMap.findAnimalsByKennelId(kennelId);
	}
	
	public List findChildrenByParent(String parentId) {
		return parentToChildrenMap.findChildrenByParentId(parentId);
	}
	
	public List findAnimalsPerRegPeriod(int year, int month) {
		return regMap.findAnimals(year, month);
	}
	
	public int getNAnimals() {
		return animalMap.getNAnimals();
	}
	
	public int getNKennels() {
		return kennelMap.getNKennels();
	}
	public int getNPhotos() {
		return jpgs.sizeJpgs();
	}
	public KennelMap getKennelMap() {
		return kennelMap;
	}
	public KennelToAnimalsMap getKennelToAnimalsMap() {
		return kennelToAnimalsMap;
	}

	public Collection getAllKennels() {
		return kennelMap.getAllKennels();
	}
	
	public Collection getAllAnimals() {
		return animalMap.getAllAnimals();
	}
	
	public Comparator getAnimalChComparator() {
		return animalChComparator;
	}
	public Comparator getAnimalChYearComparator() {
		return animalChYearComparator;
	}
	public ChampionList getRomaList() {
		return romaList;
	}
	public ChampionList getRomnzList() {
		return romnzList;
	}
	public ChampionList getRomcList() {
		return romcList;
	}
	public ChampionList getRomList() {
		return romList;
	}

	public Comparator getAnimalBornComparator() {
		return animalBornComparator;
	}
	
	public Comparator getAnimalChildComparator() {
		return animalChildComparator;
	}
	
	public Date calcDate(String dateStr) throws ParseException {
		return dates.calcDateDdMmYyyy(dateStr);
	}
	public Comparator getAnimalNameComparator() {
		return animalNameComparator;
	}
	public Comparator getKennelComparator() {
		return kennelComparator;
	}
	public Comparator getAnimalRegComparator() {
		return animalRegComparator;
	}
	public TotRegsMap getTotRegsMap() {
		return totRegsMap;
	}
	public Date getNulDate() {
		return nulDate;
	}
	public LinesFamMap getColorMap() {
		return colorMap;
	}
	public LinesFamMap getFamMap() {
		return famMap;
	}
	public LinesFamMap getLinesMap() {
		return linesMap;
	}
}
