package Shop.Cars.Enteties;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "parts")
public class Part extends BaseClass {

    private long id;
    private String name;
    private BigDecimal price;
    private String quantity;
    @ManyToOne(targetEntity = Supplier.class)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ManyToMany(targetEntity = Car.class, mappedBy = "parts")

    private List<Car> cars;
}
