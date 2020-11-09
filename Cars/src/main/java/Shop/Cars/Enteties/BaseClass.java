package Shop.Cars.Enteties;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor

public class BaseClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;


}
