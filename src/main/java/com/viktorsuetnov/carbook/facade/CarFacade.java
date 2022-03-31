package com.viktorsuetnov.carbook.facade;

import com.viktorsuetnov.carbook.dto.CarDTO;
import com.viktorsuetnov.carbook.entity.Car;
import org.springframework.stereotype.Component;

@Component
public class CarFacade {

    public CarDTO carToCarDTO(Car car) {
        CarDTO carDTO = new CarDTO();
        carDTO.setId(car.getId());
        carDTO.setCarBrand(car.getCarBrand());
        carDTO.setCarModel(car.getCarModel());
        carDTO.setYearOfIssue(car.getYearOfIssue());
        carDTO.setCarEngineCapacity(car.getCarEngineCapacity());
        carDTO.setVrp(car.getVrp());
        return carDTO;
    }
}
