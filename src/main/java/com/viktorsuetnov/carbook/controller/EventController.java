package com.viktorsuetnov.carbook.controller;

import com.viktorsuetnov.carbook.dto.EventDTO;
import com.viktorsuetnov.carbook.entity.Event;
import com.viktorsuetnov.carbook.facade.EventFacade;
import com.viktorsuetnov.carbook.payload.response.MessageResponse;
import com.viktorsuetnov.carbook.service.EventService;
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
@RequestMapping("/api/event")
@CrossOrigin
public class EventController {

    @Autowired
    private EventService eventService;
    @Autowired
    private EventFacade eventFacade;
    @Autowired
    private ResponseErrorValidator responseErrorValidator;

    @PostMapping("/{carId}/create")
    public ResponseEntity<Object> createEvent(@Valid @RequestBody EventDTO eventDTO,
                                              @PathVariable("carId") String carId,
                                              BindingResult bindingResult,
                                              Principal principal) {
        ResponseEntity<Object> errors = responseErrorValidator.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;
        Event event = eventService.createEvent(Long.parseLong(carId), eventDTO, principal);
        EventDTO createdEvent = eventFacade.eventToEventDTO(event);
        return new ResponseEntity<>(createdEvent, HttpStatus.OK);
    }

    @GetMapping("/{carId}/all")
    public ResponseEntity<List<EventDTO>> getAllEventsToCar(@PathVariable("carId") String carId, Principal principal) {
        List<EventDTO> events = eventService.getAllEventsForCar(Long.parseLong(carId), principal)
                .stream()
                .map(eventFacade::eventToEventDTO).collect(Collectors.toList());
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @PostMapping("/{eventId}/delete")
    public ResponseEntity<MessageResponse> deleteEvent(@PathVariable("eventId") String eventId,
                                                       Principal principal) {
        eventService.deleteEvent(Long.parseLong(eventId), principal);
        return new ResponseEntity<>(new MessageResponse("Event was deleted"), HttpStatus.OK);
    }


}
