import axios from "axios";

export const axiosVSCMS = axios.create({
  baseURL: "http://localhost:9090",
});

export const axiosUserMS = axios.create({
  baseURL: "http://localhost:9091",
});

export const axiosEventMS = axios.create({
  baseURL: "http://localhost:9092",
});
