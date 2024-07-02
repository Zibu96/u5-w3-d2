package giovannighirardelli.u5w3d2.repositories;

import giovannighirardelli.u5w3d2.entities.Dipendenti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DipendentiRepository extends JpaRepository<Dipendenti, UUID> {

boolean existsByEmail(String email);
boolean existsByUsername(String username);
    Optional<Dipendenti> findByEmail(String email);
}
