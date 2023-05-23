package fr.univrouen.stb23v1.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import fr.univrouen.stb23v1.entities.Client;
import fr.univrouen.stb23v1.entities.Feature;
import fr.univrouen.stb23v1.entities.Member;
import fr.univrouen.stb23v1.entities.STB;
import fr.univrouen.stb23v1.model.DetailSTBModel;
import fr.univrouen.stb23v1.model.XMLResponseModel;
import fr.univrouen.stb23v1.services.ClientService;
import fr.univrouen.stb23v1.services.FeatureService;
import fr.univrouen.stb23v1.services.MemberService;
import fr.univrouen.stb23v1.services.STBService;
import fr.univrouen.stb23v1.util.Status;
import fr.univrouen.stb23v1.util.XMLValidator;
import jakarta.xml.bind.JAXBException;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@AllArgsConstructor
@RestController()
public class CRUDController {

    private final STBService stbService;
    private final MemberService memberService;
    private final FeatureService featureService;
    private final ClientService clientService;

    @PostMapping("/stb23/insert")
    public ResponseEntity<XMLResponseModel> insert(@RequestBody String xml) throws JAXBException, ParseException, JsonProcessingException {
        // vérifier que le flux est correcte avec le fichier XSD
        xml = xml.replaceAll("[\n\r]", "" );
        boolean good = XMLValidator.validateXML(xml, "src/main/resources/static/xsd/stb23.xsd");
        if (good) {
            ObjectMapper mapper = new XmlMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            DetailSTBModel stb = mapper.readValue(xml, DetailSTBModel.class);
            // vérifier que le titre, la version  et la date ne correspond pas déjà à une STB
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = formatter.parse(stb.getDate());
            if (stbService.isPresent(stb.getTitle(), date, stb.getVersion())) {
                return ResponseEntity.ok().contentType(MediaType.APPLICATION_XML).body(
                        new XMLResponseModel(null, Status.DUPLICATED, "La spécification renseigné est déjà présent")
                );
            }
            var client = new Client(stb.getClient());
            client = clientService.save(client);
            var stbEntity = new STB(stb, client);
            stbEntity = stbService.save(stbEntity);
            var features = new ArrayList<Feature>();
            for (var feature : stb.getFeature()) {
                var f = new Feature(feature);
                f.setStb(stbEntity);
                f = featureService.save(f);
                features.add(f);
            }

            stbEntity.setFeatures(features);

            var team = new ArrayList<Member>();
            for (var member : stb.getMember()) {
                var m = new Member(member);
                m.setStb(stbEntity);
                m = memberService.save(m);
                team.add(m);
            }

            stbEntity.setTeam(team);

            stbService.save(stbEntity);

            return ResponseEntity.ok().contentType(MediaType.APPLICATION_XML).body(
                    new XMLResponseModel(stbEntity.getId(), Status.INSERTED, null)
            );
        } else {
            // Sinon renvoyer une erreur INVALIDE
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_XML).body(
                    new XMLResponseModel(null, Status.INVALID, "Le flux XML est invalide par rapport au schéma XSD")
            );
        }
    }

    @DeleteMapping("/stb23/delete/{id}")
    public ResponseEntity<XMLResponseModel> delete(@PathVariable Long id) {
        var stb = stbService.findByIdEntity(id);
        if (stb.isEmpty()) {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_XML).body(
                    new XMLResponseModel(null, Status.ERROR, "La spécification n'existe pas")
            );
        } else {
            stbService.delete(stb.get());
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_XML).body(
                    new XMLResponseModel(stb.get().getId(), Status.DELETED, "Le spécification a été supprimé avec succès")
            );
        }

    }
}
