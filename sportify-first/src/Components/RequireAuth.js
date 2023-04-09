import { useLocation } from "react-router";
import useAuth from "../hooks/useAuth";
import { Outlet, Navigate } from "react-router-dom";

const RequireAuth = ({ allowedRoles }) => {
  const auth = localStorage.getItem("savedRole");
  const user = localStorage.getItem("savedUser");
  const location = useLocation();

  return <Outlet />;
  // return auth && allowedRoles === auth ? (
  //   <Outlet />
  // ) : user ? (
  //   <Navigate to="/unauthorized" state={{ from: location }} replace />
  // ) : (
  //   <Navigate to="/login" state={{ from: location }} replace />
  // );
};

export default RequireAuth;
