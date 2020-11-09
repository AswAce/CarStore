package Shop.Cars.Services.SeedDto.Customer;


import Shop.Cars.Adaptors.DateAdapter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "customer")
public class CustomerDto {
    @XmlAttribute
    @NotNull(message = "name can't be null")
    @NonNull
    private String name;

    @NotNull
    @XmlElement(name = "birth-date")
    @XmlJavaTypeAdapter(DateAdapter.class)
    private LocalDateTime birthDate;


    @XmlElement(name = "is-young-driver")
    private boolean isYoungDriver;


}
