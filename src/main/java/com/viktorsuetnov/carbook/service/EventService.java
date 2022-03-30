package com.viktorsuetnov.carbook.service;

import com.viktorsuetnov.carbook.dto.EventDTO;
import com.viktorsuetnov.carbook.entity.Car;
import com.viktorsuetnov.carbook.entity.Event;
import com.viktorsuetnov.carbook.entity.User;
import com.viktorsuetnov.carbook.exceptions.CarNotFoundException;
import com.viktorsuetnov.carbook.exceptions.EventNotFoundException;
import com.viktorsuetnov.carbook.repository.CarRepository;
import com.viktorsuetnov.carbook.repository.EventRepository;
import com.viktorsuetnov.carbook.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private static final Logger LOG = LoggerFactory.getLogger(EventService.class);

    private final EventRepository eventRepository;
    private final CarRepository carRepository;
    private final UserRepository userRepository;

    @Autowired
    public EventService(EventRepository eventRepository, CarRepository carRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.carRepository = carRepository;
        this.userRepository = userRepository;
    }

    public Event createEvent(Long carId, EventDTO eventDTO, Principal principal) {
        User user = getUserByPrincipal(principal);
        Car car = carRepository.getCarByIdAndOwner(carId, user)
                .orElseThrow(() -> new CarNotFoundException("Car cannot be found for username: " + user.getEmail()));
        Event event = new Event();
        event.setCar(car);
        event.setDateEvent(eventDTO.getDateEvent());
        event.setOperationTitle(eventDTO.getOperationTitle());
        event.setConsumables(eventDTO.getConsumables());
        event.setQty(eventDTO.getQty());
        event.setPrice(eventDTO.getPrice());
        event.setOdometerReading(eventDTO.getOdometerReading());
        event.setNote(eventDTO.getNote());

        LOG.info("Saving Event for Car {}", car.getId());
        return eventRepository.save(event);
    }

    public Event getEventById(Long eventId, Principal principal) {
        User user = getUserByPrincipal(principal);
        return eventRepository.findEventsByCarIdAndCarOwner(eventId, user)
                .orElseThrow(() -> new EventNotFoundException("Event cannot be found for username {}" + user.getEmail()));
    }

    public List<Event> getAllEventsForCar(Long carId, Principal principal) {
        User user = getUserByPrincipal(principal);
        Car car = carRepository.getCarByIdAndOwner(carId, user)
                .orElseThrow(() -> new CarNotFoundException("Car cannot be found"));
        return eventRepository.getEventsByCar(car);
    }

    public void deleteEvent(Long eventId, Principal principal) {
        Event event = getEventById(eventId, principal);
        eventRepository.delete(event);
    }

    private User getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with " + username + " not found"));
    }
}
