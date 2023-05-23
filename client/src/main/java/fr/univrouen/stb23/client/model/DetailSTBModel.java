package fr.univrouen.stb23.client.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JacksonXmlRootElement(localName = "stb", namespace = "")
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

    public Date getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date a;
        try {
            a = formatter.parse(date);
            return a;
        } catch (Exception e) {
            return null;
        }
    }
}
