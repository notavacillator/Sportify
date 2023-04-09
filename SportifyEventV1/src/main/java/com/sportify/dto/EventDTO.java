package com.sportify.dto;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import com.sportify.entities.Event;
import com.sportify.entities.Gender;

public class EventDTO {
	private Integer eventId;

	private String eventName;

	private UserDTO creatorUser;

	private SportDTO sport;

	private VenueDTO venue;

	private LocalDateTime eventStartDateTime;

	private Long eventDurationMinutes;

	private Gender gender;

	private Integer playersRequired;

	private String requirements;

	private Integer cityId;

	private int playersJoined;

	public EventDTO() {
		super();
	}

	public EventDTO(Integer eventId, String eventName, UserDTO creatorUser, SportDTO sport, VenueDTO venue,
			LocalDateTime eventStartDateTime, Long eventDurationMinutes, Gender gender, Integer playersRequired,
			String requirements, Integer cityId, Integer playersJoined) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.creatorUser = creatorUser;
		this.sport = sport;
		this.venue = venue;
		this.eventStartDateTime = eventStartDateTime;
		this.eventDurationMinutes = eventDurationMinutes;
		this.gender = gender;
		this.playersRequired = playersRequired;
		this.requirements = requirements;
		this.cityId = cityId;
		this.playersJoined = playersJoined;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Integer getPlayersJoined() {
		return playersJoined;
	}

	public void setPlayersJoined(Integer playersJoined) {
		this.playersJoined = playersJoined;
	}

	public UserDTO getCreatorUser() {
		return creatorUser;
	}

	public void setCreatorUser(UserDTO creatorUser) {
		this.creatorUser = creatorUser;
	}

	public SportDTO getSport() {
		return sport;
	}

	public void setSport(SportDTO sport) {
		this.sport = sport;
	}

	public VenueDTO getVenue() {
		return venue;
	}

	public void setVenue(VenueDTO venue) {
		this.venue = venue;
	}

	public LocalDateTime getEventStartDateTime() {
		return eventStartDateTime;
	}

	public void setEventStartDateTime(LocalDateTime eventStartDateTime) {
		this.eventStartDateTime = eventStartDateTime;
	}

	public Long getEventDurationMinutes() {
		return eventDurationMinutes;
	}

	public void setEventDurationMinutes(Long eventDurationMinutes) {
		this.eventDurationMinutes = eventDurationMinutes;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Integer getPlayersRequired() {
		return playersRequired;
	}

	public void setPlayersRequired(Integer playersRequired) {
		this.playersRequired = playersRequired;
	}

	public String getRequirements() {
		return requirements;
	}

	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}

	public static Event ConvertFromDTO(EventDTO e) {
		Event event = new Event();
		event.setEventStartDateTime(e.getEventStartDateTime());
		event.setEventEndDateTime(e.getEventStartDateTime().plus(e.getEventDurationMinutes(), ChronoUnit.MINUTES));
		event.setGender(e.getGender());
		event.setEventName(e.getEventName());
		event.setPlayersRequired(e.getPlayersRequired());
		event.setRequirements(e.getRequirements());
		event.setCityId(e.getCityId());
		event.setPlayersJoined(e.getPlayersJoined());
		event.setSportId(e.getSport().getSportId());
		event.setCreatorUserId(e.getCreatorUser().getUserId());
		event.setVenueId(e.getVenue().getVenueId());
		return event;
	}

	public static EventDTO ConvertToDTO(Event event) {
		EventDTO e = new EventDTO();
		e.setEventId(event.getEventId());
		e.setEventStartDateTime(event.getEventStartDateTime());
		e.setEventDurationMinutes(Duration.between(event.getEventEndDateTime(), e.getEventStartDateTime()).toMinutes());
		e.setGender(event.getGender());
		e.setEventName(event.getEventName());
		e.setPlayersRequired(event.getPlayersRequired());
		e.setRequirements(event.getRequirements());
		e.setCityId(e.getCityId());
		e.setPlayersJoined(event.getPlayersJoined());
		return e;
	}

	@Override
	public String toString() {
		return "EventDTO [eventId=" + eventId + ", eventName=" + eventName + ", creatorUser=" + creatorUser + ", sport="
				+ sport + ", venue=" + venue + ", eventStartDateTime=" + eventStartDateTime + ", eventDurationMinutes="
				+ eventDurationMinutes + ", gender=" + gender + ", playersRequired=" + playersRequired
				+ ", requirements=" + requirements + ", cityId=" + cityId + ", playersJoined=" + playersJoined + "]";
	}

}
