package fr.univrouen.stb23v1.entities;

import fr.univrouen.stb23v1.model.DetailSTBModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "stb")
public class STB {

    public STB(DetailSTBModel model, Client client) throws ParseException {
        title = model.getTitle();
        description = model.getDescription();
        version = model.getVersion();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        date = formatter.parse(model.getDate());
        this.client = client;
    }

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;
    private double version;
    private Date date;
    @ManyToOne(optional = false, fetch =  FetchType.EAGER, cascade = CascadeType.ALL)
    private Client client;
    @OneToMany(fetch =   FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Member> team;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Feature> features;
}
