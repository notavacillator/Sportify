import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";
import { setCredentials,logout } from "../../Features/auth/authSlice";

const baseQuery = fetchBaseQuery({
    baseUrl: ''
})