package tn.esprit.tpfoyer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.tpfoyer.entities.Etudiant;

import java.util.List;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

    @Query("SELECT e FROM Etudiant e WHERE SIZE(e.reservations) > " +
            "(SELECT AVG(SIZE(e2.reservations)) FROM Etudiant e2)")
    List<Etudiant> findEtudiantsAboveAverageReservations();
}