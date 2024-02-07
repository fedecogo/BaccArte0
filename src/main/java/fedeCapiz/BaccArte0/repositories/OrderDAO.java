package fedeCapiz.BaccArte0.repositories;
import fedeCapiz.BaccArte0.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDAO extends JpaRepository<Order, Long> {

}
