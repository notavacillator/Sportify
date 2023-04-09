import { axiosEventMS } from "../../../app/api/sportify-api";
import EventCard from "./EventCard";
import { useState } from "react";
import { useEffect } from "react";
import useAuth from "../../../hooks/useAuth";

const JoinedEvents = () => {
  const { setAuthHeader } = useAuth();
  setAuthHeader(localStorage.getItem("savedAccessToken"));
  const [eventListData, setEventListData] = useState([]);
  const [user, setUser] = useState(
    JSON.parse(localStorage.getItem("savedUser"))
  );
  useEffect(() => {
    //Try Catch this
    async function fetchData() {
      const response = await axiosEventMS.get(
        `/event/upcoming/user/${user.userId}`
      );
      console.log(response);
      setEventListData([...response.data]);
    }
    fetchData();
  }, []);

  return (
    <>
      {eventListData.map((eve) => (
        <EventCard eve={eve} />
      ))}
    </>
  );
};
export default JoinedEvents;
