import React from "react";
import { FaFacebook, FaTwitter, FaInstagram } from "react-icons/fa";
import { Link } from "react-router-dom";
import "./FooterComponent.css";
const Footer = () => {
  return (
    <>
      <footer className="bg-dark text-light py-3 footer-container">
        <div className="row first-footer-group">
          <div className="col-md-6 first">
            <p>© 2023 Sportify. All Rights Reserved.</p>
          </div>
          <div className="col-md-6 d-flex justify-content-end second">
            <Link to="/aboutus" className="text-light me-3">
              About Us
            </Link>
          </div>
        </div>

        <div className="footer-icons d-flex justify-content-end">
          <FaFacebook className="me-3" />
          <FaTwitter className="me-3" />
          <FaInstagram />
        </div>
      </footer>
    </>
  );
};

export default Footer;
