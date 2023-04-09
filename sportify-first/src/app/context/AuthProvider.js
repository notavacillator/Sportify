import { createContext, useState } from "react";
import { axiosEventMS, axiosUserMS } from "../api/sportify-api";

const AuthContext = createContext({});

export const AuthProvider = ({ children }) => {
  const [auth, setAuth] = useState();
  const [user, setUser] = useState();

  const setAuthHeader = (token) => {
    if (token) {
      axiosEventMS.defaults.headers.common["Authorization"] = `Bearer ${token}`;
      axiosUserMS.defaults.headers.common["Authorization"] = `Bearer ${token}`;
    } else {
      delete axiosEventMS.defaults.headers.common["Authorization"];
      delete axiosUserMS.defaults.headers.common["Authorization"];
    }
  };

  return (
    <AuthContext.Provider
      value={{ auth, setAuth, setAuthHeader, user, setUser }}
    >
      {children}
    </AuthContext.Provider>
  );
};
export default AuthContext;
