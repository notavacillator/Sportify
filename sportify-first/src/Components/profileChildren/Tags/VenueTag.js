import CirclePin from "../../../assets/CirclePin.png";

const VenueTag = (props) => {
  return (
    <>
      <span className="badge rounded-pill bg-light text-dark p-2">
        <img
          src={CirclePin}
          alt="profic-pic"
          height="35px"
          style={{ verticalAlign: "-10px" }}
        ></img>
        <span className="display-6 mx-2" style={{ fontSize: "20px" }}>
          {props.venue.groundName}
        </span>
      </span>
    </>
  );
};

export default VenueTag;
