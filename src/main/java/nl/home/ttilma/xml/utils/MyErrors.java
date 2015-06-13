package nl.home.ttilma.xml.utils;

import javax.xml.transform.ErrorListener;
import javax.xml.transform.TransformerException;

public class MyErrors implements ErrorListener {

    @Override
    public void warning(TransformerException exception)
            throws TransformerException {
        System.out.println("warning: " + exception);
        exception.printStackTrace();
    }

    @Override
    public void error(TransformerException exception)
            throws TransformerException {
        System.out.println("error: " + exception);
        exception.printStackTrace();

    }

    @Override
    public void fatalError(TransformerException exception)
            throws TransformerException {
        System.out.println("fatalerror: " + exception);
        exception.printStackTrace();

    }

}
