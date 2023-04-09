import { configureStore } from "@reduxjs/toolkit";

import landingReducer from "../Features/City/landingSlice";
import userReducer from "../Features/Users/userSlice";

export const store = configureStore({
  reducer: {
    landingR: landingReducer,
    user: userReducer,
  },
});
