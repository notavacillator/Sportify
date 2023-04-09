package com.sportify.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sportify.dto.EventDTO;
import com.sportify.dto.UserDTO;
import com.sportify.entities.Event;
import com.sportify.entities.UserEvents;
import com.sportify.exceptions.SportifyAppExceptions;
import com.sportify.service.EventService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/event")
@CrossOrigin(origins = "http://localhost:3000")
public class EventController {

	@Autowired
	public EventService eventService;

	// city id will be received from frontEnd and all events based on that
	// city will be displayed
	@Operation(summary = "Browse Events")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Upcoming Events Retrieved", content = {
			@Content(mediaType = "application/json") }) })
	@GetMapping("/{cityId}")
	public ResponseEntity<List<EventDTO>> browseEvents(@RequestHeader("Authorization") String jwtToken,@PathVariable Integer cityId) throws SportifyAppExceptions {
		return new ResponseEntity<List<EventDTO>>(eventService.browseEvents(cityId,jwtToken), HttpStatus.OK);
		// Apporach - get cityId from cityName
		// send it to user_events to get eventId
		// send eventId to events table to get list of events.
	}

	// form data will be passed and event will be created
	// and db will be updated.
	// return new created Event object
	@Operation(summary = "Create Event")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Event Creation Successful", content = {
			@Content(mediaType = "application/json") }) })
	@PostMapping("/create")
	public ResponseEntity<EventDTO> createEvent(@RequestBody EventDTO eventDTO) throws SportifyAppExceptions {
		return new ResponseEntity<EventDTO>(eventService.createEvent(eventDTO), HttpStatus.CREATED);
	}

	// user data will be sent and updated in user and event table
	@Operation(summary = "Join Event")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Event Joined.", content = {
			@Content(mediaType = "application/json") }) })
	@PostMapping("/join")
	public ResponseEntity<UserEvents> joinEvent(@RequestHeader("Authorization") String authorizationHeader,
			@RequestBody UserEvents userEvent) throws SportifyAppExceptions {
		String jwtToken = authorizationHeader;
		return new ResponseEntity<UserEvents>(eventService.joinEvent(userEvent, jwtToken), HttpStatus.CREATED);
	}

	@Operation(summary = "Delete Event")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Event Deletion successful.", content = {
			@Content(mediaType = "application/json") }) })
	@PostMapping("/deleteCreated")
	public ResponseEntity<Event> deleteEvent(@RequestBody Event e) {
		System.out.println("event to be deleted " + e.getEventId());
		return new ResponseEntity<Event>(eventService.deleteEvent(e), HttpStatus.OK);
	}

	@Operation(summary = "Edit Event")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Event details updated.", content = {
			@Content(mediaType = "application/json") }) })
	// will receive event object //will return updated event object
	@PutMapping("/edit")
	public ResponseEntity<Event> editEvent(@RequestBody EventDTO eventDto) {
		return new ResponseEntity<Event>(eventService.editEvent(eventDto), HttpStatus.OK);
	}

	@Operation(summary = "Leave Joined Event")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "You are not joined in the event anymore.", content = {
					@Content(mediaType = "application/json") }) })
	@PostMapping("/leave")
	public ResponseEntity<UserEvents> leaveJoinedEvent(@RequestBody UserEvents userEvents)
			throws SportifyAppExceptions {
		// input - event id //return-confirmation string
		return new ResponseEntity<UserEvents>(eventService.leaveJoinedEvent(userEvents), HttpStatus.OK);
	}

	// frontend for DTO
	// for joined events
	@Operation(summary = "Get Upcoming Joined events of a user")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Upcoming Joined events of the user retrieved", content = {
					@Content(mediaType = "application/json") }) })
	@GetMapping("/upcoming/user/{user_id}")
	public ResponseEntity<List<EventDTO>> getEventsOfAUser(@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable Integer user_id) throws SportifyAppExceptions {
		String jwtToken = authorizationHeader;
		return new ResponseEntity<List<EventDTO>>(eventService.getUpcomingEventsOfUser(user_id, jwtToken),
				HttpStatus.OK);
	}

	// past events
	// input - date //output -event list
	@Operation(summary = "Get Past events of a user")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Past Joined events of the user retrieved", content = {
					@Content(mediaType = "application/json") }) })
	@GetMapping("/past/user/{userId}")
	public ResponseEntity<List<EventDTO>> getPastEventsOfUser(
			@RequestHeader("Authorization") String authorizationHeader, @PathVariable Integer userId)
			throws SportifyAppExceptions {
		String jwtToken = authorizationHeader;
		return new ResponseEntity<List<EventDTO>>(eventService.getPastEventsOfUser(userId, jwtToken), HttpStatus.OK);
	}

	// input - event id
	// return - list of users
	@Operation(summary = "Get all participants of an event")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Participants of event fetched.", content = {
					@Content(mediaType = "application/json") }) })
	@PostMapping("/getParticipants")
	public ResponseEntity<List<UserDTO>> getAllParticipantsOfAnEvent(
			@RequestHeader("Authorization") String authorizationHeader, @RequestBody Event event)
			throws SportifyAppExceptions {
		String jwtToken = authorizationHeader;
		return new ResponseEntity<List<UserDTO>>(eventService.getAllParticipantsOfAnEvent(event, jwtToken),
				HttpStatus.OK);
	}

	@Operation(summary = "Get events created by a user")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Events created by the user fetched.", content = {
					@Content(mediaType = "application/json") }) })
	@GetMapping("/creater/{userId}")
	public ResponseEntity<List<EventDTO>> getEventsCreatedByUser(
			@RequestHeader("Authorization") String authorizationHeader, @PathVariable Integer userId)
			throws SportifyAppExceptions {
		String jwtToken = authorizationHeader;
		return new ResponseEntity<List<EventDTO>>(eventService.getEventsCreatedByUser(userId, jwtToken), HttpStatus.OK);
	};

}