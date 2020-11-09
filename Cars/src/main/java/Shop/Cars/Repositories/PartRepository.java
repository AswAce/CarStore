package Shop.Cars.Repositories;

import Shop.Cars.Enteties.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {

    Part findByNameAndPrice(String name, BigDecimal price);

    Part findById(long id);
}
