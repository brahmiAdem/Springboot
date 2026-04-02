package tn.esprit.tpfoyer.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entities.Chambre;
import tn.esprit.tpfoyer.entities.Reservation;
import tn.esprit.tpfoyer.entities.TypeChambre;
import tn.esprit.tpfoyer.services.IChambreService;

import java.util.List;

@RestController
@RequestMapping("/chambre")
@AllArgsConstructor
public class ChambreController {
    private final IChambreService chambreService;

    @PostMapping("/add")
    Chambre addChambre(@RequestBody Chambre chambre) {
        return chambreService.addOrUpdateChambre(chambre);
    }

    @PutMapping("/update")
    Chambre updateChambre(@RequestBody Chambre chambre) {
        return chambreService.addOrUpdateChambre(chambre);
    }

    @DeleteMapping("/delete/{idChambre}")
    void deleteChambre(@PathVariable Long idChambre) {
        chambreService.deleteChambre(idChambre);
    }

    @GetMapping("/findAll")
    List<Chambre> findAllPChambres() {
        return chambreService.findAllChambres();
    }

    @GetMapping("/findById/{idChambre}")
    Chambre findChambreById(@PathVariable Long idChambre) {
        return chambreService.findChambre(idChambre);
    }
    @PostMapping("/add-with-reservation")
    public ResponseEntity<Chambre> addChambreWithReservation(
            @RequestBody ChambreReservationRequest req) {
        return ResponseEntity.ok(
                chambreService.addChambreAndReservation(req.getChambre(), req.getReservation()));
    }


    @PutMapping("/reserver/{idChambre}/reservation/{idReservation}")
    public ResponseEntity<Chambre> reserverChambre(@PathVariable Long idChambre,
                                                   @PathVariable String idReservation) {
        return ResponseEntity.ok(chambreService.reserverChambre(idChambre, idReservation));
    }


    @PutMapping("/annuler/{idChambre}/reservation/{idReservation}")
    public ResponseEntity<Chambre> annulerReservation(@PathVariable Long idChambre,
                                                      @PathVariable String idReservation) {
        return ResponseEntity.ok(chambreService.annulerReservation(idChambre, idReservation));
    }
    public static class ChambreReservationRequest {
        private Chambre chambre;
        private Reservation reservation;

        public ChambreReservationRequest() {}

        public Chambre getChambre()             { return chambre; }
        public Reservation getReservation()     { return reservation; }
        public void setChambre(Chambre c)       { this.chambre = c; }
        public void setReservation(Reservation r) { this.reservation = r; }
    }

    @GetMapping("/type/{typeC}")
    public List<Chambre> getChambresByType(@PathVariable TypeChambre typeC) {
        return chambreService.findChambresByType(typeC);
    }

    @GetMapping("/numero/{numeroChambre}")
    public Chambre getChambreByNumero(@PathVariable Long numeroChambre) {
        return chambreService.findChambreByNumero(numeroChambre);
    }

}
