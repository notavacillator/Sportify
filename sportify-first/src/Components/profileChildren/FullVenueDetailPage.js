import SportTag from "./Tags/SportTag";

const FullVenueDetailPage = (params) => {
  const value = { fontSize: "18px", fontWeight: "400" };
  return (
    <>
      <div className="card mb-3">
        <div className="row no-gutters">
          <div className="col-md-8">
            <div className="card-body">
              <h5 className="card-title">Maharaja Football Grounds</h5>
              <p className="card-text">
                <ul className="list-group list-group-flush">
                  <li className="list-group-item">
                    <span className="text-secondary">
                      Address :
                      <span className="display-6 text-dark px-2 " style={value}>
                        Balaji Nagar, Dambhar
                      </span>
                      <br />
                    </span>
                  </li>

                  <li className="list-group-item">
                    <span className="text-secondary">
                      Landmark :
                      <span className="display-6 text-dark px-2 " style={value}>
                        Balaji Nagar, Dambhar
                      </span>
                    </span>
                  </li>
                  <li className="list-group-item">
                    <span className="text-secondary">
                      Sports Allowed :
                      <span className="display-6 text-dark px-2 " style={value}>
                        <SportTag />
                        <SportTag />
                        <SportTag />
                        <SportTag />
                        <SportTag />
                        <SportTag />
                      </span>
                    </span>
                  </li>
                  <li className="list-group-item">
                    <span className="text-secondary">
                      Timing :
                      <span className="display-6 text-dark px-2 " style={value}>
                        Balaji Nagar, Dambhar
                      </span>
                    </span>
                  </li>
                  <li className="list-group-item">
                    <span className="text-secondary">
                      Facilities :
                      <span className="display-6 text-dark px-2 " style={value}>
                        Balaji Nagar, Dambhar
                      </span>
                    </span>
                  </li>
                </ul>
              </p>
            </div>
          </div>
          <div className="col-md-4">
            <img src="" className="card-img" alt="..." />
          </div>
        </div>
      </div>
    </>
  );
};

export default FullVenueDetailPage;
