package fedeCapiz.BaccArte0.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

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
    @Enumerated(EnumType.STRING)
    private BottleContents bottleContents;
    @Enumerated(EnumType.STRING)
    private Artist artist;
    private double price;
    @Column( columnDefinition = "text")
    private String bottigliCompleta;
    private String logoUser;
    private boolean isCustom;
    private boolean isCSDeleted;


    //relazione con user, bottiglia creata da questo user
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;


    //relazione con carrello
    @ManyToMany(mappedBy = "bottles")
    @JsonIgnore
    private List<Cart> carts = new ArrayList<>();


}
