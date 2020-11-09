package Shop.Cars.Services.Service;

import Shop.Cars.Services.SeedDto.Cars.CarsRootDto;
import Shop.Cars.Services.ViewDTO.CarRootDtoView;

public interface CarService {

    void insertCars(CarsRootDto carsRootDto);
    CarRootDtoView getCarsFromBrandOrderByModel(String brandName);
}
