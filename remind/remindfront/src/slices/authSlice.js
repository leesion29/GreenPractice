// slices/authSlice.js
import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  userId: null,
  userName: null,
  isLoggedIn: false,
};
  //문1> authSlice에 calcScore를 만드시오.
  // 국, 영, 수를 입력하여 총점, 평균, 등급을 출력합니다.
const authSlice = createSlice({
  name: "auth",
  initialState,
  reducers: {
    login(state, action) {
      const { userId, userName } = action.payload;
      state.userId = userId;
      state.userName = userName;
      state.isLoggedIn = true;
    },
    logout(state) {
      state.userId = null;
      state.userName = null;
      state.isLoggedIn = false;
    },
    calcScore: (state, action) => {
      const { math, eng, korea, grade } = action.payload;
      state.math = math;
      state.eng = eng;
      state.korea = korea;
      state.grade = grade;
    }
  },
});

export const { login, logout, calcScore } = authSlice.actions;
export default authSlice.reducer;
