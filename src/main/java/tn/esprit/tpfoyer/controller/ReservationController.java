package tn.esprit.tpfoyer.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entities.Reservation;
import tn.esprit.tpfoyer.services.IReservationService;
import tn.esprit.tpfoyer.services.ReservationService;

import java.util.List;
@RestController
@RequestMapping("/reservation")
@AllArgsConstructor
public class ReservationController {
    private final IReservationService reservationService;

    @PostMapping("/add")
    Reservation addReservation(@RequestBody Reservation reservation) {
        return reservationService.addOrUpdateReservation(reservation);
    }

    @PutMapping("/update")
    Reservation updateReservation(@RequestBody Reservation reservation) {
        return reservationService.addOrUpdateReservation(reservation);
    }

    @DeleteMapping("/delete/{idReservation}")
    void deleteReservation(@PathVariable String idReservation) {
        reservationService.deleteReservation(idReservation);
    }

    @GetMapping("/findAll")
    List<Reservation> findAllPReservations() {
        return reservationService.findAllReservations();
    }

    @GetMapping("/findById/{idReservation}")
    Reservation findReservationById(@PathVariable String idReservation) {
        return reservationService.findReservation(idReservation);
    }

}
