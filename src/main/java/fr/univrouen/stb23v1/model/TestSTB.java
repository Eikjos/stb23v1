package fr.univrouen.stb23v1.model;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class TestSTB {

    Resource resource;
    public String loadFileXML() {
        resource = new DefaultResourceLoader().getResource("classpath:xml/test0.xml");
        try {
            return resource.getContentAsString(StandardCharsets.UTF_8);
        } catch(Exception e) {
            return "Une erreur est survenue";
        }
    }
}
