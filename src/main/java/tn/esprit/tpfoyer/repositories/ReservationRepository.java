package tn.esprit.tpfoyer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tpfoyer.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, String> {
}
