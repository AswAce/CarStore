package Shop.Cars.Services.ViewDTO;


import Shop.Cars.Adaptors.DateAdapter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name ="customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerDtoView {
    @XmlElement
    private long id;
    @XmlElement
    private String name;

    @XmlElement(name = "birth-date")
    @XmlJavaTypeAdapter(DateAdapter.class)
    private LocalDateTime birthDate;

    @XmlElement(name ="is-younger-driver")
    private boolean isYoungDriver;

}
