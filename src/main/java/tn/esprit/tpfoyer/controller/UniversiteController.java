package tn.esprit.tpfoyer.controller;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entities.Universite;
import tn.esprit.tpfoyer.services.IUniversiteService;

import java.util.List;

@RestController
@RequestMapping("/universite")
@AllArgsConstructor
public class UniversiteController {
    private final IUniversiteService universiteService;

    @PostMapping("/add")
    Universite addUniversite(@RequestBody Universite universite) {
        return universiteService.addOrUpdateUniversite(universite);
    }

    @PutMapping("/update")
    Universite updateUniversite(@RequestBody Universite universite) {
        return universiteService.addOrUpdateUniversite(universite);
    }

    @DeleteMapping("/delete/{idUniversite}")
    void deleteUniversite(@PathVariable Long idUniversite) {
        universiteService.deleteUniversite(idUniversite);
    }

    @GetMapping("/findAll")
    List<Universite> findAllPUniversities() {
        return universiteService.findAllUniversities();
    }

    @GetMapping("/findById/{idUniversite}")
    Universite findUniversiteById(@PathVariable Long idUniversite) {
        return universiteService.findUniversite(idUniversite);
    }
}
