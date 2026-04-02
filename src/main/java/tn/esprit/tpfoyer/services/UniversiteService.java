package tn.esprit.tpfoyer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entities.Universite;
import tn.esprit.tpfoyer.repositories.UniversiteRepository;

import java.util.List;

@Service
public class UniversiteService implements IUniversiteService {
    @Autowired
    UniversiteRepository universiteRepository;
    @Override
    public Universite addOrUpdateUniversite(Universite universite) {
        return universiteRepository.save(universite);
    }

    @Override
    public void deleteUniversite(long idUniversite) {
        universiteRepository.deleteById(idUniversite);
    }

    @Override
    public List<Universite> findAllUniversities() {
        return universiteRepository.findAll();
    }

    @Override
    public Universite findUniversite(long idUniversite) {
        return universiteRepository.findById(idUniversite).orElse(null);
    }
}
