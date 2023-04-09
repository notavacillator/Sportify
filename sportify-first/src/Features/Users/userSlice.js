import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  role: 0,
  user: {},
  accessToken: "",
};

const userSlice = createSlice({
  name: "user",
  initialState: {
    value: {},
  },
  reducers: {
    changeRole: (state, action) => {
      state.role = action.payload;
    },
    saveUser: (state, action) => {
      state.user = action.payload;
    },
    changeToken: (state, action) => {
      state.accessToken = action.payload;
      console.log("access token-> " + state.accessToken);
    },
  },
});

export const userActions = userSlice.actions;

export default userSlice.reducer;
