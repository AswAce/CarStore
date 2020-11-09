package Shop.Cars.Services.Service;


import Shop.Cars.Services.SeedDto.Customer.CustomerRootDto;
import Shop.Cars.Services.ViewDTO.CustomerRootDtoView;

public interface CustomerService {

    void insertAllCustomers(CustomerRootDto customerRootDto);

    CustomerRootDtoView getAllOrderedByDate();

}
