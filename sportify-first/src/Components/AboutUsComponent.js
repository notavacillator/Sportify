import React from "react";
import "./AboutUsComponent.css";
import pratik from "../assets/team/cool_pratik.jpg";
import aman from "../assets/team/aman.jpg";
import akshat from "../assets/team/akshat.jpg";
import abhishek from "../assets/team/abhishek.jpg";
import yashvi from "../assets/team/yashvi_again.jpg";

function AboutUsComponent() {
  return (
    <div className="about-us-comp">
      <section className="page-section">
        <div className="container-fluid text-center">
          <h1>About Us</h1>
        </div>
        <div className="about-us-profiles d-flex flex-wrap gap-4">
          <div className="brutal-profile-container d-flex flex-column align-items-center">
            <div className="brutal-profile-image">
              <img className="img-responsive" src={pratik} alt="profile" />
              {/* I:\finesse\sportify-reopening-local\sportify-grand-reopening\sportify-first\src\assets\team\aman.jpg */}
              {/* I:\finesse\sportify-reopening-local\sportify-grand-reopening\sportify-first\src\components\AboutUsComponent.js */}
            </div>
            <div className="brutal-profile-desc justify-content-center p-3">
              <h5 className="profile-title">Pratik Parhad</h5>
              <p className="profile-job">Developer</p>
            </div>
          </div>
          <div className="brutal-profile-container d-flex flex-column align-items-center">
            <div className="brutal-profile-image">
              <img className="img-responsive" src={aman} alt="profile" />
            </div>
            <div className="brutal-profile-desc justify-content-center p-3">
              <h5 className="profile-title">Aman Singh Bhadauria</h5>
              <p className="profile-job">Developer</p>
            </div>
          </div>
          <div className="brutal-profile-container d-flex flex-column align-items-center">
            <div className="brutal-profile-image">
              <img className="img-responsive" src={yashvi} alt="profile" />
            </div>
            <div className="brutal-profile-desc justify-content-center p-3">
              <h5 className="profile-title">Yashvi Malu</h5>
              <p className="profile-job">Developer</p>
            </div>
          </div>
          <div className="brutal-profile-container d-flex flex-column align-items-center">
            <div className="brutal-profile-image">
              <img className="img-responsive" src={abhishek} alt="profile" />
            </div>
            <div className="brutal-profile-desc justify-content-center p-3">
              <h5 className="profile-title">Abhishek Yadav</h5>
              <p className="profile-job">Developer</p>
            </div>
          </div>
          <div className="brutal-profile-container d-flex flex-column align-items-center">
            <div className="brutal-profile-image">
              <img className="img-responsive" src={akshat} alt="profile" />
            </div>
            <div className="brutal-profile-desc justify-content-center p-3">
              <h5 className="profile-title">Akshat Khamparia</h5>
              <p className="profile-job">Developer</p>
            </div>
          </div>
        </div>
      </section>
    </div>
  );
}

export default AboutUsComponent;
