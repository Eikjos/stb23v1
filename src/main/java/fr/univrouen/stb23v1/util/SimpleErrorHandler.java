package fr.univrouen.stb23v1.util;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class SimpleErrorHandler implements ErrorHandler {

    private boolean errorOccured;

    public boolean hasError()  {
        return this.errorOccured;
    }

    public SimpleErrorHandler() {
        this.errorOccured = false;
    }

    @Override
    public void warning(SAXParseException exception) throws SAXException {
        exception.printStackTrace();
        this.errorOccured = true;
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        exception.printStackTrace();
        this.errorOccured = true;
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        exception.printStackTrace();
        this.errorOccured = true;
    }

}