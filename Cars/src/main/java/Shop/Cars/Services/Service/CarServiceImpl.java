package Shop.Cars.Services.Service;

import Shop.Cars.Enteties.Car;
import Shop.Cars.Enteties.Part;
import Shop.Cars.Repositories.CarRepository;
import Shop.Cars.Repositories.PartRepository;
import Shop.Cars.Services.SeedDto.Cars.CarsRootDto;
import Shop.Cars.Services.ViewDTO.CarDtoVIew;
import Shop.Cars.Services.ViewDTO.CarRootDtoView;
import Shop.Cars.Utils.ValidationUtilImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.validation.ConstraintViolation;
import java.util.*;

@Service

public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final Random random;
    private final PartRepository partRepository;
    private final ValidationUtilImpl validationUtil;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper, Random random, PartRepository partRepository, ValidationUtilImpl validationUtil) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.random = random;
        this.partRepository = partRepository;
        this.validationUtil = validationUtil;
    }


    @Override

    public void insertCars(CarsRootDto carsRootDto) {

        carsRootDto.getCars().
                stream().forEach(c -> {
            if (this.validationUtil.isValid(c)) {

                Car car = this.modelMapper.map(c, Car.class);


                car.setParts(this.getRandomParts());

                this.carRepository.saveAndFlush(car);
            } else {
                this.validationUtil.
                        getViolation(c).
                        stream().
                        map(ConstraintViolation::getMessage).
                        forEach(System.out::println);
            }
        });

    }

    @Override
    public CarRootDtoView getCarsFromBrandOrderByModel(String brandName) {
        List<Car> cars = this.carRepository.
                findByMakeOrderByModelAscTravelledDistanceDesc(brandName);

        CarRootDtoView carRootDtoView=new CarRootDtoView();
        List<CarDtoVIew> carsDto=new ArrayList<>();
        for (Car car : cars) {
            CarDtoVIew carDto = this.modelMapper.map(car, CarDtoVIew.class);
carsDto.add(carDto);
        }
        carRootDtoView.setCars(carsDto);



        return  carRootDtoView;
    }

    private List<Part> getRandomParts() {
        List<Part> parts = new ArrayList<>();
        Random random = new Random();

        List<Part> partEntities = this.partRepository.findAll();

        int length = random.nextInt(10) + 10;

        for (int i = 0; i < length; i++) {
            int partIndex = random.nextInt(
                    (int) (this.partRepository.count() - 1)) + 1;
            parts.add(partEntities.get(partIndex));

        }

        return parts;
    }
}
