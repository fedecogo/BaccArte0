package fedeCapiz.BaccArte0.repositories;

import fedeCapiz.BaccArte0.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDAO extends JpaRepository<Cart, Long> {

}
