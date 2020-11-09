package Shop.Cars.Enteties;


import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cars")
public class Car extends BaseClass {


    private String make;
    private String model;
    @Column(name = "travelled_distance")
    private long travelledDistance;

    @ManyToMany(targetEntity = Part.class)
    @JoinTable(name = "parts_cars",
            joinColumns = @JoinColumn(name = "car_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "part_id", referencedColumnName = "id")
    )
    private List<Part> parts;
    @OneToOne()
    private Sale sale;

}
