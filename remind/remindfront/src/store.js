// store.js
import { configureStore } from "@reduxjs/toolkit";
import authReducer from "./slices/authSlice"; // 예시로 auth slice 포함
const store = configureStore({
  reducer: {
    auth: authReducer,
    // 다른 reducer 추가 가능
  },
});

export default store;
