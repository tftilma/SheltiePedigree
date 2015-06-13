/*
 * Created on Dec 23, 2006
 *
*/
package nl.home.ttilma.ped.service;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ttilma
 */
public class Files {
	private Set<String> jpgs = new HashSet<String>();
	
	private void debug(String s) {
		System.out.println(s);
	}
	
	public void detectAllPhotos(String path) {
		File dir = new File(path);
		String[] fileNames = dir.list();
		for (int i=0; i<fileNames.length; i++) {
			if (fileNames[i].endsWith(".jpg")) {
				//debug("Adding fileName " + fileNames[i]);
				jpgs.add(fileNames[i]);
			}
		}
	}
	
	public boolean hasJpg(String fileName) {
		return jpgs.contains(fileName + ".jpg");
	}
	
	public int sizeJpgs() {
		return jpgs.size();
	}
}
