import { React, useState, useEffect } from "react";
import "./LandingPageComp.css";
import { useDispatch, useSelector } from "react-redux";
import { chooseCity, fetchAllCities } from "../Features/City/landingSlice";
import axios from "../app/api/sportify-api";
import { useNavigate } from "react-router";

function LandingPageComp() {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(fetchAllCities());
  }, []);

  const [city, setCity] = useState({});
  const getcities = useSelector((state) => state.landingR.cities);
  // console.log("aaaaaaaaaaaaaaaaa " + getcities);
  const cityList = getcities.map((c) => (
    <option key={c.cityId} value={c.cityId}>
      {c.name}
    </option>
  ));

  const handleSubmit = () => {
    dispatch(chooseCity(city));
    navigate("browse");
  };

  return (
    <>
        <div className="landing-page-banner ">
        <main className="page-main-section col-lg-4 col-md-8  col-10 m-auto">
          <div className="city-select ">
            <h2
              className="display-6 mb-4"
              style={{ fontWeight: "400", fontSize: "30px" }}
            >
PICK YOUR CITY            </h2>
            <div className="">
              <div className="">
                <select
                  className="form-select form-control h-100  me-2"
                  onChange={(e) => {
                    setCity({
                      id: e.target.value,
                      name: e.target.options[e.target.selectedIndex].innerText,
                    });
                  }}
                >
                  <option value=""></option>
                  {cityList}
                </select>
                </div>
              <div>
                <button
                  className="button-profile-land "
                  onClick={handleSubmit}
                  // disabled={!cid}
                >
                  START
                </button>
              </div>
            </div>
          </div>
        </main>
      </div>

    </>
  );
}

export default LandingPageComp;
