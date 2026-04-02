package tn.esprit.tpfoyer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entities.Chambre;
import tn.esprit.tpfoyer.entities.Reservation;
import tn.esprit.tpfoyer.entities.TypeChambre;
import tn.esprit.tpfoyer.repositories.ChambreRepository;
import tn.esprit.tpfoyer.repositories.ReservationRepository;

import java.util.List;
@Service
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

    @Override
    public List<Chambre> findAllChambres() {
        return chambreRepository.findAll();
    }

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
    public List<Chambre> findChambresByType(TypeChambre typeC) {
        return chambreRepository.findByTypeC(typeC);
    }

    @Override
    public Chambre findChambreByNumero(Long numeroChambre) {
        return chambreRepository.findByNumeroChambre(numeroChambre);
    }
}
