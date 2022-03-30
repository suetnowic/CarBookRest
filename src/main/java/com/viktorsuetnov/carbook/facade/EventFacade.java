package com.viktorsuetnov.carbook.facade;

import com.viktorsuetnov.carbook.dto.EventDTO;
import com.viktorsuetnov.carbook.entity.Event;
import org.springframework.stereotype.Component;

@Component
public class EventFacade {

    public EventDTO eventToEventDTO(Event event) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setCar(event.getCar());
        eventDTO.setConsumables(event.getConsumables());
        eventDTO.setDateEvent(event.getDateEvent());
        eventDTO.setNote(event.getNote());
        eventDTO.setOdometerReading(event.getOdometerReading());
        eventDTO.setOperationTitle(event.getOperationTitle());
        eventDTO.setPrice(event.getPrice());
        event.setQty(event.getQty());
        event.setId(event.getId());
        return eventDTO;
    }
}
