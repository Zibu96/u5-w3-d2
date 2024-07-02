package giovannighirardelli.u5w3d2.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import giovannighirardelli.u5w3d2.enums.Ruolo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "dipendenti")
@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties({"password", "ruolo", "enabled", "authorities", "accountNonExpired", "credentialsNonExpired", "accountNonLocked"})
public class Dipendenti implements UserDetails {
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
    @Enumerated(EnumType.STRING)
    private Ruolo ruolo;

    public Dipendenti(String username, String name, String lastName, String email, String profilePic, String password) {
        this.username = username;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.profilePic = profilePic;
        this.password= password;
        this.ruolo= Ruolo.USER;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.ruolo.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
