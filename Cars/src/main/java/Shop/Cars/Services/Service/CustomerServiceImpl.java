package Shop.Cars.Services.Service;


import Shop.Cars.Enteties.Customer;
import Shop.Cars.Repositories.CustomerRepository;
import Shop.Cars.Services.SeedDto.Customer.CustomerRootDto;
import Shop.Cars.Services.ViewDTO.CustomerDtoView;
import Shop.Cars.Services.ViewDTO.CustomerRootDtoView;
import Shop.Cars.Utils.ValidationUtilImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {


    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtilImpl validationUtil;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper, ValidationUtilImpl validationUtil) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void insertAllCustomers(CustomerRootDto customerRootDto) {
        customerRootDto.getCustomers().forEach(customerDto -> {
            if (this.validationUtil.isValid(customerDto)) {
                if (this.customerRepository.
                        findByNameAndBirthDate(customerDto.getName(),

                                customerDto.getBirthDate()) == null) {
                    Customer newCustomer = this.modelMapper.map(customerDto, Customer.class);
                    this.customerRepository.saveAndFlush(newCustomer);
                } else {
                    System.out.println("UserExist");
                }
            } else {
                this.validationUtil.getViolation(customerDto).forEach(c ->
                        System.out.println(c.getMessage()));
            }


        });


    }

    @Override
    public CustomerRootDtoView getAllOrderedByDate() {
        List<Customer> all = this.customerRepository.findAll();
        CustomerRootDtoView customerRootDtoView = new CustomerRootDtoView();
        List<CustomerDtoView> customers = new ArrayList<>();

        for (Customer customer : all) {
            CustomerDtoView dtoView = this.modelMapper.map(customer, CustomerDtoView.class);
            customers.add(dtoView);
        }

        List<CustomerDtoView> collect = new ArrayList<>(customers);
        customerRootDtoView.setCustomers(collect);
        System.out.println();
        return customerRootDtoView;
    }
}
