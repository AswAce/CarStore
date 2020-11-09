package Shop.Cars.Services.Service;

import Shop.Cars.Enteties.Supplier;
import Shop.Cars.Repositories.SupplierRepository;
import Shop.Cars.Services.SeedDto.Suppliers.SupplierDto;
import Shop.Cars.Services.SeedDto.Suppliers.SupplierRootDto;
import Shop.Cars.Utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validator;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository, ModelMapper modelMapper, ValidationUtil validator) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;

        this.validator = validator;
    }

    @Override
    public void insertSuppliers(SupplierRootDto supplierRootDto) {
        supplierRootDto.
                getSuppliers().stream().
                forEach(supplierDto -> {
                            if (this.validator.isValid(supplierDto)) {
                                if (
                                        this.supplierRepository.
                                                findByName(supplierDto.getName()) == null) {
                                    Supplier supplier = this.modelMapper.map(supplierDto, Supplier.class);
                                    this.supplierRepository.saveAndFlush(supplier);
                                } else {
                                    System.out.println("Supplier already inside the DB");
                                }
                            } else {
                                this.validator.getViolation(supplierDto).
                                        stream().map(ConstraintViolation::getMessage).
                                        forEach(System.out::println);
                            }
                        }
                );
    }

    @Override
    public Supplier getSupplierById(int id) {
        return this.supplierRepository.findById(id);

    }
}
