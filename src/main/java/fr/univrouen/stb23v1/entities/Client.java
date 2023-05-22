package fr.univrouen.stb23v1.entities;

import fr.univrouen.stb23v1.model.ClientModel;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Client {

    public Client(ClientModel model) {
        entity = model.getEntity();
        firstname = model.getPerson().getFirstname();
        lastname = model.getPerson().getLastname();
        gender = Gender.titleToGender(model.getPerson().getGender());
        if (model.getTel() != null) {
            phones = String.join(";", model.getTel());
        }
        if (model.getMail() != null) {
            mails = String.join(";", model.getMail());
        }
    }

    @Id
    @GeneratedValue
    private Long id;
    private String entity;
    private String firstname;
    private String lastname;
    private Gender gender;
    private String phones;
    private String mails;
}
