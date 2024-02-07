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

    private double price;

    @Enumerated(EnumType.STRING)
    private TypeOfBottle typeOfBottle;

/*
    //relazione con user
    //bottiglia creata da questo user
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
*/
    //relazione con carrello
    @ManyToMany(mappedBy = "bottles")
    private List<Cart> carts = new ArrayList<>();


}
