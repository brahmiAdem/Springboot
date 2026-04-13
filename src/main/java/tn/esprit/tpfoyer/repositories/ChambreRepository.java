package tn.esprit.tpfoyer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.tpfoyer.entities.Chambre;
import tn.esprit.tpfoyer.entities.TypeChambre;

import java.util.List;

public interface ChambreRepository extends JpaRepository<Chambre, Long> {

    List<Chambre> findByTypeC(TypeChambre typeC);

    List<Chambre> findByNumeroChambre(Long numeroChambre);


    //jpql


    @Query("SELECT c FROM Chambre c WHERE c.typeC = :type")
    List<Chambre> findChambresByType(@Param("type") TypeChambre type);


    @Query("SELECT c FROM Chambre c " +  "JOIN c.reservations r " +
            "WHERE c.bloc.idBloc = :idBloc " +
            "AND r.estValide = true")
    List<Chambre> findChambresValidesByBloc(@Param("idBloc") Long idBloc);

    @Modifying
    @Query("UPDATE Chambre c SET c.typeC = :type WHERE c.idChambre = :id")
    int updateTypeChambre(@Param("type") TypeChambre type,
                          @Param("id") Long id);
}