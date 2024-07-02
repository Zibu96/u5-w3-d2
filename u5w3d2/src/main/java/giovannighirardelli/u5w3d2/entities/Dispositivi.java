package giovannighirardelli.u5w3d2.entities;

import giovannighirardelli.u5w3d2.enums.Stato;
import giovannighirardelli.u5w3d2.enums.Tipologia;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Table(name = "dispositivi")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Dispositivi {
    @Id
    @GeneratedValue
    private UUID id;
    @Enumerated(EnumType.STRING)
    private Tipologia tipologia;
    @Enumerated(EnumType.STRING)
    private Stato stato;
    @ManyToOne
    @JoinColumn(name = "dipendenti_id")
    private Dipendenti dipendenti;


    public Dispositivi(Tipologia tipologia, Stato stato, Dipendenti dipendenti) {
        this.tipologia = tipologia;
        this.stato = stato;
        this.dipendenti = null;
    }
}
