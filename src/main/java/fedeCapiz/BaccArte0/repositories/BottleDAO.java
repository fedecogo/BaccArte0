package fedeCapiz.BaccArte0.repositories;

import fedeCapiz.BaccArte0.entities.Bottle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BottleDAO extends JpaRepository<Bottle,Long> {
        List<Bottle> findByUserIdAndIsCSDeletedFalse(Long userId);
    }
