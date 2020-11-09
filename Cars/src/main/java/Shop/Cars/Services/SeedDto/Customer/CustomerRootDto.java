package Shop.Cars.Services.SeedDto.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.List;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "customers")
public class CustomerRootDto {

    @XmlElement(name = "customer")
    private List<CustomerDto> customers;


}
