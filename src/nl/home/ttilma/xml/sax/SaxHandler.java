package nl.home.ttilma.xml.sax;


import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxHandler<Model extends SaxModel> extends DefaultHandler {
	private static Log log = LogFactory.getLog("SaxHandler");
	
	private Model model;
	private Model currEl;
	private Method[] methods;
	private Method currMethod;
	private Map<String, SaxModel> children = new HashMap<String, SaxModel>();
	
	public SaxHandler(Model model) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		log.debug("SaxHandler");
		this.model = model;
		currEl = this.model;
		analyse(model);
	}
	
	private void analyse(Model cEl) 
	throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		methods = cEl.getClass().getMethods();
		log.debug("analyse methods=" + methods);
		String className = cEl.getClass().getName(); 
		cEl.zetClassWithPrefix(className);
		int postLastDot = className.indexOf('.') + 1;
		cEl.zetClassWithoutPrefix(className.substring(postLastDot));
		
		for (int methodNr = 0; methodNr < methods.length; methodNr++) {
			Method method = methods[methodNr];
			String methodName = method.getName();
			if (methodName != null && methodName.length() > 3) {
				String methodClassName = methodName.substring(3);
				
				//System.out.println("methodClassName=" + methodClassName);
				
				if ("set".equals(methodName.subSequence(0, 3))) {
					
					Class[] parameterTypes = method.getParameterTypes();
					Class newClass = parameterTypes[0];
					System.out.println("methodName=" + methodName);
					System.out.println("newclass=" + newClass + ".");
					System.out.println("is1? " + SaxModel.class.isAssignableFrom(newClass));
					//System.out.println("is2?" + newClass.isAssignableFrom(SaxModel.class));
					if (SaxModel.class.isAssignableFrom(newClass)) {
						System.out.println("adding as SaxModel methodName=" + methodClassName);
						model.addChild(methodClassName, (SaxModel) newClass.newInstance());
						Model subEl = (Model) newClass.newInstance();
						analyse(subEl);
					} else if (String.class.isAssignableFrom(newClass)) {
						System.out.println("adding as String methodName=" + methodClassName);
						model.addChild(methodClassName, null);
					}
				}
				 
			}
		}
	}

	/**
	 * Start of the tag
	 * qname is the XML-tag
	 * atts is very important as it contains the attributes
	 */
	public void startElement(String uri, String localName, String qname,
			Attributes atts) {
		log.debug("startElement qname=" + qname);
		try {
			String currElClassName = currEl.getClass().getName();
			int posLastDot = currElClassName.lastIndexOf('.');
			String currSimpleClassName = currElClassName.substring(posLastDot + 1);
			log.debug("startElement trying... currEl=" + currSimpleClassName);
			Class clazz = null;
			if (currSimpleClassName.equals(qname)) {
				log.debug("startElement qname==currEl currEl=" + currEl);
				clazz = currEl.getClass();
			} else {
				log.debug("startElement qname!=currEl currEl=" + currEl);

				String setterMethodName = "set"+qname;
				System.out.println("startElement setterMethodName=" + setterMethodName);
				System.out.println("startElement qname=" + qname);
				System.out.println("startElement children=" + model.getChildren());
				SaxModel sm = model.findChild(qname);
				System.out.println("startElement sm=" + sm);
				
				if (sm != null) {
					//clazz = currEl.findChild(qname).getClass();
					// clazz = Class.forName(qname);
					clazz = sm.getClass();
					log.debug("startElement clazz=" + clazz);
				} else {
					// Assume sm refers actually to a String
					clazz = String.class;
				}
				//Method m = currEl.getClass().getMethod(setterMethodName, clazz);
				currMethod = currEl.getClass().getMethod(setterMethodName, clazz);
				System.out.println("startElement method=" + currMethod);
				//Class cm = m.getClass();
				System.out.println("startElement methods class=" + clazz);
				Object newObject = clazz.newInstance();
				currMethod.invoke(currEl, newObject);
				if (newObject instanceof SaxModel) {
					currEl = (Model)newObject;
				}
			}
			log.debug("startElement clazz=" + clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * end of the tag
	 */
	public void endElement(String uri, String localName, String qname) {
		currEl = (Model) currEl.getParent();
	}
	
	/**
	 * For contents of the tag
	 * String val = String.copyValueOf(chs, start, length).trim();
	 */
	public void characters(char[] chs, int start, int length)
	throws SAXException {
		String val = String.copyValueOf(chs, start, length).trim();
		System.out.println("chars=" + val + ".");
		if (currMethod != null) {
			Object[] args = new Object[1];
			args[0] = val;
			try {
				System.out.println("currEl=" + currEl+ ".");
				System.out.println("currEl.getParent()=" + currEl.getParent()+ ".");
				System.out.println("currMethod=" + currMethod+ ".");
				this.currMethod.invoke(currEl.getParent(), args);
			} catch (Exception e) {
				e.printStackTrace();
				throw new SAXException(e);
			}
		}
	}

	public Model getModel() {
		return model;
	}		
}
