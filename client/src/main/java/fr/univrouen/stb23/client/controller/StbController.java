package fr.univrouen.stb23.client.controller;

import fr.univrouen.stb23.client.model.CreateSTBModel;
import fr.univrouen.stb23.client.model.DetailSTBModel;
import fr.univrouen.stb23.client.model.STBListModel;
import fr.univrouen.stb23.client.model.XMLResponseModel;
import fr.univrouen.stb23.client.util.AppConstant;
import fr.univrouen.stb23.client.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

@Controller
public class StbController {

    private RestTemplate restTemplate;

    @Autowired
    public StbController(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @GetMapping("/stb23")
    public String GetAll(Model model) {
        var url = AppConstant.API_URL + "/stb23/resume/xml";
        ResponseEntity<STBListModel> response = restTemplate.getForEntity(url, STBListModel.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            var m = response.getBody();
            model.addAttribute("list", m);
            return "STB/list.html";
        } else {
            return "404.html";
        }
    }

    @GetMapping("/stb23/{id}")
    public String GetById(Model model, @PathVariable Long id) {
        var url = AppConstant.API_URL + "/stb23/xml/" + id;
        ResponseEntity<DetailSTBModel> response = restTemplate.getForEntity(url, DetailSTBModel.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            var m = response.getBody();
            model.addAttribute("stb", m);
            return "STB/detail.html";
        } else {
            return "404.html";
        }
    }

    @GetMapping("/stb23/create")
    public String create(Model model) {
        model.addAttribute("model", new CreateSTBModel());
        return "STB/create.html";
    }

    @PostMapping("/stb23/save")
    public String Save(@ModelAttribute CreateSTBModel m, Model model) {
        var url = AppConstant.API_URL + "/stb23/insert";
        var entity = new HttpEntity<String>(m.getStb());
        ResponseEntity<XMLResponseModel> response = restTemplate.postForEntity(url, entity, XMLResponseModel.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            var body = response.getBody();
            if (body.status == Status.INSERTED) {
                return "redirect:/stb23";
            } else if (body.status == Status.INVALID) {
                return "redirect:/stb23/create?message=" + response.getBody().message;
            } else {
                return "redirect:/stb23/create?message=" + response.getBody().message;
            }
        } else {
            return "404.html";
        }
    }

    @PostMapping("/stb23/{id}")
    public String Delete(@PathVariable Long id, Model model) {
        var url = AppConstant.API_URL + "/stb23/delete/" + id;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<XMLResponseModel> response = restTemplate.exchange(url, HttpMethod.DELETE, entity, XMLResponseModel.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            model.addAttribute("response", response.getBody());
        }
        return "STB/delete.html";
    }


}
