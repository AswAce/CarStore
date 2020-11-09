package Shop.Cars.Services.Service;

import Shop.Cars.Enteties.Supplier;
import Shop.Cars.Services.SeedDto.Suppliers.SupplierRootDto;

public interface SupplierService {
    void insertSuppliers(SupplierRootDto supplierRootDto);

    Supplier getSupplierById(int id);


}
