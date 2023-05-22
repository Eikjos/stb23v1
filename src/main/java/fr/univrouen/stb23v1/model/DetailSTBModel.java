package fr.univrouen.stb23v1.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.univrouen.stb23v1.entities.Client;
import fr.univrouen.stb23v1.entities.Feature;
import fr.univrouen.stb23v1.entities.Member;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JacksonXmlRootElement(localName = "stb", namespace = "")
@XmlRootElement(name = "stb")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DetailSTBModel {

    @JacksonXmlProperty(isAttribute = true)
    private Long id;

    @JacksonXmlProperty(localName = "title")
    private String title;

    @JacksonXmlProperty(localName = "description")
    private String description;

    @JacksonXmlProperty(localName = "version")
    private Double version;

    @JacksonXmlProperty(localName = "date")
    private String date;

    @JacksonXmlProperty(localName = "client")
    private ClientModel client;


    @JacksonXmlElementWrapper(localName = "team")
    @JacksonXmlProperty(localName = "member")
    private List<MemberModel> member;

    @JacksonXmlElementWrapper(localName = "features")
    @JacksonXmlProperty(localName = "feature")
    private List<FeatureModel> feature;

    public void setClient(ClientModel client) {
        this.client = client;
    }

    public void setDate(Date date) {
        this.date = new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public void setMember(List<MemberModel> members) {
        this.member = members;
    }

    public void setFeature(List<FeatureModel> features) {
        this.feature = features;
    }
}
