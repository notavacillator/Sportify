package com.sportify.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_events")
public class UserEvents {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer userEventId;

	@ManyToOne
	@JoinColumn(name = "event_id")
	public Event event;

	public Integer venueId;

	public Integer userId;

	public UserEvents(){
		super();
	}

	public UserEvents(Integer userEventId, Event event, Integer venueId, Integer userId) {
		super();
		this.userEventId = userEventId;
		this.event = event;
		this.venueId = venueId;
		this.userId = userId;
	}


	public Integer getUserEventId() {
		return userEventId;
	}

	public void setUserEventId(Integer userEventId) {
		this.userEventId = userEventId;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getVenueId() {
		return venueId;
	}

	public void setVenueId(Integer venueId) {
		this.venueId = venueId;
	}

}
