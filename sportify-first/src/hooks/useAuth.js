import { useContext } from "react";
import AuthContext from "../app/context/AuthProvider";

const useAuth = () => {
  return useContext(AuthContext);
};

export default useAuth;
