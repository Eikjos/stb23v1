package fr.univrouen.stb23v1.entities;

import fr.univrouen.stb23v1.model.MemberModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Member {

    public Member(MemberModel model) {
        lastname = model.getPerson().getLastname();
        firstname = model.getPerson().getFirstname();
        gender = Gender.titleToGender(model.getPerson().getGender());
        mail = model.getMail();
        functions = String.join(";", model.getFunction());
    }

    @Id
    @GeneratedValue
    private Long id;
    private String lastname;
    private String firstname;
    private Gender gender;
    private String mail;
    private String functions;
    @ManyToOne
    private STB stb;

    public String[] getFunctions() {
        var fun = functions.split(";");
        return fun;
    }

    @Override
    public int hashCode() {
        return Math.toIntExact(id);
    }
}
