package com.viktorsuetnov.carbook.controller;

import com.viktorsuetnov.carbook.dto.CarDTO;
import com.viktorsuetnov.carbook.entity.Car;
import com.viktorsuetnov.carbook.facade.CarFacade;
import com.viktorsuetnov.carbook.service.CarService;
import com.viktorsuetnov.carbook.validations.ResponseErrorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/car")
@CrossOrigin
public class CarController {

    @Autowired
    private CarService carService;
    @Autowired
    private CarFacade carFacade;
    @Autowired
    private ResponseErrorValidator responseErrorValidator;

    @PostMapping("/create")
    public ResponseEntity<Object> createCar(@Valid @RequestBody CarDTO carDTO,
                                            BindingResult bindingResult,
                                            Principal principal) {
        ResponseEntity<Object> errors = responseErrorValidator.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;
        Car car = carService.createCar(carDTO, principal);
        CarDTO createdCar = carFacade.carToCarDTO(car);
        return new ResponseEntity<>(createdCar, HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<CarDTO>> getAllCars() {
        List<CarDTO> carDTOList = carService.getAllCars()
                .stream()
                .map(carFacade::carToCarDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(carDTOList, HttpStatus.OK);
    }

    @GetMapping("/user/cars")
    public ResponseEntity<List<CarDTO>> getAllCarsForUser(Principal principal) {
        List<CarDTO> carDTOList = carService.getAllCarsByUser(principal)
                .stream()
                .map(carFacade::carToCarDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(carDTOList, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Object> updateCar(@Valid @RequestBody CarDTO carDTO,
                                            BindingResult bindingResult,
                                            Principal principal) {
        ResponseEntity<Object> errors = responseErrorValidator.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;
        Car car = carService.updateCar(carDTO, principal);
        CarDTO carUpdated = carFacade.carToCarDTO(car);
        return new ResponseEntity<>(carUpdated, HttpStatus.OK);
    }

}
