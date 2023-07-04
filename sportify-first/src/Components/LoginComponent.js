import React from "react";
import styles from '../Components/LoginComponent.module.css'; 
import { axiosUserMS } from "../app/api/sportify-api";
import { useState, useEffect, useRef } from "react";
import useAuth from "../hooks/useAuth";
import { Link, useLocation, useNavigate } from "react-router-dom";
import { useSelector, useDispatch } from "react-redux";
import { userActions } from "../Features/Users/userSlice";
// import { loggedInUser } from "../Features/Users/userSlice";

function LoginComponent() {
  const {setAuthHeader} = useAuth();

  const dispatch = useDispatch();
  const city = useSelector((state) => state.landingR.city);
  const navigate = useNavigate();
  const location = useLocation();
  const from = location?.state?.from?.pathname || "/";

  const user = useSelector((store) => store.user.user);
  const role = useSelector((store) => store.user.role);
  const accessToken = useSelector((store) => store.user.accessToken);

  // const { setAuth, auth } = useAuth();

  const userRef = useRef();
  const errRef = useRef();

  const [userMail, setUserMail] = useState("");
  const [userPswd, setUserPswd] = useState("");
  const [errMsg, setErrMsg] = useState("");
  // const [success, setSuccess] = useState(false);

  useEffect(() => {
    userRef.current.focus();
  }, []);

  useEffect(() => {
    setErrMsg("");
  }, [userMail, userPswd]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axiosUserMS.post(
        "/user/authenticate",
        JSON.stringify({
          emailId: userMail,
          password: userPswd,
        }),
        {
          headers: { "content-type": "application/json" },
          // withCredentials: true,
        }
      );
      dispatch(userActions.changeToken(response.data.jwt));
      dispatch(userActions.saveUser(response.data.userDetail));
      dispatch(userActions.changeRole(response.data.userDetail.isAdmin));

      localStorage.setItem("savedRole", response.data.userDetail.isAdmin);
      localStorage.setItem(
        "savedUser",
        JSON.stringify(response.data.userDetail)
      );
      localStorage.setItem("savedAccessToken", response.data.jwt);

      !city ? navigate("/") : navigate("/browse");
      setAuthHeader(response.data.jwt);
    } catch (err) {
      console.log(err);
      // ISKO KARNA HAI
      if (!err?.response) setErrMsg("no server response");
      else if (err.response?.status === 400)
        setErrMsg("missing uname or password");
      else if (err.response?.status === 401) setErrMsg("unauthorized");
      else setErrMsg("login failed");
      errRef.current.focus();
    }
  };

  return (
    <>
      {/* {success ? (
        <h1>logged in!</h1>
      ) : ( */}
      <p ref={errRef} className={errMsg ? "errmsg" : "offscreen"}>
        {errMsg}
      </p>
      <div className={`${styles['form-container']} m-auto p-5 row ${styles['brutal-card']}`}>
        <h2 className="">Sign In </h2>
        <form onSubmit={handleSubmit}>
          <div className="form-outline mb-4 form-group">
            <label className="form-label" htmlFor="form2Example1">
              Email address:
            </label>
            <input
              type="email"
              id="form2Example1"
              className="form-control"
              ref={userRef}
              onChange={(e) => setUserMail(e.target.value)}
            />
          </div>

          <div className="form-outline mb-4 form-group">
            <label className="form-label" htmlFor="form2Example2">
              Password:
            </label>
            <input
              type="password"
              id="form2Example2"
              className="form-control"
              onChange={(e) => setUserPswd(e.target.value)}
            />
          </div>

          <div className="row mb-4">
            {/* <div className="col d-flex justify-content-center">
            <div className="form-check">
              <input
                className="form-check-input"
                type="checkbox"
                value=""
                id="form2Example31"
              />
              <label className="form-check-label" htmlFor="form2Example31">
                {" "}
                Remember me{" "}
              </label>
            </div>
          </div> */}

            <div className="col">
              {/* <a href="#!">Forgot password?</a> */}

              {/* HEHEHEHEHEHEHEHE */}
              <a href="https://media.tenor.com/strg_-AfSVwAAAAC/spider-man-i-missed-the-part-where-thats-my-problem.gif">
                Forgot password?
              </a>
            </div>
          </div>

          <button
            type="submit"
            className="styles.brutal-button btn btn-primary btn-block mb-4 d-flex justify-content-center"
          >
            Sign in
          </button>

          <div className="text-center">
            <p>
              Not a member?
              <Link to="/signup">Sign Up</Link>
            </p>
          </div>
        </form>
      </div>
      {/* )} */}
    </>
  );
}

export default LoginComponent;
