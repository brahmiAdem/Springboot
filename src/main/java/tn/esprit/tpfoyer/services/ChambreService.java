package tn.esprit.tpfoyer.services;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entities.Chambre;
import tn.esprit.tpfoyer.entities.Reservation;
import tn.esprit.tpfoyer.entities.TypeChambre;
import tn.esprit.tpfoyer.repositories.ChambreRepository;
import tn.esprit.tpfoyer.repositories.ReservationRepository;

import java.util.List;

@Service
@Slf4j

public class ChambreService implements IChambreService {

    @Autowired
    ChambreRepository chambreRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Override
    public Chambre addOrUpdateChambre(Chambre chambre) {
        return chambreRepository.save(chambre);
    }

    @Override
    public void deleteChambre(long idChambre) {
        chambreRepository.deleteById(idChambre);
    }
/*
        @Override
        public List<Chambre> findAllChambres() {
            return chambreRepository.findAll();
        }
*/
    @Override
    public Chambre findChambre(long idChambre) {
        return chambreRepository.findById(idChambre).orElse(null);
    }

    @Override
    public Chambre addChambreAndReservation(Chambre chambre, Reservation reservation) {
        Reservation savedReservation = reservationRepository.save(reservation);
        chambre.getReservations().add(savedReservation);
        return chambreRepository.save(chambre);
    }

    @Override
    public Chambre reserverChambre(Long idChambre, String idReservation) {
        Chambre chambre = chambreRepository.findById(idChambre)
                .orElseThrow(() -> new RuntimeException("Chambre introuvable id=" + idChambre));

        Reservation reservation = reservationRepository.findById(idReservation)
                .orElseThrow(() -> new RuntimeException("Réservation introuvable id=" + idReservation));

        if (chambre.getReservations().contains(reservation)) {
            return chambre;
        }

        chambre.getReservations().add(reservation);
        reservation.setEstValide(true);
        reservationRepository.save(reservation);

        return chambreRepository.save(chambre);
    }

    @Override
    public Chambre annulerReservation(Long idChambre, String idReservation) {
        Chambre chambre = chambreRepository.findById(idChambre)
                .orElseThrow(() -> new RuntimeException("Chambre introuvable id=" + idChambre));

        Reservation reservation = reservationRepository.findById(idReservation)
                .orElseThrow(() -> new RuntimeException("Réservation introuvable id=" + idReservation));

        boolean removed = chambre.getReservations().remove(reservation);
        if (!removed) {
            return chambre;
        }

        reservation.setEstValide(false);
        reservationRepository.save(reservation);

        return chambreRepository.save(chambre);
    }

    @Override
    public List<Chambre> findByType(TypeChambre type) {
        return chambreRepository.findByTypeC(type);
    }

    @Override
    public List<Chambre> findByNumero(Long numero) {
        return chambreRepository.findByNumeroChambre(numero);
    }



    //jpql

   @Override
    public List<Chambre> getChambresByType(TypeChambre type) {
        return chambreRepository.findChambresByType(type);
    }
    @Override
    public List<Chambre> getChambresValidesByBloc(Long idBloc) {
        return chambreRepository.findChambresValidesByBloc(idBloc);
    }

    @Transactional
    public int updateType(Long id, TypeChambre type) {
        return chambreRepository.updateTypeChambre(type, id);
    }

    //
    @Override
    @Scheduled(cron = "*/15 * 8-11 * * MON-FRI")
    public List<Chambre> findAllChambres() {
        List<Chambre> chambres = chambreRepository.findAll();
        log.info("=== Scheduler Chambres ===");
        chambres.forEach(chambre -> {
            log.info("Chambre -> {}", chambre);
        });
        return chambres;
    }
}