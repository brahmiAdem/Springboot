package tn.esprit.tpfoyer.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entities.Bloc;
import tn.esprit.tpfoyer.services.IBlocService;

import java.util.List;

@RestController
@RequestMapping("/bloc")
@AllArgsConstructor
public class BlocController {
    private final IBlocService blocService;

    @PostMapping("/add")
    Bloc addBloc(@RequestBody Bloc bloc) {
        return blocService.addOrUpdateBloc(bloc);
    }

    @PutMapping("/update")
    Bloc updateBloc(@RequestBody Bloc bloc) {
        return blocService.addOrUpdateBloc(bloc);
    }

    @DeleteMapping("/delete/{idBloc}")
    void deleteBloc(@PathVariable Long idBloc) {
        blocService.deleteBloc(idBloc);
    }

    @GetMapping("/findAll")
    List<Bloc> findAllPBlocs() {
        return blocService.findAllBlocs();
    }

    @GetMapping("/findById/{idBloc}")
    Bloc findBlocById(@PathVariable Long idBloc) {
        return blocService.findBloc(idBloc);
    }
    @PostMapping("/add-with-foyer/{idFoyer}")
    public ResponseEntity<Bloc> addBlocWithFoyer(@RequestBody Bloc bloc,
                                                 @PathVariable Long idFoyer) {
        return ResponseEntity.ok(blocService.addBlocAndFoyer(bloc, idFoyer));
    }


    @PutMapping("/affecter/{idBloc}/foyer/{idFoyer}")
    public ResponseEntity<Bloc> affecterBlocAFoyer(@PathVariable Long idBloc,
                                                   @PathVariable Long idFoyer) {
        return ResponseEntity.ok(blocService.affecterBlocAFoyer(idBloc, idFoyer));
    }


    @PutMapping("/desaffecter/{idBloc}")
    public ResponseEntity<Bloc> desaffecterBlocDeFoyer(@PathVariable Long idBloc) {
        return ResponseEntity.ok(blocService.desaffecterBlocDeFoyer(idBloc));
    }

}
