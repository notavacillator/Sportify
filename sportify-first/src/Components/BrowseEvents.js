import { axiosEventMS } from "../app/api/sportify-api";
import "./BrowseEvents.css";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import useAuth from "../hooks/useAuth";

function BrowseEvents() {
  const { setAuthHeader } = useAuth();
  setAuthHeader(localStorage.getItem("savedAccessToken"));
  // const axios = require('axios');
  const user = JSON.parse(localStorage.getItem("savedUser")).userId;
  const navigate = useNavigate();
  const [eventListData, setEventListData] = useState([]);
  const cityId = JSON.parse(localStorage.getItem("chosenCity")).id;

  useEffect(() => {
    //Try Catch this
    async function fetchData() {
      const response = await axiosEventMS.get(`/event/${cityId}`);
      setEventListData([...response.data]);
    }
    fetchData();
  }, [cityId]);

  const handleCreateEvent = () => {
    let userLS;
    if (localStorage.getItem("savedUser") !== undefined) {
      userLS = JSON.parse(localStorage.getItem("savedUser"));
    }
    userLS ? navigate("/createEvent") : navigate("/login");
  };

  const joinEventMethod = (eventId, venueId) => {
    axiosEventMS.post("/event/join", {
      event: { eventId: eventId },
      venueId: venueId,
      userId: user,
    });

    navigate("/profile/events/joined");
  };

  return (
    <div className="browse-events-section">
      <div className="section-header">
        <div className="header-first-flex d-flex justify-content-between align-items-center">
          <div className="city-name-edit d-flex gap-2 align-items-start">
            <h1>{JSON.parse(localStorage.getItem("chosenCity")).name}</h1>
            <div className="edit-city-icon">
              <i className="fas fa-pencil-alt"></i>
            </div>
          </div>
          <div className="create-event-link" onClick={handleCreateEvent}>
            <div className="create-event brutal-button d-flex gap-2">
              <h5>Create Event</h5>
              <div className="create-event-icon">
                <i className="fas fa-calendar-plus"></i>
              </div>
            </div>
          </div>
        </div>
        <div className="header-second-flex">
          <div className="filter-set">
            <h4 className="filter-heading">Filters</h4>

            <div className="date-filter">
              <label htmlFor="event-date">Date</label>
              <input
                type="date"
                className="form-control-sm"
                name="event-date"
                id="event-date"
              />
            </div>

            <div className="time-filter">
              <label htmlFor="time">Time </label>
              <select className="form-select-sm" name="time" id="time">
                <option value="all">All times</option>
                <option value="morning">Morning</option>
                <option value="afternoon">Afternoon</option>
                <option value="evening">Evening</option>
                <option value="night">Night</option>
              </select>
            </div>
            <div className="sport-filter">
              <label htmlFor="sport">Sport </label>
              <select className="form-select-sm" name="sport" id="sport">
                <option value="all">All sports</option>
                <option value="football">Football</option>
                <option value="cricket">Cricket</option>
                <option value="basketball">Basketball</option>
                <option value="tennis">Tennis</option>
              </select>
            </div>
          </div>
        </div>
      </div>

      <div className="event-list-section">
        {eventListData.map((event) => (
          <div className="event-card brutal-card d-flex flex-column gap-2">
            {/* <div className="event-ground-image">
              <img
                src="https://via.placeholder.com/300x300"
                alt="event-ground"
              />
            </div> */}
            <div className="event-card-desc">
              <h3 className="event-title event-sport">
                Sports : {event.sport.sportName}
              </h3>
              <h4 className="event-title event-venue">
                Venue : {event.venue.groundName}
              </h4>
              <label htmlFor="datetime-local">Event Date & Time : </label>
              <input
                className="form-control-sm"
                type="datetime-local"
                name="datetime-local"
                id="datetime-local"
                value={event.eventStartDateTime}
                readOnly
              />
              <p className="players-joined">
                Players joined : {event.playersJoined}
              </p>
              <p className="event-requirement">{event.requirements}</p>
              <p className="event-creator">
                Created by {event.creatorUser.emailId}
              </p>
            </div>
            <button
              onClick={() => joinEventMethod(event.eventId, event.venueId)}
              className="join-event event-brutal-button ms-auto"
            >
              Join Event
            </button>
          </div>
        ))}
      </div>
    </div>
  );
}

export default BrowseEvents;
