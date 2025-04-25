// slices/authSlice.js
import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  userId: null,
  userName: null,
  isLoggedIn: false,
};

const authSlice = createSlice({
  name: "auth",
  initialState,
  reducers: {
    login(state, action) {
      const { userId, userName } = action.payload;
      console.log("vv", userName);
      state.userId = userId;
      state.userName = userName;
      state.isLoggedIn = true;
    },
    logout(state) {
      state.userId = null;
      state.userName = null;
      state.isLoggedIn = false;
    },
  },
});

export const { login, logout } = authSlice.actions;
export default authSlice.reducer;
