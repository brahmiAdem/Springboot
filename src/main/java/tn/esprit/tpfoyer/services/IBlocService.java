package tn.esprit.tpfoyer.services;

import tn.esprit.tpfoyer.entities.Bloc;

import java.util.List;

public interface IBlocService {
    Bloc addBlocAndFoyer(Bloc bloc, Long idFoyer);
    Bloc affecterBlocAFoyer(Long idBloc, Long idFoyer);
    Bloc desaffecterBlocDeFoyer(Long idBloc);
    Bloc addOrUpdateBloc(Bloc bloc);
    void deleteBloc(long idBloc);
    List<Bloc> findAllBlocs();
    Bloc findBloc(long idBloc);
    List<Bloc> findBlocsSansFoyer();
    List<Bloc> findBlocsByCapaciteGreaterThan(Long capacite);
    List<Bloc> findBlocsByNomBlocStartsWith(String prefix);
    List<Bloc> findBlocsByNomBlocStartsWithAndCapaciteGreaterThan(String prefix, Long capacite);
}
