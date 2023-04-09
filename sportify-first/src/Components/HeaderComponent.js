import React from "react";
import "./HeaderComponent.css";
import { Link } from "react-router-dom";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useSelector, useDispatch } from "react-redux";
import { userActions } from "../Features/Users/userSlice";

function HeaderComponent() {
  const navigate = useNavigate();
  const savedUser = JSON.parse(localStorage.getItem("savedUser"));
  // const [isDropdownActive, setIsDropdownActive] = useState(false);
  const dispatch = useDispatch();
  const [isDropdownActive, setIsDropdownActive] = useState(false);

  const toggleDropdown = () => {
    setIsDropdownActive(!isDropdownActive);
  };

  const handleLogOut = () => {
    dispatch(userActions.changeToken(""));
    dispatch(userActions.changeRole(""));
    dispatch(userActions.saveUser(""));
    localStorage.removeItem("savedRole");
    localStorage.removeItem("savedUser");
    localStorage.removeItem("savedAccessToken");
    navigate("/");
  };

  return (
    <div className="fixed-nav">
      <nav className="navbar navbar-expand-lg navbar-light bg-light justify-content-between">
        <h2 className="mx-2">
          <a href="/#" className="navbar-brand">
            SPORTIFY
          </a>
        </h2>

        {savedUser ? (
          (console.log(savedUser),
          (
            <div className="dropdown" onClick={toggleDropdown}>
              <div className="button-profile-header profile-icon">
              <i className="fas fa-bars"></i>

              </div>
              <div
                className={`dropdown-content${
                  isDropdownActive ? "active" : ""
                }`}
              >
                <Link to="profile">My Profile</Link>
                <Link to="">Joined Events</Link>
                <div onClick={handleLogOut}>
                  <Link>Logout</Link>
                </div>
              </div>
            </div>
          ))
        ) : (
          <div className="" id="navbarSupportedContent">
            <ul className="navbar-nav ms-auto flex-row">
              <li className="nav-item">
                <Link to="/login" className="button-profile-header">
                  L O G I N
                </Link>
              </li>
              <li className="nav-item mx-3">
                <Link to="/signup" className="button-profile-header">
                  S I G N U P
                </Link>
              </li>
            </ul>
          </div>
        )}
      </nav>
    </div>
  );
}

export default HeaderComponent;

// below renders  only if the user has logged in

// function UserNavbar() {
//   const [isDropdownActive, setIsDropdownActive] = useState(false);

//   function toggleDropdown() {
//     setIsDropdownActive((prevState) => !prevState);
//   }

//   return (
//     <div className="fixed-nav">
//       <nav className="navbar navbar-expand-lg navbar-light bg-light justify-content-between">
//         <h2 className="mx-2">
//           <a href="/#" className="navbar-brand">
//             SPORTIFY
//           </a>
//         </h2>

//         <div className="dropdown" onClick={toggleDropdown}>
//           <div className="profile-icon">
//             <i className="fas fa-user"></i>
//           </div>
//           <div className={`dropdown-content${isDropdownActive ? ' active' : ''}`}>
//             <Link to="#">My Profile</Link>
//             <Link to="#">Joined Events</Link>
//             <Link to="#">Logout</Link>
//           </div>
//         </div>
//       </nav>
//     </div>
//   );
// }
