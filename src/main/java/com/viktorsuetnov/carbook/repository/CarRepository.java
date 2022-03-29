package com.viktorsuetnov.carbook.repository;

import com.viktorsuetnov.carbook.entity.Car;
import com.viktorsuetnov.carbook.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> getCarsByOwner(User owner);

    Optional<Car> getCarByIdAndOwner(Long carId, User owner);
}
