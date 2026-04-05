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
    public ResponseEntity<Chambre> addChambreWithReservation(@RequestBody ChambreReservationRequest req) {
        return ResponseEntity.ok(
                chambreService.addChambreAndReservation(req.getChambre(), req.getReservation())
        );
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

    @GetMapping("/type/{type}")
    public List<Chambre> getByType(@PathVariable TypeChambre type) {
        return chambreService.findByType(type);
    }

    @GetMapping("/numero/{numeroChambre}")
    public List<Chambre> getByNumero(@PathVariable Long numeroChambre) {
        return chambreService.findByNumero(numeroChambre);
    }

    public static class ChambreReservationRequest {
        private Chambre chambre;
        private Reservation reservation;

        public ChambreReservationRequest() {}

        public Chambre getChambre() {
            return chambre;
        }

        public Reservation getReservation() {
            return reservation;
        }

        public void setChambre(Chambre chambre) {
            this.chambre = chambre;
        }

        public void setReservation(Reservation reservation) {
            this.reservation = reservation;
        }
    }
}