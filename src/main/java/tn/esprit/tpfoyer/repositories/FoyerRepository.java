package tn.esprit.tpfoyer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tpfoyer.entities.Foyer;

public interface FoyerRepository extends JpaRepository<Foyer, Long> {
}
