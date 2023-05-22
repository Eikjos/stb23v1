package fr.univrouen.stb23v1.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.xml.bind.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@JacksonXmlRootElement(localName = "stb")
@XmlAccessorType(XmlAccessType.NONE)
public class STBModel {


    @JacksonXmlProperty(isAttribute = true)
    private Long id;

    @JacksonXmlElementWrapper(localName = "title")
    private String title;

    @JacksonXmlElementWrapper(localName = "description")
    private String description;

    @JacksonXmlElementWrapper(localName = "date")
    private String date;

    @JacksonXmlElementWrapper(localName = "client")
    private String client;

    public void setDate(Date date) {
        this.date = new SimpleDateFormat("yyyy-MM-dd").format(date);
    }
}
