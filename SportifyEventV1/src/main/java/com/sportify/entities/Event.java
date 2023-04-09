package com.sportify.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "event")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "event_id")
	private Integer eventId;

	@Column(name = "event_name")
	private String eventName;

	@Column(name = "creator_id")
	private Integer creatorUserId;

	@Column(name = "sport_id")
	private Integer sportId;

	@Column(name = "venue_id")
	private Integer venueId;

	@Column(name = "city_id")
	private Integer cityId;

	@Column(name = "event_start_date_time", columnDefinition = "TIMESTAMP")
	private LocalDateTime eventStartDateTime;

	@Column(name = "event_end_date_time", columnDefinition = "TIMESTAMP")
	private LocalDateTime eventEndDateTime;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Column(name = "players_required")
	private Integer playersRequired;

	@Column(name = "players_joined")
	private int playersJoined;

	@Size(max = 500)
	private String requirements;

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

	public Integer getCreatorUserId() {
		return creatorUserId;
	}

	public void setCreatorUserId(Integer creatorUserId) {
		this.creatorUserId = creatorUserId;
	}

	public Integer getSportId() {
		return sportId;
	}

	public void setSportId(Integer sportId) {
		this.sportId = sportId;
	}

	public Integer getVenueId() {
		return venueId;
	}

	public void setVenueId(Integer venueId) {
		this.venueId = venueId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public LocalDateTime getEventStartDateTime() {
		return eventStartDateTime;
	}

	public void setEventStartDateTime(LocalDateTime eventStartDateTime) {
		this.eventStartDateTime = eventStartDateTime;
	}

	public LocalDateTime getEventEndDateTime() {
		return eventEndDateTime;
	}

	public void setEventEndDateTime(LocalDateTime eventEndDateTime) {
		this.eventEndDateTime = eventEndDateTime;
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

	public Integer getPlayersJoined() {
		return playersJoined;
	}

	public void setPlayersJoined(Integer playersJoined) {
		this.playersJoined = playersJoined;
	}

	public void updateEvent(Event e) {
		this.setEventStartDateTime(e.getEventStartDateTime());
		this.setEventEndDateTime(e.getEventEndDateTime());
		this.setGender(e.getGender());
		this.setVenueId(e.getVenueId());
		this.setEventName(e.getEventName());
		this.setPlayersRequired(e.getPlayersRequired());
		this.setRequirements(e.getRequirements());
		this.setSportId(e.getSportId());
		this.setCreatorUserId(e.getCreatorUserId());
		this.setPlayersJoined(e.getPlayersJoined());
	}

	@Override
	public String toString() {
		return "Event [eventId=" + eventId + ", eventName=" + eventName + ", creatorUserId=" + creatorUserId
				+ ", sportId=" + sportId + ", venueId=" + venueId + ", cityId=" + cityId + ", eventStartDateTime="
				+ eventStartDateTime + ", eventEndDateTime=" + eventEndDateTime + ", gender=" + gender
				+ ", playersRequired=" + playersRequired + ", requirements=" + requirements + "]";
	}

}