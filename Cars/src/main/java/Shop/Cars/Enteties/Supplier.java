package Shop.Cars.Enteties;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "suppliers")
public class Supplier extends BaseClass {

    @NotNull(message = "Name can not be null!")
    private String name;
    @Column(name = "is_importer")
    private boolean isImporter;

    @OneToMany(targetEntity = Part.class, mappedBy = "supplier")
    private List<Part> parts;
}
