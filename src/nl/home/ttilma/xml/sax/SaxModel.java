package nl.home.ttilma.xml.sax;

import java.util.HashMap;
import java.util.Map;

public abstract class SaxModel {
	private String classWithPrefix;
	private String classWithoutPrefix;
	private SaxModel parent;
	//private List<SaxModel> children = new ArrayList<SaxModel>();
	private Map<String, SaxModel> children = new HashMap<String, SaxModel>();
	
	public SaxModel() {
		this(null);
	}
	
	public SaxModel(SaxModel parent) {
		this.parent = parent;
	}
	
	public void addChild(String childName, SaxModel child) {
		System.out.println("addChild childName=" + childName + "; child=" + child);
		children.put(childName, child);
		if (child != null) {
			child.parent = this;
		}
		System.out.println("addChild children=" + children);
	}
	
	public Map<String, SaxModel> getChildren() {
		return children;
	}
	
	public SaxModel findChild(String className) {
		return children.get(className);
	}

	public SaxModel getParent() {
		return parent;
	}
	
	public abstract String getSaxName();

	public String getClassWithoutPrefix() {
		return classWithoutPrefix;
	}

	public void zetClassWithoutPrefix(String classWithoutPrefix) {
		this.classWithoutPrefix = classWithoutPrefix;
	}

	public String getClassWithPrefix() {
		return classWithPrefix;
	}

	public void zetClassWithPrefix(String classWithPrefix) {
		this.classWithPrefix = classWithPrefix;
	}
}
