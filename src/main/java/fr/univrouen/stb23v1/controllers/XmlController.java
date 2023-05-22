package fr.univrouen.stb23v1.controllers;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import fr.univrouen.stb23v1.model.DetailSTBModel;
import fr.univrouen.stb23v1.model.STBListModel;
import fr.univrouen.stb23v1.model.STBModel;
import fr.univrouen.stb23v1.model.XMLResponseModel;
import fr.univrouen.stb23v1.services.STBService;
import fr.univrouen.stb23v1.util.Status;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class XmlController {

    private final STBService stbService;

    @GetMapping("/stb23/resume/xml")
    public ResponseEntity<STBListModel> resume() {
        var stbs = stbService.findAll();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_XML).body(stbs);
    }

    @GetMapping("/stb23/xml/{id}")
    public ResponseEntity<?> findById(@PathVariable(required = true) Long id) {
        var stb = stbService.findById(id);
        if (stb == null) {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_XML).body(
                    new XMLResponseModel(id, Status.ERROR, null)
            );
        }
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_XML).body(stb);
    }

}
