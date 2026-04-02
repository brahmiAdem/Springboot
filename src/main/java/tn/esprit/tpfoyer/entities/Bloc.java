package tn.esprit.tpfoyer.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Bloc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idBloc;
    String nomBloc;
    String capaciteBloc;
    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    Foyer foyer;
    @OneToMany (mappedBy = "bloc")
    @JsonIgnore
    @ToString.Exclude
    Set<Chambre> Chambres = new HashSet<>();
}
