package fedeCapiz.BaccArte0.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Address {

    //CSV
    @Id
    private Long id;
    private String nomeComune;
    private String provincia ;
    private String regione;
    private String via;
}
