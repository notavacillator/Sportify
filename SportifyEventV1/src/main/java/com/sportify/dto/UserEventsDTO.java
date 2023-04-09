package com.sportify.dto;

import com.sportify.entities.Event;
import com.sportify.entities.UserEvents;

public class UserEventsDTO {

	public Integer id;
	public EventDTO event;
	public Integer venueId;
	public Integer userId;

	public UserEventsDTO() {
		super();
	}

	public UserEventsDTO(Integer id, EventDTO event) {
		super();
		this.id = id;
		this.event = event;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EventDTO getEvent() {
		return event;
	}

	public void setEvent(EventDTO event) {
		this.event = event;
	}

	public Integer getVenueId() {
		return venueId;
	}

	public void setVenueId(Integer venueId) {
		this.venueId = venueId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public static UserEventsDTO convertToDto(UserEvents ue) {
		EventDTO event = EventDTO.ConvertToDTO(ue.getEvent());
		UserEventsDTO userEvents = new UserEventsDTO(ue.getUserEventId(), event);
		return userEvents;
	}

	public static UserEvents convertFromDto(UserEventsDTO udto) {
		Event event = EventDTO.ConvertFromDTO(udto.getEvent());
		UserEvents userEvents = new UserEvents(udto.getId(), event, udto.getVenueId(), udto.getUserId());
		return userEvents;
	}

	@Override
	public String toString() {
		return "UserEventsDTO [id=" + id + ", event=" + event + ", venueId=" + venueId + ", userId=" + userId + "]";
	}
	
	

}
