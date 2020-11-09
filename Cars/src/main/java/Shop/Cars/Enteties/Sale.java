package Shop.Cars.Enteties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sales")
public class Sale extends BaseClass {

    private double discount;
    @OneToOne( )
    @JoinColumn(name = "car_id",referencedColumnName = "id")
    private Car car;

    @ManyToOne ()
    @JoinColumn(name = "customer_id",referencedColumnName = "id")
    private Customer customer;

}
