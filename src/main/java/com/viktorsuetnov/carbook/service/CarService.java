package com.viktorsuetnov.carbook.service;

import com.viktorsuetnov.carbook.dto.CarDTO;
import com.viktorsuetnov.carbook.entity.Car;
import com.viktorsuetnov.carbook.entity.User;
import com.viktorsuetnov.carbook.exceptions.CarNotFoundException;
import com.viktorsuetnov.carbook.repository.CarRepository;
import com.viktorsuetnov.carbook.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class CarService {

    private static final Logger LOG = LoggerFactory.getLogger(CarService.class);

    private final CarRepository carRepository;
    private final UserRepository userRepository;

    @Autowired
    public CarService(CarRepository carRepository, UserRepository userRepository) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
    }

    public List<Car> getAllCarsByUser(Principal principal) {
        User user = getUserByPrincipal(principal);
        return carRepository.getCarsByOwner(user);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car getCarByIdAndUser(Long carId, Principal principal) {
        User user = getUserByPrincipal(principal);
        return carRepository.getCarByIdAndOwner(carId, user)
                .orElseThrow(() -> new CarNotFoundException(
                        "Car with id " + carId + " and user " + user.getEmail() + " not found"));
    }

    public Car createCar(CarDTO carDTO, Principal principal) {
        User user = getUserByPrincipal(principal);
        Car car = new Car();
        car.setOwner(user);
        car.setCarBrand(carDTO.getCarBrand());
        car.setCarModel(carDTO.getCarModel());
        car.setCarGeneration(carDTO.getCarGeneration());
        car.setYearOfIssue(carDTO.getYearOfIssue());
        car.setCarTransmission(carDTO.getCarTransmission());
        car.setCarEngineType(carDTO.getCarEngineType());
        car.setCarBodyType(carDTO.getCarBodyType());
        car.setCarEngineCapacity(carDTO.getCarEngineCapacity());
        car.setCarEngineCapacity(carDTO.getCarEngineCapacity());
        car.setCarEnginePower(carDTO.getCarEnginePower());
        car.setCarOdometerType(carDTO.getCarOdometerType());
        car.setCarColor(carDTO.getCarColor());
        car.setVrp(carDTO.getVrp());

        LOG.info("Saving Car for user {}", user.getEmail());
        return carRepository.save(car);
    }

    private User getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with " + username + " not found"));
    }
}
