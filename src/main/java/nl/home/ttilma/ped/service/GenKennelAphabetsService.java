/*
 * Created on Aug 2, 2006
 *
 */
package nl.home.ttilma.ped.service;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import nl.home.ttilma.ped.dom.Kennel;
import nl.home.ttilma.ped.dom.list.KennelsPerChar;
import nl.home.ttilma.xml.utils.DocumentUtils;
import nl.home.ttilma.xml.utils.Transform;
/**
 * @author ttilma
 */
public class GenKennelAphabetsService extends XmlWriterService {
	public void generate() 
	throws Exception {
		List<Kennel>[] charKennelList = maps.getKennelsPerChar();
		for (char kennelFirstChar = KennelsPerChar.START_CHAR; kennelFirstChar <= KennelsPerChar.END_CHAR; kennelFirstChar++) {
			String charKennelFileName = "html//" + kennelFirstChar + ".html";
			//System.out.println("STEP 5B [" + kennelFirstChar +
			//    ": length=" + charKennelList[kennelFirstChar -
			// KennelAlphabet.START_CHAR].size());
			Document kennelAlphabetsDoc = listToDoc(kennelFirstChar,
					charKennelList[kennelFirstChar - KennelsPerChar.START_CHAR]);
			Transform.transform(kennelAlphabetsDoc,
					"src/main/resources/xml/knlalphabet2html.xslt", charKennelFileName);
		}
	}
	
	private Document listToDoc(char startingCharOfKennel, List<Kennel> kennels)
			throws ParserConfigurationException {
		destDoc = DocumentUtils.createDocument();
		Collections.sort(kennels);
		Node rootNode = generateKennels(startingCharOfKennel, kennels);
		destDoc.appendChild(rootNode);
		return destDoc;
	}
	
	private Node generateKennels(char startingCharOfKennels, List<Kennel> kennels) {
		Node rootNode = destDoc.createElement("kennels");
		rootNode.appendChild(createTextNode("firstchar", ""
				+ startingCharOfKennels));
		for (Iterator<Kennel> iter = kennels.iterator(); iter.hasNext();) {
			Kennel knl =  iter.next();
			//System.out.println("Kennel id=" + knl.getId());
			//System.out.println("Kennel name=" + knl.getName());
			List animals = maps.findAnimalsByKennelId(knl.getKennelid());
			if (animals != null) {
				Node kennelNode = destDoc.createElement("kennel");
				kennelNode.appendChild(createTextNode("kennelid", knl
						.getKennelid()));
				int nAnimals = animals.size();
				if (nAnimals >= 10) {
					kennelNode
							.appendChild(createTextNode("kennelislarge", "1"));
				} else {
					kennelNode
							.appendChild(createTextNode("kennelislarge", "0"));
				}
				kennelNode.appendChild(createTextNode("kennelname", knl
						.getName()));
				kennelNode.appendChild(createTextNode("kenneltotal", Integer
						.toString(nAnimals)));
				rootNode.appendChild(kennelNode);
			}
		}
		return rootNode;
	}
	
	
}
