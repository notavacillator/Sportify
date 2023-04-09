import { createSlice } from "@reduxjs/toolkit";
import { axiosVSCMS } from "../../app/api/sportify-api";
import { useDispatch } from "react-redux";

// const dispatch = useDispatch();
const democities = [
  { id: 1, name: "a" },
  { id: 2, name: "b" },
];
const initialState = {
  cities: [
    // { cityid: 0, cityName: "lucknow" },
    // { cityid: 1, cityName: "jabalpur" },
    // { cityid: 2, cityName: "bhilai" },
    // { cityid: 3, cityName: "gwalior" },
    // { cityid: 4, cityName: "surat" },
  ],

  city: {},
};
export const landingSlice = createSlice({
  name: "landing",
  initialState,
  reducers: {
    chooseCity: (state, action) => {
      state.city = action.payload;
      console.log("city is " + state.city.name); //debugging
      localStorage.setItem("chosenCity", JSON.stringify(state.city));
    },
    fillCities: (state, action) => {
      state.cities = action.payload;
    },
  },
});

export const fetchAllCities = () => async (dispatch) => {
  try {
    const response = await axiosVSCMS.get("/city");
    // console.log(response);
    const allCities = response.data;
    // console.log(allCities);
    dispatch(landingSlice.actions.fillCities([...allCities]));
  } catch (err) {
    console.error(err);
  }
};
export const { chooseCity, fillCities } = landingSlice.actions;
export default landingSlice.reducer;
