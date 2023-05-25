package fr.univrouen.stb23v1.model;


import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.*;
import fr.univrouen.stb23v1.entities.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "client")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ClientModel {

    @JacksonXmlProperty(localName= "entity")
    public String entity;

    @JacksonXmlProperty(localName = "person")
    public PersonModel person;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "mail")
    public String[] mail;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "tel")
    public String[] tel;

    public ClientModel(Client client) {
        entity = client.getEntity();
        person = new PersonModel(client.getFirstname(), client.getGender() == null ? null : client.getGender().name(), client.getLastname());
        setTel(client.getPhones());
        setMail(client.getMails());
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.split(";");
    }

    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.split(";");
    }
}
