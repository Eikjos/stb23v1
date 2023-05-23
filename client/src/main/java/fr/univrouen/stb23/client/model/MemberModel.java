package fr.univrouen.stb23.client.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MemberModel {

    @JacksonXmlElementWrapper(localName = "person", useWrapping = false)
    private PersonModel person;

    @JacksonXmlProperty(localName = "mail")
    private String mail;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "function")
    private String[] function;

}
