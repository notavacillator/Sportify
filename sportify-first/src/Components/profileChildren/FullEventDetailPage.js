import "./FullEventDetailPage.css";
import FullVenueDetailPage from "./FullVenueDetailPage";
import SportTag from "./Tags/SportTag";
import UserTag from "./Tags/UserTag";

const FullEventDetailPage = (params) => {
  const value = { fontSize: "20px", fontWeight: "400" };
  return (
    <>
      <div>
        <div
          className="container bg-dark p-3"
          style={{ marginBottom: "150px" }}
        >
          <div className="row">
            <div className="col-md-3 p-2 bg-dark">
              <div className="event-icon-cricket"></div>
            </div>
            <div className="col-md-9 p-2 bg-light ">
              <div
                className="heading"
                style={{ display: "flex", justifyContent: "space-between" }}
              >
                <p className=" display-6 p-0 m-0 ">Event Name</p>

                <div>
                  <button
                    className="btn btn-danger  mt-1 px-5"
                    style={{
                      fontSize: "20px",
                      fontWeight: "500",
                    }}
                  >
                    Leave
                  </button>
                </div>
              </div>
              <hr />
              <div style={{ fontSize: "20px" }}>
                <p className="my-2">
                  <span className="text-secondary px-2">Date :</span>
                  <span className="display-6" style={value}>
                    {new Date().toDateString()}
                  </span>
                </p>
                <p className="my-2">
                  <span className="text-secondary px-2">Time :</span>
                  <span className="display-6" style={value}>
                    {new Date().toLocaleTimeString()}
                  </span>
                </p>
                <p className="my-2">
                  <span className="text-secondary px-2">Duration :</span>
                  <span className="display-6" style={value}>
                    {120} (minutes)
                  </span>
                </p>
              </div>
            </div>
          </div>
          <div className="row">
            <div className="col-md-3 p-2 bg-dark text-light p-3 m-sm-0 ">
              <span className=" p-0 m-0">
                Sport &nbsp;&nbsp; : <SportTag />
              </span>
              <br></br>
              <span className=" p-0 m-0 ">
                Creator : <UserTag showFull />
              </span>
              <hr></hr>
              <p className="My Team display-6 text-center" style={{ ...value }}>
                My Team
              </p>
              <hr></hr>
              <div
                className="participants"
                style={{ overflowY: "scroll", maxHeight: "380px" }}
              >
                <UserTag showFull={true}></UserTag>
                <UserTag showFull={true}></UserTag>
                <UserTag showFull={true}></UserTag>
                <UserTag showFull={true}></UserTag>
                <UserTag showFull={true}></UserTag>
                <UserTag showFull={true}></UserTag>
                <UserTag showFull={true}></UserTag>
                <UserTag showFull={true}></UserTag>
                <UserTag showFull={true}></UserTag>
                <UserTag showFull={true}></UserTag>
                <UserTag showFull={true}></UserTag>
                <UserTag showFull={true}></UserTag>
                <UserTag showFull={true}></UserTag>
              </div>
            </div>
            <div
              className="col-md-9 py-3 px-0"
              style={{
                position: "relative",
              }}
            >
              <div className="bg-warning p-3" title="Venue of the Event">
                <FullVenueDetailPage />
              </div>
              <div className="bg-warning px-3 pb-3">
                <div className="card">
                  <div className="card-body">
                    <h5 className="card-title">Event Requirements</h5>
                    <p>
                      This Sports equipments with racket monotone icon in
                      powerpoint pptx png and editable eps format is a 100
                      percent editable icon. The downloaded file will have this
                      icon in EPS, PNG and Powerpoint pptx format and is perfect
                      for your next project. It has a simple yet stylish design.
                    </p>
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

export default FullEventDetailPage;
