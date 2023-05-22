package fr.univrouen.stb23v1.entities;

import fr.univrouen.stb23v1.model.FeatureModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Feature {

    public Feature(FeatureModel model) throws ParseException {
        name = model.getName();
        description = model.getDescription();
        section = model.getSection();
        number = model.getNumber();
        priority = model.getPriority();
        if (model.getDelivery() != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            delivery = formatter.parse(model.getDelivery());
        }
        comment = model.getComment();
    }

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private Integer section;
    private Integer number;
    private Integer priority;
    private Date delivery;
    private String comment;
    @ManyToOne
    private STB stb;
}
