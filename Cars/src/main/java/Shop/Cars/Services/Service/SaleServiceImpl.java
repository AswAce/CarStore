package Shop.Cars.Services.Service;

import Shop.Cars.Enteties.Car;
import Shop.Cars.Enteties.Customer;
import Shop.Cars.Enteties.Sale;
import Shop.Cars.Repositories.CarRepository;
import Shop.Cars.Repositories.CustomerRepository;
import Shop.Cars.Repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final Random random;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository, CarRepository carRepository, CustomerRepository customerRepository, Random random) {
        this.saleRepository = saleRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.random = random;
    }


    @Override
    public void createSale() {
        for (int i = 0; i < 20; i++) {
            int randomCar = this.random.nextInt(this.carRepository.findAll().size()) + 1;
            int randomCustomer = this.random.nextInt(this.customerRepository.findAll().size()) + 1;
            Customer customer = this.customerRepository.findById((long) randomCustomer).orElse(null);
            Car car = this.carRepository.findById((long) randomCar).orElse(null);
            Sale sale = new Sale();
            sale.setDiscount(getDiscount());
            sale.setCar(car);
            sale.setCustomer(customer);
            this.saleRepository.saveAndFlush(sale);
        }


    }

    @Override
    public double getDiscount() {
        double[] discounts = {0, 0.05, 0.01, 0.15, 0.2, 0.3, 0.4, 0.5};
        int n = this.random.nextInt(discounts.length);
        return discounts[n];
    }
}
