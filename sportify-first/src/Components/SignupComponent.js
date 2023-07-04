import React from "react";
import { axiosUserMS } from "../app/api/sportify-api";
import {
  faCheck,
  faTimes,
  faInfoCircle,
} from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { useState, useRef, useEffect } from "react";
import { Link } from "react-router-dom";

const sectionStyle = {
  backgroundImage:
    "url(https://mdbcdn.b-cdn.net/img/Photos/new-templates/search-box/img4.webp)",
};

const cardStyle = {
  borderRadius: "15px",
};

const REGISTER_URL = "user/signup";
const EMAIL_REGEX = /^([a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6})$/;
const PSWD_REGEX =
  /(?=(.*[0-9]))(?=.*[!@#$%^&*()\\[\]{}\-_+=~`|:;"'<>,./?])(?=.*[a-z])(?=(.*[A-Z]))(?=(.*)).{8,}/;

function Signup() {
  const nameRef = useRef();
  const errRef = useRef();

  const [name, setName] = useState("");

  const [email, setEmail] = useState("");
  const [validEmail, setValidEmail] = useState(false);
  const [emailFocus, setEmailFocus] = useState(false);

  const [pswd, setPswd] = useState("");
  const [validPswd, setValidPswd] = useState(false);
  const [pswdFocus, setPswdFocus] = useState(false);

  const [matchPswd, setMatchPswd] = useState("");
  const [validMatch, setValidMatch] = useState(false);
  const [matchFocus, setMatchFocus] = useState(false);

  const [errMsg, setErrMsg] = useState("");
  const [success, setSuccess] = useState(false);

  useEffect(() => {
    nameRef.current.focus();
  }, []);

  useEffect(() => {
    setValidEmail(EMAIL_REGEX.test(email));
  }, [email]);

  useEffect(() => {
    setValidPswd(PSWD_REGEX.test(pswd));
    setValidMatch(matchPswd === pswd);
  }, [pswd, matchPswd]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    const v1 = EMAIL_REGEX.test(email);
    const v2 = PSWD_REGEX.test(pswd);
    if (!v1 || !v2) {
      setErrMsg("Invalid Entry");
      return;
    }

    try {
      const response = await axiosUserMS.post(
        REGISTER_URL,
        JSON.stringify({ emailId: email, password: pswd }),
        {
          headers: { "Content-Type": "application/json" },
          // withCredentials: true, //Getting cors error if uncommented
        }
      );
      console.log(response?.data);
      console.log(response?.accessToken);
      console.log(JSON.stringify(response));
      setSuccess(true);
      //clear state and controlled inputs
      //need value attrib on inputs for this
      setEmail("");
      setPswd("");
      setMatchPswd("");
    } catch (err) {
      if (!err?.response) {
        setErrMsg("No Server Response");
      } else if (err.response?.status === 500) {
        setErrMsg("Email Taken");
      } else {
        setErrMsg("Registration Failed");
      }
      errRef.current.focus();
    }
  };

  return (
    <>
      {success ? (
        <section>
          <h1>Success!</h1>
          <p>
            <Link to="/login" className="fw-bold text-body">
              Login here
            </Link>
          </p>
        </section>
      ) : (
        <section className="vh-150 bg-image p-5" style={sectionStyle}>
          <div className="mask d-flex align-items-center h-100 gradient-custom-3">
            <div className="container h-100">
              <div className="row d-flex justify-content-center align-items-center h-100">
                <div className="col-12 col-md-9 col-lg-7 col-xl-6">
                  <div className="bg-light mt-4 " style={cardStyle}>
                    <div className="card-body p-5 ">
                      <p
                        ref={errRef}
                        className={errMsg ? "errmsg" : "offscreen"}
                        aria-live="assertive"
                      >
                        {errMsg}
                      </p>
                      <h2 className="text-uppercase text-center mb-5">
                        Create an account
                      </h2>

                      <form onSubmit={handleSubmit}>
                        <div className="form-outline form-group mb-4">
                          <label
                            className="form-label"
                            htmlFor="form3Example1cg"
                          >
                            Your Name
                          </label>
                          <input
                            type="text"
                            id="form3Example1cg"
                            className="form-control form-control-lg"
                            ref={nameRef}
                            onChange={(e) => setName(e.target.value)}
                            value={name}
                            required
                          />
                        </div>

                        <div className="form-outline mb-4">
                          <label
                            className="form-label"
                            htmlFor="form3Example3cg"
                          >
                            Your Email
                            <FontAwesomeIcon
                              icon={faCheck}
                              className={validEmail ? "valid" : "hide"}
                            />
                            <FontAwesomeIcon
                              icon={faTimes}
                              className={
                                validEmail || !email ? "hide" : "invalid"
                              }
                            />
                          </label>
                          <input
                            type="email"
                            id="form3Example3cg"
                            className="form-control form-control-lg"
                            autoComplete="off"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            onFocus={() => setEmailFocus(true)}
                            onBlur={() => setEmailFocus(false)}
                          />
                          <p
                            className={
                              emailFocus && email && !validEmail
                                ? "instructions"
                                : "offscreen"
                            }
                          >
                            <FontAwesomeIcon
                              icon={faInfoCircle}
                            ></FontAwesomeIcon>
                            Can contain lowercase and uppercase letters.
                            <br></br>
                            Only numbers, underscores, periods and hyphens
                            allowed
                          </p>
                        </div>

                        <div className="form-outline mb-4">
                          <label
                            className="form-label"
                            htmlFor="form3Example4cg"
                          >
                            Password
                            <FontAwesomeIcon
                              icon={faCheck}
                              className={validPswd ? "valid" : "hide"}
                            />
                            <FontAwesomeIcon
                              icon={faTimes}
                              className={
                                validPswd || !pswd ? "hide" : "invalid"
                              }
                            />
                          </label>
                          <input
                            type="password"
                            id="form3Example4cg"
                            className="form-control form-control-lg"
                            autoComplete="off"
                            value={pswd}
                            onChange={(e) => setPswd(e.target.value)}
                            onFocus={(e) => setPswdFocus(true)}
                            onBlur={(e) => setPswdFocus(false)}
                          />
                          <p
                            className={
                              pswdFocus && !validPswd
                                ? "instructions"
                                : "offscreen"
                            }
                          >
                            <FontAwesomeIcon
                              icon={faInfoCircle}
                            ></FontAwesomeIcon>
                            8 to 24 characters.
                            <br />
                            Must include uppercase and lowercase letters, a
                            number and a special character.
                            <br />
                            {
                              "Allowed special characters: ! @ # $ % ^ & * ( )  [ ] { } - _ + = ~ | : ; \" ' < > , . / ?"
                            }
                          </p>
                        </div>

                        <div className="form-outline mb-4">
                          <label
                            className="form-label"
                            htmlFor="form3Example4cdg"
                          >
                            Repeat your password
                            <FontAwesomeIcon
                              icon={faCheck}
                              className={
                                validMatch && matchPswd ? "valid" : "hide"
                              }
                            />
                            <FontAwesomeIcon
                              icon={faTimes}
                              className={
                                validMatch || !matchPswd ? "hide" : "invalid"
                              }
                            />
                          </label>
                          <input
                            type="password"
                            id="form3Example4cdg"
                            className="form-control form-control-lg"
                            required
                            value={matchPswd}
                            onChange={(e) => setMatchPswd(e.target.value)}
                            onFocus={() => setMatchFocus(true)}
                            onBlur={() => setMatchFocus(false)}
                          />
                          <p
                            className={
                              matchFocus && !validMatch
                                ? "instructions"
                                : "offscreen"
                            }
                          >
                            <FontAwesomeIcon icon={faInfoCircle} />
                            Must match the password above.
                          </p>
                        </div>

                        {/* <div className="form-check d-flex justify-content-center mb-5">
                        <input
                          className="form-check-input me-2"
                          type="checkbox"
                          value=""
                          id="form2Example3cg"
                        />
                        <label
                          className="form-check-label"
                          htmlFor="form2Example3g"
                        >
                          I agree all statements in{" "}
                          <a href="#!" className="text-body">
                            <u>Terms of service</u>
                          </a>
                        </label>
                      </div> */}

                        <div className="d-flex justify-content-center">
                          <button
                            type="submit"
                            className="brutal-button btn btn-success btn-block btn-lg gradient-custom-4 text-body"
                          >
                            Register
                          </button>
                        </div>

                        <p className="text-center text-muted mt-5 mb-0">
                          Already have an account?{" "}
                          <Link to="/login" className="fw-bold text-body">
                            Login here
                          </Link>
                        </p>
                      </form>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </section>
      )}
    </>
  );
}

export default Signup;
