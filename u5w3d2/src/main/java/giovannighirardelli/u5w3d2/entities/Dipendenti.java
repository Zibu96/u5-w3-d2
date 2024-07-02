package giovannighirardelli.u5w3d2.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Table(name = "dipendenti")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Dipendenti {
    @Id
    @GeneratedValue
    private UUID id;
    private String username;
    private String name;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    @Column(name= "profile_pic")
    private String profilePic;
    private String password;

    public Dipendenti(String username, String name, String lastName, String email, String profilePic, String password) {
        this.username = username;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.profilePic = profilePic;
        this.password= password;
    }
}
