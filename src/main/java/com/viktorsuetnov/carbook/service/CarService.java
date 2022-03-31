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

    public Car createCar(Car carIn, Principal principal) {
        User user = getUserByPrincipal(principal);
        Car car = new Car();
        car.setOwner(user);
        createAndUpdateCar(carIn, car);

        LOG.info("Saving Car for user {}", user.getEmail());
        return carRepository.save(car);
    }

    public Car updateCar(Car carIn, Principal principal) {
        User user = getUserByPrincipal(principal);
        Car carFromDB = carRepository.getCarByIdAndOwner(carIn.getId(), user)
                .orElseThrow(() -> new CarNotFoundException(
                "Car with id " + carIn.getId() + " and user " + user.getEmail() + " not found"));
        createAndUpdateCar(carIn, carFromDB);

        LOG.info("Updating Car for user {}", user.getEmail());
        return carRepository.save(carFromDB);
    }

    private void createAndUpdateCar(Car carIn, Car carFromDB) {
        carFromDB.setCarBrand(carIn.getCarBrand());
        carFromDB.setCarModel(carIn.getCarModel());
        carFromDB.setCarGeneration(carIn.getCarGeneration());
        carFromDB.setYearOfIssue(carIn.getYearOfIssue());
        carFromDB.setCarTransmission(carIn.getCarTransmission());
        carFromDB.setCarEngineType(carIn.getCarEngineType());
        carFromDB.setCarBodyType(carIn.getCarBodyType());
        carFromDB.setCarEngineCapacity(carIn.getCarEngineCapacity());
        carFromDB.setCarEngineCapacity(carIn.getCarEngineCapacity());
        carFromDB.setCarEnginePower(carIn.getCarEnginePower());
        carFromDB.setCarOdometerType(carIn.getCarOdometerType());
        carFromDB.setCarColor(carIn.getCarColor());
        carFromDB.setVrp(carIn.getVrp());
    }

    private User getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with " + username + " not found"));
    }


}
