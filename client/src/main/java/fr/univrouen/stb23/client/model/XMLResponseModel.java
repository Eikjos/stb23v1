package fr.univrouen.stb23.client.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import fr.univrouen.stb23.client.util.Status;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "error")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class XMLResponseModel {

    @JacksonXmlProperty(localName = "id")
    public Long id;

    @JacksonXmlProperty(localName = "status")
    public Status status;

    @JacksonXmlProperty(localName =  "message")
    public String message;
}
