package fedeCapiz.BaccArte0.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


/*    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;*/

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

}
