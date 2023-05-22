package fr.univrouen.stb23v1.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import fr.univrouen.stb23v1.model.DetailSTBModel;
import fr.univrouen.stb23v1.model.STBListModel;
import fr.univrouen.stb23v1.services.STBService;
import fr.univrouen.stb23v1.util.Transformer;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

@AllArgsConstructor
@RestController
public class HtmlController {

    private final STBService stbService;

    @GetMapping("stb23/resume")
    public ResponseEntity<String> findAll() throws JsonProcessingException, TransformerException {
        STBListModel list = stbService.findAll();
        ObjectMapper xmlMapper = new XmlMapper();

        String xml = xmlMapper.writeValueAsString(list);

        var html = Transformer.XMLtoHTML(xml, "src/main/resources/static/xslt/stblist.xslt");

        return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(html);
    }

    @GetMapping("stb23/html/{id}")
    public ResponseEntity<String> findById(@PathVariable Long id) throws JsonProcessingException, TransformerException {

        DetailSTBModel stb = stbService.findById(id);
        ObjectMapper xmlMapper = new XmlMapper();

        String xml = xmlMapper.writeValueAsString(stb);

        var html = Transformer.XMLtoHTML(xml, "src/main/resources/static/xslt/stb23.xslt");

        return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(html);
    }

}
