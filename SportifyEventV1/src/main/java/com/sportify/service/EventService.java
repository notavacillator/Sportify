package com.sportify.service;

import java.util.List;

import com.sportify.dto.EventDTO;
import com.sportify.dto.UserDTO;
import com.sportify.entities.Event;
import com.sportify.entities.UserEvents;
import com.sportify.exceptions.SportifyAppExceptions;

public interface EventService {
	public List<EventDTO> browseEvents(Integer cityId,String jwtToken) throws SportifyAppExceptions;

	public EventDTO createEvent(EventDTO eventDTO) throws SportifyAppExceptions;

	public UserEvents joinEvent(UserEvents userEvent, String jwtToken) throws SportifyAppExceptions;

	public Event deleteEvent(Event e);

	public Event editEvent(EventDTO eventDto);

	public UserEvents leaveJoinedEvent(UserEvents userEvent) throws SportifyAppExceptions;

	public List<EventDTO> getUpcomingEventsOfUser(Integer userId, String jwtToken) throws SportifyAppExceptions;

	public List<EventDTO> getPastEventsOfUser(Integer userId, String jwtToken) throws SportifyAppExceptions;

	public List<EventDTO> getEventsCreatedByUser(Integer userId, String jwtToken) throws SportifyAppExceptions;

	public List<UserDTO> getAllParticipantsOfAnEvent(Event event, String jwtToken) throws SportifyAppExceptions;

}
