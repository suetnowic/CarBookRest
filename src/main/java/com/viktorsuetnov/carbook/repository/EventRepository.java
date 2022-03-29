package com.viktorsuetnov.carbook.repository;

import com.viktorsuetnov.carbook.entity.Car;
import com.viktorsuetnov.carbook.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> getEventsByCar(Car car);

    Event getEventByIdAndCarId(Long eventId, Long carId);
}
