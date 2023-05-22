package fr.univrouen.stb23v1.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import fr.univrouen.stb23v1.entities.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MemberModel {

    @JacksonXmlElementWrapper(localName = "person")
    private PersonModel person;

    @JacksonXmlProperty(localName = "mail")
    private String mail;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "function")
    private String[] function;

    public MemberModel(Member member) {
        person = new PersonModel(member.getFirstname(), member.getGender().name(), member.getLastname());
        mail = member.getMail();
        function = member.getFunctions();
    }
}
