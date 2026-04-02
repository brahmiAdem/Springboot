package tn.esprit.tpfoyer.services;

import tn.esprit.tpfoyer.entities.Etudiant;

import java.util.List;

public interface IEtudiantService {
    Etudiant addOrUpdateEtudiant(Etudiant etudiant);
    void deleteEtudiant(long idEtudiant);
    List<Etudiant> findAllEtudiants();
    Etudiant findEtudiant(long idEtudiant);
}
