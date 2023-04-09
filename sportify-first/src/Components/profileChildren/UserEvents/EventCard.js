import "./EventCard.css";
import UserTag from "../Tags/UserTag";
import VenueTag from "../Tags/VenueTag";
import { useNavigate } from "react-router";

const EventCard = (props) => {
  const navigate = useNavigate();
  return (
    <>
      <div className=" mb-5 border border-3">
        <div className="row g-0">
          <div className="col-md-4">
            <div className="event-icon-cricket p-2"></div>
          </div>
          <div className="col-md-8">
            <div className="card-body">
              <h4
                className="card-title border-bottom  display-6 p-2 ps-3"
                style={{
                  fontSize: "25px",
                }}
              >
                <b>{props.eve.eventName}</b>
              </h4>

              <div className="m-3">
                <div className="col-md-12 ">
                  <li className="list-group-item border-bottom border-1">
                    <span className="text-secondary">
                      Date & Time :
                      <span
                        className="display-6 text-danger px-2 "
                        style={{
                          fontSize: "20px",
                          fontWeight: "400",
                          lineHeight: "50px",
                        }}
                      >
                        <input
                          className="w-100 border-0 display-6"
                          type="datetime-local"
                          readOnly
                          style={{ fontSize: "25px", display: "inline-block" }}
                          value={props.eve.eventStartDateTime}
                        ></input>
                      </span>
                    </span>
                  </li>

                  <li className="list-group-item ">
                    <span className="text-secondary">
                      Players joined :
                      <span
                        className="display-6 text-danger px-2 "
                        style={{
                          fontSize: "20px",
                          fontWeight: "400",
                          lineHeight: "50px",
                        }}
                      >
                        {props.eve.playersJoined}/{props.eve.playersRequired}
                      </span>
                    </span>
                  </li>
                  <hr />
                  {/* <li className="list-group-item">
                    <span className="text-secondary">Participants </span>
                    <span className="container participant-list ">
                      <div className="row">
                        <UserTag />
                      </div>
                      <div className="row">
                        <UserTag />
                      </div>
                      <div className="row">
                        <UserTag />
                      </div>
                      <div className="row">
                        <UserTag />
                      </div>
                      <div className="row">
                        <UserTag />
                      </div>
                      <div className="row">
                        <UserTag />
                      </div>
                      <div className="row">
                        <UserTag />
                      </div>
                    </span>
                  </li> */}

                  <div className="col-md-12">
                    <li className="list-group-item " title="Creator">
                      <span className="text-secondary "> Creator : &nbsp;</span>
                      <UserTag
                        showFull={true}
                        creator={props.eve.creatorUser}
                      />
                    </li>
                    <hr />
                    <li className="list-group-item">
                      <span className="text-secondary">
                        Venue &nbsp; :<VenueTag venue={props.eve.venue} />
                      </span>
                    </li>
                  </div>
                  <div className="row mt-2 m-auto">
                    <hr />
                    <button className="btn col-6  btn-danger  rounded-0 ">
                      Leave
                    </button>
                    <button
                      className="btn  col-6 btn-secondary  rounded-0 "
                      onClick={() => navigate("/eventDetail")}
                    >
                      View
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default EventCard;
