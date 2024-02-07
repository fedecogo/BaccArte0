package fedeCapiz.BaccArte0.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
@JsonIgnoreProperties({"password",/*"cart","payments","bottles"*/"authorities", "accountNonExpired", "enabled", "accountNonLocked", "credentialsNonExpired", "username"})

public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;
    private String name;
    private String surname;
    private String username;
    private String email;
    private String password;
    private String avatar;
    private Long phoneNumber;
    @Enumerated(EnumType.STRING)
    private TypeOfUser typeOfUser;


    @OneToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    /*
        //RELAZIONE CON ORDER
        @OneToMany(mappedBy = "user")
        private List<Payment> payments;

        //RELAZIONE CON BOTTTLE
        @OneToMany(mappedBy = "user")
        private List<Bottle> bottles;

     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(typeOfUser.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }
    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
