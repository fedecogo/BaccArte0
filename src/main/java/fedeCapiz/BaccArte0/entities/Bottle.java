package fedeCapiz.BaccArte0.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "bottles")
public class Bottle {
    @Id
    @GeneratedValue
    private long id_bottle;
    @Enumerated(EnumType.STRING)
    private SizeBottle sizeBottle;
    // NON è 100 MA 70

    @Enumerated(EnumType.STRING)
    private BottleContents bottleContents;
    private double price;
    //IMMAGINE BOTTIGLIA COMPLETA
    private String bottigliCompleta;
    private String logoUser;
    //relazione con user
    //bottiglia creata da questo user
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;



    //relazione con carrello
    @ManyToMany(mappedBy = "bottles")
    private List<Cart> carts = new ArrayList<>();


}
