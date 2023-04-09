import profileimage from "../../../assets/profile-image.jpg";

const UserCard = (props) => {
  return (
    <>
      <span
        className={props.showFull ? "badge rounded-pill text-dark" : "badge"}
      >
        <img
          className="profile-image"
          src={profileimage}
          alt="profic-pic"
          width="40px"
          style={{ verticalAlign: "-13px" }}
        ></img>
        {props.showFull && (
          <span className="display-6 mx-2" style={{ fontSize: "20px" }}>
            {props.creator && props.creator.emailId}
          </span>
        )}
      </span>
    </>
  );
};

export default UserCard;
