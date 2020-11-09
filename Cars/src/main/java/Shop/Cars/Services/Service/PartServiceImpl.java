package Shop.Cars.Services.Service;


import Shop.Cars.Enteties.Part;
import Shop.Cars.Enteties.Supplier;
import Shop.Cars.Repositories.PartRepository;
import Shop.Cars.Repositories.SupplierRepository;
import Shop.Cars.Services.SeedDto.Parts.PartsRootDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PartServiceImpl implements PartService {

    private final PartRepository partRepository;
    private final SupplierRepository supplierRepository;
    private final Random random;
    private final ModelMapper modelMapper;

    @Autowired
    public PartServiceImpl(PartRepository partRepository, SupplierRepository supplierRepository, Random random, ModelMapper modelMapper) {
        this.partRepository = partRepository;
        this.supplierRepository = supplierRepository;
        this.random = random;
        this.modelMapper = modelMapper;
    }

    @Override
    public void importParts(PartsRootDto partsRootDto) {
        partsRootDto.getParts().forEach(p -> {
            if (this.partRepository.
                    findByNameAndPrice(p.getName(), p.getPrice()) == null) {
                int randomSupplier = random.nextInt(this.supplierRepository.findAll().size()) + 1;
                Supplier supplier = this.supplierRepository.
                        findById(randomSupplier);
                Part part = this.modelMapper.map(p, Part.class);
                part.setSupplier(supplier);
                this.partRepository.saveAndFlush(part);
            } else {
                System.out.println("Part with that name and price exist.");
            }
        });


    }
}
