/*
 * Created on Aug 4, 2006
 *
*/
package nl.home.ttilma.ped.service;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import nl.home.ttilma.ped.dom.Animal;
import nl.home.ttilma.ped.dom.Kennel;
import nl.home.ttilma.utils.Strings;
import nl.home.ttilma.xml.utils.DocumentUtils;
import nl.home.ttilma.xml.utils.Transform;


/**
 * @author ttilma
 */
public class GenPedigree extends XmlWriterService {
	private static Log log = LogFactory.getLog("GenPedigree");
	private static final int MAXLEVEL = 7;
	private static final boolean WITHCHILDREN = true;
	
	private int nAnimalInPedigree;
	private String[] namesArr;
	private String[] idArr;
	private String firstWithPhoto=null;
	private String firstWithoutPhoto=null;
	
	private Date date1920;
	//private Date date1930;
	
	private void init() {
		nAnimalInPedigree = (int) Math.pow(2.0, MAXLEVEL);
		namesArr = new String[nAnimalInPedigree];
		idArr = new String[nAnimalInPedigree];
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		try {
			date1920 = df.parse("01-01-1920");
			//date1930 = df.parse("01-01-1930");
			//log.info("date1920=" + date1920);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	private void clear() {
		for (int idx=0; idx < nAnimalInPedigree; idx++) {
			namesArr[idx] = null;
			idArr[idx] = null;
		}
	}
	
	public void generate() throws Exception {
		init();
		int i = 0;
		for (Iterator iter = maps.getAllAnimals().iterator(); iter.hasNext();) {
			clear();
			i++;
			Animal animal = (Animal) iter.next();
			String animalId = animal.getId();
			//log.info("generating pedigree " + i + " for " + animalId);
			
			destDoc = generatePedigreeDocument(animal);
			if (destDoc == null) {
				log.error("destDoc is null");
			}
			String pedFileName = "html/" + animalId + ".html";
			Transform.transform(destDoc, "xml/ped2html.xslt", pedFileName);
		}
	}
	
	private Document generatePedigreeDocument(Animal animal) 
	throws ParserConfigurationException {
		destDoc = DocumentUtils.createDocument();
		Node rootNode = generatePedigree(animal);
		destDoc.appendChild(rootNode);
		return destDoc;
	}

	private Node generatePedigree(Animal animal) {
		Node rootNode = destDoc.createElement("root");
		String animalId = animal.getId();
		//log.info("generatePedigree: for " + animalId);

		generatePedigree(animal, 1, 1, namesArr, idArr);
		
		//Kennel knl = maps.findKennelById(animal.getKennelId());
		
		Node childNode = destDoc.createElement("childNode");
		createChildNode(destDoc, childNode, animal, idArr[1], 1);
		rootNode.appendChild(childNode);
		
		//Add all names in the pedigree-array
		Node pedigreeNode = destDoc.createElement("pedigree");
	    for (int idx=1; idx <namesArr.length; idx++) {
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
		//log.info("generatePedigree: rootNode=" + rootNode);
		
		return rootNode;
	}
	
	private void generateDescendants(Node rootNode, String animalId) {
	    //if (log.isInfoEnabled()) log.info("generateDescendants for " + animalId);
	    Node descendantsNode = destDoc.createElement("descendants");
	    
	    List list = maps.findChildrenByParent(animalId); 
	    	//(List) parentChildrenMap.get(animalId);
	    if (list != null) {
	      //log.info("generateDescendants: " + list);
	      // Sort the list of animals alphabetic
	      Collections.sort(list, maps.getAnimalChildComparator());
	      int nr=0;
	      for (Iterator iter=list.iterator(); iter.hasNext();) {
	        nr++;
	        Animal descendant = (Animal) iter.next();
	        createDescendant(descendantsNode, descendant, nr);
	      }
	      //Node descNode = destDoc.createElement("desc");
	      //descNode.appendChild(createTextNode("nr1", Integer.toString(nr)));
	      //descendantsNode.appendChild(descNode);
	    }
	    rootNode.appendChild(descendantsNode);
	  }
	  
	  private void createDescendant(Node descendantsNode , Animal childAnimal, int nr)
	  {
	    Node descNode = destDoc.createElement("descender");
	    descNode.appendChild(createTextNode("nr", Integer.toString(nr)));
	    descNode.appendChild(createTextNode("descid", childAnimal.getId()));
	    descNode.appendChild(createTextNode("descname", childAnimal.getTotalName()));
	    //log.info("descendant " + childAnimal.getTotalName());
	    String gender = 
	        childAnimal.getGender() == Animal.MALE ? "dog" :
		          childAnimal.getGender() == Animal.FEMALE ? "bitch" :
		          "";
	    descNode.appendChild(createTextNode("gender", gender));	    
	    
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
	    descNode.appendChild(createTextNode("fatherid", fatherId));
	    descNode.appendChild(createTextNode("fathername", fatherStr));
	    descNode.appendChild(createTextNode("motherid", motherId));
	    descNode.appendChild(createTextNode("mothername", motherStr));
	    String nChildren = Integer.toString(childAnimal.getNChildren());
	    descNode.appendChild(createTextNode("nchildren", nChildren));
	    /*if (log.isInfoEnabled()) {
	    	log.info("fatherid=" + fatherId);
	    	log.info("fathername=" + fatherStr);
	    	log.info("motherid=" + motherId);
	    	log.info("fathername=" + fatherStr);
	    	log.info("mothername=" + motherStr);
	    	log.info("nchildren=" + nChildren);
	    }*/
	    descendantsNode.appendChild(descNode);
	  }

	
	private void createAnimalNode(Node pedigreeNode, String totalName, 
		      String idVal, int idx) {
	    Node animalNode = destDoc.createElement("animal");
		   
	    animalNode.appendChild(createTextNode("name", totalName));
	    animalNode.appendChild(createTextNode("id", idVal));
	    animalNode.appendChild(createTextNode("idx", Integer.toString(idx)));
		   
	    pedigreeNode.appendChild(animalNode);
	}

	
	private void createChildNode(Document destDoc, Node rootNode,
			Animal animal, String idVal, int idx) {
		Node childNode = destDoc.createElement("child");
		createChampNode(destDoc, childNode, animal);

		if (animal.containsPhoto() && firstWithPhoto == null) {
			firstWithPhoto = animal.getId();
			log.info("First with photo: " + animal.getId());
		} else if (!animal.containsPhoto() && firstWithoutPhoto == null) {
			firstWithoutPhoto = animal.getId();
			log.info("First without " + animal.getId());
		}
		
		if (animal.containsPhoto()) {
			if (animal.getBornDate() != null && animal.getBornDate().before(date1920)) {
				log.info("Old Sheltie: " + animal.getTotalName() + " " + animal.getId());
			}
		}
 
		childNode.appendChild(createTextNode("containsphoto", "" + animal.containsPhoto()));

		Kennel kennel1 = maps.findKennelById(animal.getKennelId());
		Kennel kennel2 = maps.findKennelById(animal.getKennelId2());
		String knlid2 = "";
		String knlname2 = "";
		if (kennel2 != null) {
			knlid2 = kennel2.getKennelId();
			knlname2 = kennel2.getName();
		}
		
		childNode.appendChild(createTextNode("kennelid", kennel1.getKennelId()));
		childNode.appendChild(createTextNode("kennelname", kennel1.getName()));
		
		childNode.appendChild(createTextNode("kennelid2", knlid2));
		childNode.appendChild(createTextNode("kennelname2", knlname2));
		childNode.appendChild(createTextNode("name", animal.getTotalName()));
		childNode.appendChild(createTextNode("gender", Integer.toString(animal.getGender())));
		if (animal.getReg() != null) {
			if (animal.getReg().getYear() != null) {
				if (animal.getReg().getMonth() != null) {
					childNode.appendChild(createTextNode("regyear", animal.getReg().getYear()));
					childNode.appendChild(createTextNode("regmonth", animal.getReg().getMonth()));
				} else {
					System.out.println("animal REGMONTH problem= " + animal.getId());
				}
			} else {
				//System.out.println("animal REGYEAR problem= " + animal.getId());
			}
		}
		childNode.appendChild(createTextNode("name", animal.getTotalName()));
		childNode.appendChild(createTextNode("childid", idVal));
		
		childNode.appendChild(createTextNode("idx", Integer.toString(idx)));
		childNode.appendChild(createTextNode("born", animal.getBorn()));
		childNode.appendChild(createTextNode("color", animal.getColor()));
		childNode.appendChild(createTextNode("line", animal.getLine()));
		childNode.appendChild(createTextNode("family", animal.getFam()));
		
		childNode.appendChild(createTextNode("prevengch", animal.getPrevEngChId()));
		childNode.appendChild(createTextNode("nextengch", animal.getNextEngChId()));
		childNode.appendChild(createTextNode("prevnlch", animal.getPrevNlChId()));
		childNode.appendChild(createTextNode("nextnlch", animal.getNextNlChId()));
		if (animal.getExportFrom() != null) {
			childNode.appendChild(createTextNode("exportfrom", animal.getExportFrom()));
		}
		if (animal.getImportTo() != null) {
			childNode.appendChild(createTextNode("importto", animal.getImportTo()));
		}
		if (animal.getChyear() != null) {
			childNode.appendChild(createTextNode("chyear", animal.getChyear()));
		}
	    
		rootNode.appendChild(childNode);
	}
	
	private void createChampNode(Document destDoc, Node rootNode, 
	        Animal animal)
	  {
	    if (animal.isChampion())
	    {
	      //System.out.println("champion is" + animal);
	      //log.info("createChampNode " + animal);
	      Node chsNode = destDoc.createElement("chs");
	      Node chNode = null;
		  for (Iterator iter = animal.getChampions().iterator(); iter.hasNext();)
		  {
	        String ch = (String) iter.next();
	        
		    chNode  = destDoc.createElement("ch");
		    //log.info("createChampNode ch=" + ch);
		    
		    if (!Strings.isTrimEmpty(ch)) {
			    Node chTitleNode = destDoc.createElement("chtitle");
			    chTitleNode.appendChild(destDoc.createTextNode(ch));
			    chNode.appendChild(chTitleNode);
		    }
		    
		    if (!Strings.isTrimEmpty(animal.getChyear())) {
			    Node chYearNode = destDoc.createElement("chyear");
			    chYearNode.appendChild(destDoc.createTextNode(animal.getChyear()));
			    chNode.appendChild(chYearNode);
		    }
		    
		    chsNode.appendChild(chNode);
		  }
		  rootNode.appendChild(chsNode);
	    }	    
	  }

	  
	  private void generatePedigree(Animal animal,
			int level, int idx, String[] namesArr, String[] idArr) {
		if (animal != null && level < MAXLEVEL) {
			String animalId = animal.getId();
			//log.info("generatePedigree " + animalId + " level=" + level + " idx=" +idx);
			if (animal.getTotalName() == null) {
				throw new NullPointerException("TotalName = null" + animalId);
			}
			namesArr[idx] = animal.getTotalName();
			idArr[idx] = animalId;
			generatePedigree(maps.findAnimalById(animal.getFatherId()), level + 1,
					2 * idx, namesArr, idArr);
			generatePedigree(maps.findAnimalById(animal.getMotherId()), level + 1,
					2 * idx + 1, namesArr, idArr);
		}
	}
}
