import React from "react";
import { useDispatch, useSelector } from "react-redux";
import ScorePage from "./ScorePage";
import { login, logout } from "../slices/authSlice";

function LoginPage() {
  const loginCheck = useSelector((state) => state.auth.isLoggedIn);

  const dispatch = useDispatch();

  const handleLogin = () => {
    if (!loginCheck) {
      dispatch(login({ userId: "2020143039", userName: "이시온" }));
    } else {
      dispatch(logout());
    }
  };

  return (
    <div>
      <button onClick={handleLogin}>{!loginCheck ? "Login" : "Logout"}</button>
      <br />
      <br />
      <div>
        <ScorePage />
      </div>
    </div>
  );
}

export default LoginPage;
