import { Link, NavLink, Outlet } from "react-router-dom";
const MyEvents = () => {
  const nav_style = {
    textDecoration: "none",
    textAlign: "center",
    color: "Black",
    display: "inline-block",
    padding: "5px 20px",
    fontSize: "20px",
    fontWeight: "400",
    width: "100%",
    margin: "0px",
  };
  return (
    <>
      <nav className=" row p-0 m-1 mb-3 border ">
        <div className="col-sm-4">
          <NavLink
            className="col-sm-4  border-end"
            activeClassName="active"
            to="joined"
            exact
            style={nav_style}
          >
            Joined Events
          </NavLink>
        </div>
        <div className="col-sm-4  border-end ">
          <NavLink
            className="col-sm-4 px-0"
            activeClassName="active"
            to="created"
            style={nav_style}
          >
            <span>Created Events</span>
          </NavLink>
        </div>
        <div className="col-sm-4">
          <NavLink
            className="col-lg-4 "
            activeClassName="active"
            to="past"
            style={nav_style}
          >
            <span>Past Events</span>
          </NavLink>
        </div>
      </nav>
      <div>
        <Outlet></Outlet>
      </div>
    </>
  );
};

export default MyEvents;
