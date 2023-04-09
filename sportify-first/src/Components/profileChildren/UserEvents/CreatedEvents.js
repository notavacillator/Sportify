import { useEffect, useState } from "react";
import { axiosEventMS } from "../../../app/api/sportify-api";
import useAuth from "../../../hooks/useAuth";
import EventCard from "./EventCard";

const CreatedEvents = () => {

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
        `/event/creater/${user.userId}`
      );
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
export default CreatedEvents;
