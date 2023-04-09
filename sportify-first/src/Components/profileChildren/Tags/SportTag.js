import SportIcon from "../../../assets/sport_icon.png";

const SportTag = (params) => {
  return (
    <>
      <span className="badge rounded-pill bg-warning m-1 text-dark p-1">
        <img
          src={SportIcon}
          alt="sport-pic"
          height={params.big ? "40px" : "30px"}
          style={{ verticalAlign: params.big ? "-13px" : "-10px" }}
        ></img>
        <span
          className="display-6 ms-1 me-3"
          style={{ fontSize: params.big ? "20px" : "15px", fontWeight: "400" }}
        >
          Football
        </span>
      </span>
    </>
  );
};

export default SportTag;
