package tn.esprit.tpfoyer.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entities.Etudiant;
import tn.esprit.tpfoyer.services.IEtudiantService;

import java.util.List;

@RestController
@RequestMapping("/etudiant")
@AllArgsConstructor
public class EtudiantController {

    private final IEtudiantService etudiantService;

    @PostMapping("/add")
    Etudiant addEtudiant(@RequestBody Etudiant etudiant) {
        return etudiantService.addOrUpdateEtudiant(etudiant);
    }

    @PutMapping("/update")
    Etudiant updateEtudiant(@RequestBody Etudiant etudiant) {
        return etudiantService.addOrUpdateEtudiant(etudiant);
    }

    @DeleteMapping("/delete/{idEtudiant}")
    void deleteEtudiant(@PathVariable Long idEtudiant) {
        etudiantService.deleteEtudiant(idEtudiant);
    }

    @GetMapping("/findAll")
    List<Etudiant> findAllPEtudiants() {
        return etudiantService.findAllEtudiants();
    }

    @GetMapping("/findById/{idEtudiant}")
    Etudiant findEtudiantById(@PathVariable Long idEtudiant) {
        return etudiantService.findEtudiant(idEtudiant);
    }

    // JPQL
    @GetMapping("/jpql/above-average-reservations")
    public List<Etudiant> getEtudiantsAboveAverageReservations() {
        return etudiantService.getEtudiantsAboveAverageReservations();
    }
}