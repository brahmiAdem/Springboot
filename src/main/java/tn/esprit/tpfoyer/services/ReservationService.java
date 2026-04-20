package tn.esprit.tpfoyer.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entities.Reservation;
import tn.esprit.tpfoyer.repositories.ReservationRepository;

import java.util.List;

@Service
@Slf4j
public class ReservationService implements IReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    @Override
    public Reservation addOrUpdateReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public void deleteReservation(String idReservation) {
        reservationRepository.deleteById(idReservation);
    }

    @Override
    @Scheduled(cron = "0 15,45 8 * * MON")
    public List<Reservation> findAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();

        log.info("=== Scheduler Reservations ===");
        reservations.forEach(reservation -> log.info("Reservation -> {}", reservation));
        log.info("Total reservations: {}", reservations.size());

        return reservations;
    }

    @Override
    public Reservation findReservation(String idReservation) {
        return reservationRepository.findById(idReservation).orElse(null);
    }
}