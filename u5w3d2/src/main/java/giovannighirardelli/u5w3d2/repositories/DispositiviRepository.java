package giovannighirardelli.u5w3d2.repositories;


import giovannighirardelli.u5w3d2.entities.Dispositivi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DispositiviRepository extends JpaRepository<Dispositivi, UUID> {
}
