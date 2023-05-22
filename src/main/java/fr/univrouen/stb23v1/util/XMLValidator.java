package fr.univrouen.stb23v1.util;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.StringReader;

public class XMLValidator {

    public static boolean validateXML(String xmlString, String xsdPath) {
        var handler = new SimpleErrorHandler();
        try {
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            schemaFactory.setErrorHandler(handler);
            Schema schema = schemaFactory.newSchema(new File(xsdPath));

            Validator validator = schema.newValidator();

            var s = new StreamSource(new StringReader(xmlString));
            validator.validate(s);

        } catch (Exception e) {
        }
        return !handler.hasError();
    }
}
