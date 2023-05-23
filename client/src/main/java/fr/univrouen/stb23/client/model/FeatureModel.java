package fr.univrouen.stb23.client.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "feature")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FeatureModel {

    @JacksonXmlProperty(isAttribute = true)
    private String name;

    @JacksonXmlProperty(isAttribute = true)
    private Integer section;

    @JacksonXmlProperty(isAttribute = true)
    private Integer number;

    @JacksonXmlProperty(localName = "description")
    private String description;

    @JacksonXmlProperty(localName = "priority")
    private Integer priority;

    @JacksonXmlProperty(localName = "delivery")
    private String delivery;

    @JacksonXmlProperty(localName = "comment")
    private String comment;

    public void setDelivery(Date date) {
        if (date != null) {
            delivery = new SimpleDateFormat("yyyy-MM-dd").format(date);
        }
    }

    public Date getDelivery()  {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date a;
        try {
            a = formatter.parse(delivery);
            return a;
        } catch (Exception e) {
            return null;
        }
    }
}
