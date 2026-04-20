package tn.esprit.tpfoyer.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entities.Bloc;
import tn.esprit.tpfoyer.entities.Foyer;
import tn.esprit.tpfoyer.repositories.BlocRepository;
import tn.esprit.tpfoyer.repositories.FoyerRepository;

import java.util.List;

@Service
@Slf4j
public class BlocService implements IBlocService {

    @Autowired
    FoyerRepository foyerRepository;

    @Autowired
    BlocRepository blocRepository;

    @Override
    public Bloc addOrUpdateBloc(Bloc bloc) {
        return blocRepository.save(bloc);
    }

    @Override
    public void deleteBloc(long idBloc) {
        blocRepository.deleteById(idBloc);
    }

    @Override
    @Scheduled(cron = "0 */10 9-17 * * TUE,FRI")
    public List<Bloc> findAllBlocs() {
        List<Bloc> blocs = blocRepository.findAll();

        log.info("=== Scheduler Blocs ===");
        blocs.forEach(bloc -> log.info("Bloc -> {}", bloc));
        log.info("Total blocs: {}", blocs.size());

        return blocs;
    }

    @Override
    public Bloc findBloc(long idBloc) {
        return blocRepository.findById(idBloc).orElse(null);
    }

    @Override
    public Bloc addBlocAndFoyer(Bloc bloc, Long idFoyer) {
        Foyer foyer = foyerRepository.findById(idFoyer).orElseGet(() -> {
            Foyer newFoyer = new Foyer();
            newFoyer.setNomFoyer("Foyer-" + bloc.getNomBloc());
            newFoyer.setCapaciteFoyer(Long.valueOf(bloc.getCapaciteBloc()));
            return foyerRepository.save(newFoyer);
        });
        bloc.setFoyer(foyer);
        return blocRepository.save(bloc);
    }

    @Override
    public Bloc affecterBlocAFoyer(Long idBloc, Long idFoyer) {
        Bloc bloc = blocRepository.findById(idBloc)
                .orElseThrow(() -> new RuntimeException("Bloc introuvable id=" + idBloc));
        Foyer foyer = foyerRepository.findById(idFoyer)
                .orElseThrow(() -> new RuntimeException("Foyer introuvable id=" + idFoyer));
        bloc.setFoyer(foyer);
        return blocRepository.save(bloc);
    }

    @Override
    public Bloc desaffecterBlocDeFoyer(Long idBloc) {
        Bloc bloc = blocRepository.findById(idBloc)
                .orElseThrow(() -> new RuntimeException("Bloc introuvable id=" + idBloc));
        bloc.setFoyer(null);
        return blocRepository.save(bloc);
    }

    @Override
    public List<Bloc> findBlocsSansFoyer() {
        return blocRepository.findByFoyerIsNull();
    }

    @Override
    public List<Bloc> findBlocsByCapaciteGreaterThan(Long capacite) {
        return blocRepository.findByCapaciteBlocGreaterThan(capacite);
    }

    @Override
    public List<Bloc> findBlocsByNomBlocStartsWith(String prefix) {
        return blocRepository.findByNomBlocStartingWith(prefix);
    }

    @Override
    public List<Bloc> findBlocsByNomBlocStartsWithAndCapaciteGreaterThan(String prefix, Long capacite) {
        return blocRepository.findByNomBlocStartingWithAndCapaciteBlocGreaterThan(prefix, capacite);
    }
}