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
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double totCartPrice = 0;


    //relazione con bottle
    @ManyToMany
    @JoinTable(
            name = "cart_bottles",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "bottle_id")
    )
    private List<Bottle> bottles = new ArrayList<>();

    //relazione con user
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


    //relazione con order
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

}