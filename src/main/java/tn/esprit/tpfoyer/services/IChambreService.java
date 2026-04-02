package tn.esprit.tpfoyer.services;

import tn.esprit.tpfoyer.entities.Chambre;
import tn.esprit.tpfoyer.entities.Reservation;

import java.util.List;

public interface IChambreService {
    Chambre addOrUpdateChambre(Chambre chambre);
    void deleteChambre(long idChambre);
    List<Chambre> findAllChambres();
    Chambre findChambre(long idChambre);
    Chambre addChambreAndReservation(Chambre chambre, Reservation reservation);
    Chambre reserverChambre(Long idChambre, String idReservation);
    Chambre annulerReservation(Long idChambre, String idReservation);
}
