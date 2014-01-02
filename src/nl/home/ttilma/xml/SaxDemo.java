package nl.home.ttilma.xml;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class SaxDemo extends DefaultHandler {
	private int indentPos;
	
	SaxDemo() {
		// super();
	}

	public static void main(String[] args) throws Exception {
		String uri = SaxDemo.class.getResource("/shelties2.xml").toExternalForm();
		InputSource input = new InputSource(uri);
		XMLReader xr = XMLReaderFactory.createXMLReader();
		DefaultHandler handler = new SaxDemo();
		xr.setContentHandler(handler);
		xr.setErrorHandler(handler);
		xr.parse(input);
	}

	public void startElement(String uri, String localName, String qname,
			Attributes atts) {
		System.out.println();
		indent(indentPos);
		System.out.print("<" + qname);
		for (int i = 0, len = atts.getLength(); i < len; i++) {
			System.out.print(" " + atts.getQName(i) + "=\"" + atts.getValue(i)
					+ "\"");
		}
		System.out.print(">");
		indentPos++;
	}

	public void endElement(String uri, String localName, String qname) {
		indentPos--;
		
		//indent(indentPos);
		System.out.print("</" + qname + ">");
	}

	public void characters(char[] chs, int start, int length)
	throws SAXException {
		//indent(indentPos);
		String val = String.copyValueOf(chs, start, length).trim();
		System.out.print("[val=" + val+
				//"; chs=" + chs.toString() +
				//"; start=" + start +
				//"; lenth=" + length +
				"]");
	}
	
	private static void indent(int spaces) {
		for (int i = 0; i < spaces; i++) {
			System.out.print("  ");
		}
	}
}
