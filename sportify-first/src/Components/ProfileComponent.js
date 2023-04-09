import React, { useEffect, useState } from "react";
import { NavLink, Outlet } from "react-router-dom";
import "./ProfileComponent.css";
import profileimage from "../assets/profile-image.jpg";

function ProfileComponent() {
  const nav_styles = {
    textDecoration: "none",
    width: "100%",
    color: "black",
    padding: "10px 20px",
    display: "block",
    fontSize: "20px",
    fontWeight: "400",
  };
  // const [data, setData] = useState({
  //   name: "Akshat Kumpriya",
  //   dob: "23",
  //   city: "Raipur",
  //   gender: "Others",
  //   Email: "akki@gmail.com",
  // });
  // useEffect(() => {
  //   fetch("")
  //     .then((response) => response.json())
  //     .then((data) => setData(data));
  // }, []);

  //const user = JSON.parse(localStorage.getItem("savedUser"));
  const [user, setUser] = useState(
    JSON.parse(localStorage.getItem("savedUser"))
  );
  const [chosenCity, setChosenCity] = useState(
    JSON.parse(localStorage.getItem("chosenCity"))
  );

  return (
    <>
      <div className="profile-page-comp d-flex flex-column ">
        <div className="profile-heading m-auto d-flex gap-4 ">
          <img
            src={profileimage}
            alt="Profile Pic"
            className="profile-image"
            width="130px"
          ></img>
          <div className="profile-info d-flex flex-column justify-content-center ">
            <h2>{user.emailId}</h2>
            <h5>{chosenCity.name}</h5>
          </div>
        </div>
      </div>
      <hr></hr>
      <div className=" container row m-auto ">
        <div className=" col-sm-3 p-0  ">
          <ul
            class="nav nav-pills nav-stacked "
            style={{ position: "sticky", top: "0" }}
          >
            <li>
              <NavLink
                className="display-6"
                activeClassName="active"
                to="/profile/events"
                style={nav_styles}
              >
                My Events
              </NavLink>
            </li>
            <li>
              <NavLink
                className="display-6"
                activeClassName="active"
                to="edit-profile"
                style={nav_styles}
              >
                Full Profile
              </NavLink>
            </li>
          </ul>
        </div>
        <div className="col-sm-9  ">
          <Outlet></Outlet>
        </div>
      </div>
    </>
  );
}

export default ProfileComponent;
