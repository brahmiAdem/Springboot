package tn.esprit.tpfoyer.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entities.Foyer;
import tn.esprit.tpfoyer.services.IFoyerService;

import java.util.List;

@RestController
@RequestMapping("/foyer")
@AllArgsConstructor
public class FoyerController {
    private final IFoyerService foyerService;

    @PostMapping("/add")
    Foyer addFoyer(@RequestBody Foyer foyer) {
        return foyerService.addOrUpdateFoyer(foyer);
    }

    @PutMapping("/update")
    Foyer updateFoyer(@RequestBody Foyer foyer) {
        return foyerService.addOrUpdateFoyer(foyer);
    }

    @DeleteMapping("/delete/{idFoyer}")
    void deleteFoyer(@PathVariable Long idFoyer) {
        foyerService.deleteFoyer(idFoyer);
    }

    @GetMapping("/findAll")
    List<Foyer> findAllPFoyers() {
        return foyerService.findAllFoyers();
    }

    @GetMapping("/findById/{idFoyer}")
    Foyer findFoyerById(@PathVariable Long idFoyer) {
        return foyerService.findFoyer(idFoyer);
    }
}
