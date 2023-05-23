package fr.univrouen.stb23.client.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
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

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.split(";");
    }

    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.split(";");
    }
}
