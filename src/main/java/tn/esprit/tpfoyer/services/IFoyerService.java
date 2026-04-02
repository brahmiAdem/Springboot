package tn.esprit.tpfoyer.services;

import tn.esprit.tpfoyer.entities.Foyer;

import java.util.List;

public interface IFoyerService {
    Foyer addOrUpdateFoyer(Foyer foyer);
    void deleteFoyer(long idFoyer);
    List<Foyer> findAllFoyers();
    Foyer findFoyer(long idFoyer);
}
