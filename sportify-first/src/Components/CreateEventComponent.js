import { axiosEventMS } from "../app/api/sportify-api";
import React, { useRef } from "react";
import "./CreateEventComponent.css";
import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import axios from "axios";
import useAuth from "../hooks/useAuth";

function CreateEventComponent() {
  const { setAuthHeader } = useAuth();
  setAuthHeader((localStorage.getItem("savedAccessToken")));
  const [sports, setSports] = useState([]);
  const [selectedSport, setSelectedSport] = useState("");
  const cityId = JSON.parse(localStorage.getItem("chosenCity")).id;
  const userId = JSON.parse(localStorage.getItem("savedUser")).userId;

  const eventNameRef = useRef();
  const venueRef = useRef();
  const startDateTimeRef = useRef();
  const durationMinsRef = useRef();
  const playersRequiredRef = useRef();
  const genderRef = useRef();
  const requirementsRef = useRef();

  useEffect(() => {
    axios
      .get("http://localhost:9090/sport")
      .then((response) => {
        console.log("sport", response);
        setSports([...response.data]);
      })
      .catch((error) => {
        console.log(error);
      });
  }, [selectedSport]);

  const handleSportChange = (event) => {
    setSelectedSport(event.target.value);

    axios
      .get(`http://localhost:9090/venue/sport/${event.target.value}`)
      .then((response) => {
        console.log(response);
        setGrounds([...response.data]);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const handleCreateEvent = async (e) => {
    e.preventDefault();
    const formData = {
      eventName: eventNameRef.current.value,
      creatorUser: {
        userId: userId,
      },
      sport: {
        sportId: selectedSport,
      },
      venue: {
        venueId: venueRef.current.value,
      },
      eventStartDateTime: startDateTimeRef.current.value,
      eventDurationMinutes: durationMinsRef.current.value,
      gender: genderRef.current.value,
      playersRequired: playersRequiredRef.current.value,
      requirements: requirementsRef.current.value,
      cityId: cityId,
    };
    console.log(formData);
    const response = await axiosEventMS.post("/event/create", formData);
  };

  const [grounds, setGrounds] = useState([]);

  return (
    <div className="create-event-container">
      <h2 className="create-event-header">Create Event</h2>
      <form onSubmit={handleCreateEvent}>
        <div className="mb-3">
          <label htmlFor="name" className="form-label">
            Event Name
          </label>
          <input
            type="text"
            className="form-control"
            required
            ref={eventNameRef}
          ></input>
        </div>

        <div className="mb-3">
          <label htmlFor="sport" className="form-label">
            Choose Sport
          </label>
          <select
            className="form-select"
            id="sport"
            value={selectedSport}
            onChange={handleSportChange}
          >
            <option value="">--Select Sport--</option>
            {sports.map((sport) => (
              <option key={sport.sportId} value={sport.sportId}>
                {sport.sportName}
              </option>
            ))}
          </select>
        </div>

        <div className="mb-3">
          <label htmlFor="venue" className="form-label">
            Choose Venue (Ground)
          </label>
          <select className="form-select" id="venue" ref={venueRef}>
            <option value="">--Select Venue--</option>
            {grounds.map((ground) => {
              let data = `Address : ${ground.address},\nCity : ${ground.city.name} \nTiming : ${ground.timings}`;
              return (
                <option
                  key={ground.venueId}
                  value={ground.venueId}
                  title={data}
                >
                  {ground.groundName}
                </option>
              );
            })}
          </select>
        </div>

        <div className="row ">
          <div className="mb-3 col-md-4">
            <label htmlFor="date-time" className="form-label">
              Choose Date and Time
            </label>
            <input
              type="datetime-local"
              className="form-control"
              id="date-time"
              required
              ref={startDateTimeRef}
            />
          </div>

          <div className="mb-3 col-md-4">
            <label htmlFor="duration" className="form-label">
              Enter Duration ("in minutes")
            </label>
            <input
              type="number"
              className="form-control"
              id="duration"
              required
              min={30}
              ref={durationMinsRef}
            />
          </div>

          <div className="mb-3 col-md-4">
            <label htmlFor="players-required" className="form-label">
              Players Required
            </label>
            <input
              type="number"
              className="form-control"
              id="players-required"
              min={1}
              ref={playersRequiredRef}
            />
          </div>
          <div className="mb-3 col-md-4">
            <label htmlFor="gender" className="form-label">
              Gender Category
            </label>
            <select className="form-select" id="gender" ref={genderRef}>
              <option value="">--Select Gender--</option>
              <option value="MAN">MAN</option>
              <option value="WOMAN">WOMAN</option>
              <option value="MIXED">MIXED</option>
            </select>
          </div>
        </div>

        <div className="mb-3">
          <label htmlFor="additional-requirements" className="form-label">
            Additional Requirements
          </label>
          <textarea
            className="form-control"
            id="additional-requirements"
            rows="3"
            ref={requirementsRef}
          ></textarea>
        </div>

        <button type="submit" className="btn btn-primary">
          Submit
        </button>
      </form>
    </div>
  );
}

export default CreateEventComponent;
