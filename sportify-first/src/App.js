import "./App.css";
import { Routes, Route } from "react-router-dom";
import FooterComponent from "./Components/FooterComponent";
import HeaderComponent from "./Components/HeaderComponent";
import LoginComponent from "./Components/LoginComponent";
import Layout from "./Components/Layout";
import Signup from "./Components/SignupComponent";
import LandingPageComp from "./Components/LandingPageComp";
import TestProfileComponent from "./Components/TestProfileComponent";
import TestEventsComponent from "./Components/TestEventsComponent";
import AdminPageComponent from "./Components/AdminPageComponent";
import MissingComponent from "./Components/MissingComponent";
import RequireAuth from "./Components/RequireAuth";
import Unauthorized from "./Components/Unauthorized";
import AboutUsComponent from "./Components/AboutUsComponent";
import CreatedEvents from "./Components/profileChildren/UserEvents/CreatedEvents";
import JoinedEvents from "./Components/profileChildren/UserEvents/JoinedEvents";
import PastEvents from "./Components/profileChildren/UserEvents/PastEvents";
import ProfileComponent from "./Components/ProfileComponent";
import EditProfile from "./Components/profileChildren/EditProfile";
import MyEvents from "./Components/profileChildren/MyEvents";
import BrowseEvents from "./Components/BrowseEvents";
import CreateEventComponent from "./Components/CreateEventComponent";
import FullEventDetailPage from "./Components/profileChildren/FullEventDetailPage";

const ROLES = {
  User: 100,
  Admin: 101,
};

function App() {
  return (
    <>
      <div className="App">
        <HeaderComponent />
        <Routes>
          <Route path="/" element={<Layout />}>
            {/* Public Routes */}
            <Route path="" element={<LandingPageComp />}></Route>
            <Route path="login" element={<LoginComponent />}></Route>
            <Route path="signup" element={<Signup />}></Route>
            <Route path="unauthorized" element={<Unauthorized />}></Route>
            <Route path="aboutus" element={<AboutUsComponent />}></Route>
            <Route path="browse" element={<BrowseEvents />}></Route>
            <Route
              path="createEvent"
              element={<CreateEventComponent />}
            ></Route>
            {/* Private Routes */}
            <Route element={<RequireAuth allowedRoles={[ROLES.User]} />}>
              <Route path="events" element={<TestEventsComponent />}></Route>
            </Route>
            <Route element={<RequireAuth allowedRoles={[ROLES.Admin]} />}>
              <Route path="admin" element={<AdminPageComponent />}></Route>
            </Route>
            <Route element={<RequireAuth allowedRoles={[ROLES.User]} />}>
              <Route path="profile" element={<ProfileComponent />}>
                <Route path="events" element={<MyEvents />}>
                  <Route path="past" element={<PastEvents />} />
                  <Route path="joined" element={<JoinedEvents />} />
                  <Route path="created" element={<CreatedEvents />} />
                </Route>
                <Route path="edit-profile" element={<EditProfile />}></Route>
              </Route>
              <Route
                path="eventDetail"
                element={<FullEventDetailPage />}
              ></Route>
            </Route>

            {/* Catch All */}
            <Route path="*" element={<MissingComponent />}></Route>
          </Route>
        </Routes>
        <FooterComponent />
      </div>
    </>
  );
}

export default App;
