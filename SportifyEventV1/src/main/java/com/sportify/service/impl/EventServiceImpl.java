package com.sportify.service.impl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sportify.dto.EventDTO;
import com.sportify.dto.SportDTO;
import com.sportify.dto.UserDTO;
import com.sportify.dto.VenueDTO;
import com.sportify.entities.Event;
import com.sportify.entities.UserEvents;
import com.sportify.exceptions.SportifyAppExceptions;
import com.sportify.repository.EventRepository;
import com.sportify.repository.UserEventRepository;
import com.sportify.service.EventService;

@Service
@Transactional
public class EventServiceImpl implements EventService {

	@Autowired
	public EventRepository eventRepository;

	@Autowired
	public UserEventRepository userEventRepository;

	@Autowired
	public RestTemplate restTemplate;

	@Override
	public List<EventDTO> browseEvents(Integer cityId,String jwtToken) throws SportifyAppExceptions {
		List<Event> listOfEvent = eventRepository.findByCityId(cityId);

		if (listOfEvent.isEmpty())
			throw new SportifyAppExceptions("No events found");

		List<EventDTO> eventDtoList = new ArrayList<>();
		System.out.println("city id in service impl " + cityId);

		listOfEvent.forEach(e -> {

			if (e.getEventStartDateTime().isAfter(LocalDateTime.now())) {
				EventDTO eventDto = new EventDTO();
				eventDto = EventDTO.ConvertToDTO(e);

				// getting creator user
				HttpHeaders headers = new HttpHeaders();
				headers.set("Authorization", jwtToken);
				HttpEntity<String> requestEntity = new HttpEntity<>(headers);

				ResponseEntity<UserDTO> userDtoResponse = restTemplate.exchange("http://localhost:9091/user/{user_id}",
						HttpMethod.GET, requestEntity, UserDTO.class, e.getCreatorUserId());
				UserDTO userDto = userDtoResponse.getBody();
				eventDto.setCreatorUser(userDto);

				// getting sport
				ResponseEntity<SportDTO> sportDtoResponse = restTemplate.exchange(
						"http://localhost:9090/sport/{sport_id}", HttpMethod.GET, requestEntity, SportDTO.class,
						e.getSportId());
				SportDTO sportDto = sportDtoResponse.getBody();
				eventDto.setSport(sportDto);

				// getting venue
				ResponseEntity<VenueDTO> venueDtoResponse = restTemplate.exchange(
						"http://localhost:9090/venue/{venue_id}", HttpMethod.GET, requestEntity, VenueDTO.class,
						e.getVenueId());
				VenueDTO venueDto = venueDtoResponse.getBody();
				eventDto.setVenue(venueDto);

				eventDtoList.add(eventDto);
			}
		});
		// System.out.println("events "+eventDtoList);
		return eventDtoList;
	}

	@Override
	public EventDTO createEvent(EventDTO eventDto) throws SportifyAppExceptions {
		List<Event> userCreatedEvents = eventRepository.findByCreatorUserId(eventDto.getCreatorUser().getUserId());

		LocalDateTime newEventStartTime = eventDto.getEventStartDateTime();
		LocalDateTime newEventEndTime = newEventStartTime.plus(eventDto.getEventDurationMinutes(), ChronoUnit.MINUTES);

		if (!userCreatedEvents.isEmpty()) {
			for (Event eve : userCreatedEvents) {

				LocalDateTime startTime = eve.getEventStartDateTime();
				LocalDateTime endTime = eve.getEventEndDateTime();

				if ((newEventStartTime.isAfter(startTime) && newEventStartTime.isBefore(endTime))
						|| (newEventEndTime.isBefore(endTime) && newEventEndTime.isAfter(startTime))
						|| newEventStartTime.isEqual(startTime) || newEventStartTime.isEqual(endTime))
					throw new SportifyAppExceptions("You have a conflicting event in the same time");
			}
		}

		List<UserEvents> userJoindedEvents = userEventRepository.findByUserId(eventDto.getCreatorUser().getUserId());

		if (!userJoindedEvents.isEmpty()) {
			for (UserEvents eve : userJoindedEvents) {

				LocalDateTime startTime = eve.getEvent().getEventStartDateTime();
				LocalDateTime endTime = eve.getEvent().getEventEndDateTime();

				if ((newEventStartTime.isAfter(startTime) && newEventStartTime.isBefore(endTime))
						|| (newEventEndTime.isBefore(endTime) && newEventEndTime.isAfter(startTime))
						|| newEventStartTime.isEqual(startTime) || newEventStartTime.isEqual(endTime))
					throw new SportifyAppExceptions("You have a conflicting event in the same time");
			}
		}

		Event event = eventRepository.save(EventDTO.ConvertFromDTO(eventDto));
		EventDTO newEvent = EventDTO.ConvertToDTO(event);

		return newEvent;

	}

	// 06-03-2023 todo : refrain user from joining an event at the same time
	@Override
	public UserEvents joinEvent(UserEvents userEvent, String jwtToken) throws SportifyAppExceptions {
		Event event = eventRepository.findById(userEvent.getEvent().getEventId()).get();

		if (event.getPlayersRequired() <= event.getPlayersJoined()) {
			throw new SportifyAppExceptions("This Event is already full");
		}

		List<Event> userCreatedEvents = eventRepository.findByCreatorUserId(userEvent.getUserId());

		LocalDateTime newEventStartTime = event.getEventStartDateTime();
		LocalDateTime newEventEndTime = event.getEventEndDateTime();

		if (!userCreatedEvents.isEmpty()) {
			for (Event eve : userCreatedEvents) {

				LocalDateTime startTime = eve.getEventStartDateTime();
				LocalDateTime endTime = eve.getEventEndDateTime();

				if ((newEventStartTime.isAfter(startTime) && newEventStartTime.isBefore(endTime))
						|| (newEventEndTime.isBefore(endTime) && newEventEndTime.isAfter(startTime))
						|| newEventStartTime.isEqual(startTime) || newEventStartTime.isEqual(endTime))
					throw new SportifyAppExceptions("You have a conflicting event in the same time");
			}
		}

		List<UserEvents> userJoindedEvents = userEventRepository.findByUserId(userEvent.getUserId());

		if (!userJoindedEvents.isEmpty()) {
			for (UserEvents eve : userJoindedEvents) {

				LocalDateTime startTime = eve.getEvent().getEventStartDateTime();
				LocalDateTime endTime = eve.getEvent().getEventEndDateTime();

				if ((newEventStartTime.isAfter(startTime) && newEventStartTime.isBefore(endTime))
						|| (newEventEndTime.isBefore(endTime) && newEventEndTime.isAfter(startTime))
						|| newEventStartTime.isEqual(startTime) || newEventStartTime.isEqual(endTime))
					throw new SportifyAppExceptions("You have a conflicting event in the same time");
			}
		}
		event.setPlayersJoined(event.getPlayersJoined() + 1);
		eventRepository.flush();
		userEvent.setEvent(event);
		UserEvents eventEvents = userEventRepository.save(userEvent);
		return eventEvents;
	}

	@Override
	public Event deleteEvent(Event event) {
		Optional<Event> eventToBeDeleted = eventRepository.findById(event.getEventId());
		Event deleteEvent = eventToBeDeleted.get();
		System.out.println("xxxxx"+deleteEvent);
		userEventRepository.deleteByEvent(event);
		userEventRepository.flush();
		eventRepository.deleteById(deleteEvent.getEventId());
		eventRepository.flush();
		return deleteEvent;
	}

	@Override
	public Event editEvent(EventDTO eventDto) {
		Optional<Event> updateEvent = eventRepository.findById(eventDto.getEventId());
		Event updatedEvent = updateEvent.get();
		updatedEvent.updateEvent(EventDTO.ConvertFromDTO(eventDto));
		eventRepository.flush();
		return updatedEvent;
	}

	@Override
	public UserEvents leaveJoinedEvent(UserEvents userEvent) throws SportifyAppExceptions {
		UserEvents deletedUserEvent = userEventRepository.findByUserIdAndEvent(userEvent.getUserId(),
				userEvent.getEvent());
		userEventRepository.deleteById(deletedUserEvent.getUserEventId());
		userEventRepository.flush();
		Event event = eventRepository.findById(userEvent.getEvent().getEventId()).get();
		if (event.getPlayersJoined() <= 0)
			throw new SportifyAppExceptions("Invalid Operation");
		event.setPlayersJoined(event.getPlayersJoined() - 1);
		return deletedUserEvent;
	}

	@Override
	public List<EventDTO> getUpcomingEventsOfUser(Integer userId, String jwtToken) throws SportifyAppExceptions {
		List<UserEvents> fetchedUserEvents = userEventRepository.findByUserId(userId);

		if (fetchedUserEvents.isEmpty())
			throw new SportifyAppExceptions("No Upcoming Event");

		List<EventDTO> eventDtoList = new ArrayList<>();

		fetchedUserEvents.forEach(ue -> {
			Event event = ue.getEvent();

			// showing only upcoming joined events
			if (event.getEventStartDateTime().isAfter(LocalDateTime.now())) {
				EventDTO eventDto = new EventDTO();
				eventDto = EventDTO.ConvertToDTO(event);

				// getting creator user
				HttpHeaders headers = new HttpHeaders();
				headers.set("Authorization", jwtToken);
				HttpEntity<String> requestEntity = new HttpEntity<>(headers);

				ResponseEntity<UserDTO> userDtoResponse = restTemplate.exchange("http://localhost:9091/user/{user_id}",
						HttpMethod.GET, requestEntity, UserDTO.class, event.getCreatorUserId());
				UserDTO userDto = userDtoResponse.getBody();
				eventDto.setCreatorUser(userDto);

				// getting sport
				ResponseEntity<SportDTO> sportDtoResponse = restTemplate.exchange(
						"http://localhost:9090/sport/{sport_id}", HttpMethod.GET, requestEntity, SportDTO.class,
						event.getSportId());
				SportDTO sportDto = sportDtoResponse.getBody();
				eventDto.setSport(sportDto);

				// getting venue
				ResponseEntity<VenueDTO> venueDtoResponse = restTemplate.exchange(
						"http://localhost:9090/venue/{venue_id}", HttpMethod.GET, requestEntity, VenueDTO.class,
						event.getVenueId());
				VenueDTO venueDto = venueDtoResponse.getBody();
				eventDto.setVenue(venueDto);

				eventDtoList.add(eventDto);
			}
		});
		return eventDtoList;
	}

	@Override
	public List<EventDTO> getPastEventsOfUser(Integer userId, String jwtToken) throws SportifyAppExceptions {
		List<UserEvents> fetchedUserEvents = userEventRepository.findByUserId(userId);

		if (fetchedUserEvents.isEmpty())
			throw new SportifyAppExceptions("No Event joined");

		List<EventDTO> eventDtoList = new ArrayList<>();

		fetchedUserEvents.forEach(ue -> {
			Event event = ue.getEvent();

			// showing past joined events
			if (event.getEventEndDateTime().isBefore(LocalDateTime.now())) {

				EventDTO eventDto = new EventDTO();
				eventDto = EventDTO.ConvertToDTO(event);

				// getting creator user
				HttpHeaders headers = new HttpHeaders();
				headers.set("Authorization", jwtToken);
				HttpEntity<String> requestEntity = new HttpEntity<>(headers);

				ResponseEntity<UserDTO> userDtoResponse = restTemplate.exchange("http://localhost:9091/user/{user_id}",
						HttpMethod.GET, requestEntity, UserDTO.class, event.getCreatorUserId());
				UserDTO userDto = userDtoResponse.getBody();
				eventDto.setCreatorUser(userDto);

				// getting sport
				ResponseEntity<SportDTO> sportDtoResponse = restTemplate.exchange(
						"http://localhost:9090/sport/{sport_id}", HttpMethod.GET, requestEntity, SportDTO.class,
						event.getSportId());
				SportDTO sportDto = sportDtoResponse.getBody();
				eventDto.setSport(sportDto);

				// getting venue
				ResponseEntity<VenueDTO> venueDtoResponse = restTemplate.exchange(
						"http://localhost:9090/venue/{venue_id}", HttpMethod.GET, requestEntity, VenueDTO.class,
						event.getVenueId());
				VenueDTO venueDto = venueDtoResponse.getBody();
				eventDto.setVenue(venueDto);

				eventDtoList.add(eventDto);
			}
		});
		return eventDtoList;
	}

	// event table. similar to above for eventdto
	// 06-03-2023 - Show in ascending order in frontend
	@Override
	public List<EventDTO> getEventsCreatedByUser(Integer userId, String jwtToken) throws SportifyAppExceptions {
		List<Event> fetchedCreatedEvents = eventRepository.findByCreatorUserId(userId);

		if (fetchedCreatedEvents.isEmpty())
			throw new SportifyAppExceptions("No Event Created");

		List<EventDTO> eventDtoList = new ArrayList<>();

		fetchedCreatedEvents.forEach(event -> {
			System.out.println("event Id " + event.getEventId());
			EventDTO eventDto = EventDTO.ConvertToDTO(event);

			// getting creator user
			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", jwtToken);
			HttpEntity<String> requestEntity = new HttpEntity<>(headers);

			ResponseEntity<UserDTO> userDtoResponse = restTemplate.exchange("http://localhost:9091/user/{user_id}",
					HttpMethod.GET, requestEntity, UserDTO.class, event.getCreatorUserId());
			UserDTO userDto = userDtoResponse.getBody();
			eventDto.setCreatorUser(userDto);

			// getting sport
			ResponseEntity<SportDTO> sportDtoResponse = restTemplate.exchange("http://localhost:9090/sport/{sport_id}",
					HttpMethod.GET, requestEntity, SportDTO.class, event.getSportId());
			SportDTO sportDto = sportDtoResponse.getBody();
			eventDto.setSport(sportDto);

			// getting venue
			ResponseEntity<VenueDTO> venueDtoResponse = restTemplate.exchange("http://localhost:9090/venue/{venue_id}",
					HttpMethod.GET, requestEntity, VenueDTO.class, event.getVenueId());
			VenueDTO venueDto = venueDtoResponse.getBody();
			eventDto.setVenue(venueDto);

			eventDtoList.add(eventDto);
		});

		return eventDtoList;
	}

	// input - userId
	// return userDTO
	@Override
	public List<UserDTO> getAllParticipantsOfAnEvent(Event event, String jwtToken) throws SportifyAppExceptions {
		List<UserEvents> listOfParticipants = userEventRepository.findByEvent(event);

		if (listOfParticipants.isEmpty())
			throw new SportifyAppExceptions("No Participants");

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", jwtToken);
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);

		List<UserDTO> userDtoList = new ArrayList<UserDTO>();
		listOfParticipants.forEach(ue -> {

			ResponseEntity<UserDTO> userDtoResponse = restTemplate.exchange("http://localhost:9091/user/{user_id}",
					HttpMethod.GET, requestEntity, UserDTO.class, ue.getUserId());
			UserDTO userDto = userDtoResponse.getBody();
			userDtoList.add(userDto);
		});
		return userDtoList;
	}

}