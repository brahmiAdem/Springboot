package tn.esprit.tpfoyer.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Reservation {
    @Id
    String idReservation;
    Date anneUniversitaire;
    boolean estValide;
    @ManyToMany
    @JsonIgnore
    @ToString.Exclude
    Set<Etudiant> etudiants;

}
