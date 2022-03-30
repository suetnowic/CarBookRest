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
        createOrUpdateCar(carDTO, car);

        LOG.info("Saving Car for user {}", user.getEmail());
        return carRepository.save(car);
    }

    public Car updateCar(CarDTO carDTO, Principal principal) {
        User user = getUserByPrincipal(principal);
        Car carFromDB = carRepository.getCarByIdAndOwner(carDTO.getId(), user)
                .orElseThrow(() -> new CarNotFoundException(
                "Car with id " + carDTO.getId() + " and user " + user.getEmail() + " not found"));
        createOrUpdateCar(carDTO, carFromDB);

        LOG.info("Updating Car for user {}", user.getEmail());
        return carRepository.save(carFromDB);
    }

    private void createOrUpdateCar(CarDTO carDTO, Car carFromDB) {
        carFromDB.setCarBrand(carDTO.getCarBrand());
        carFromDB.setCarModel(carDTO.getCarModel());
        carFromDB.setCarGeneration(carDTO.getCarGeneration());
        carFromDB.setYearOfIssue(carDTO.getYearOfIssue());
        carFromDB.setCarTransmission(carDTO.getCarTransmission());
        carFromDB.setCarEngineType(carDTO.getCarEngineType());
        carFromDB.setCarBodyType(carDTO.getCarBodyType());
        carFromDB.setCarEngineCapacity(carDTO.getCarEngineCapacity());
        carFromDB.setCarEngineCapacity(carDTO.getCarEngineCapacity());
        carFromDB.setCarEnginePower(carDTO.getCarEnginePower());
        carFromDB.setCarOdometerType(carDTO.getCarOdometerType());
        carFromDB.setCarColor(carDTO.getCarColor());
        carFromDB.setVrp(carDTO.getVrp());
    }

    private User getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with " + username + " not found"));
    }


}
