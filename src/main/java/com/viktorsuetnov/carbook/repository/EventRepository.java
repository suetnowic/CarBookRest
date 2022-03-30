package com.viktorsuetnov.carbook.repository;

import com.viktorsuetnov.carbook.entity.Car;
import com.viktorsuetnov.carbook.entity.Event;
import com.viktorsuetnov.carbook.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> getEventsByCar(Car car);

    Event getEventByIdAndCarId(Long eventId, Long carId);

    Optional<Event> findEventsByCarIdAndCarOwner(Long eventId, User user);

}
