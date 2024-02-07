package fedeCapiz.BaccArte0.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    //relazione one to one con carello
    @OneToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    //relazione con payment
    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;




//relazione con user
/*    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;*/
    // non so se ha senso fare questo, l'id dello user arriva dal carrello



}


