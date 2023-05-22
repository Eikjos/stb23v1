package fr.univrouen.stb23v1.util;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;

public class Transformer {

    public static String XMLtoHTML(String xml, String xlstPath) throws TransformerException {
        Source xmlSource = new StreamSource(new StringReader(xml));
        Source xsltSource = new StreamSource(new File(xlstPath));

        // création d'un transformateur
        TransformerFactory factory = TransformerFactory.newInstance();
        javax.xml.transform.Transformer transformer = factory.newTransformer(xsltSource);

        // transformation de la chaîne XML en HTML
        StringWriter writer = new StringWriter();
        transformer.transform(xmlSource, new StreamResult(writer));
        return writer.toString();
    }
}
