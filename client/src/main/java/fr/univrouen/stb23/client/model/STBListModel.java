package fr.univrouen.stb23.client.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "result")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class STBListModel {

    @JacksonXmlElementWrapper(useWrapping = false)
    private List<STBModel> stb;

}
