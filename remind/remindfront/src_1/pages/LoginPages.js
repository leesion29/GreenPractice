import React from "react";
import { useDispatch } from "react-redux";
import { login } from "../slices/authSlice";

const LoginPages = () => {
  const dispatch = useDispatch();

  const handleLogin = () => {
    dispatch(login({ userId: "200501011", userName: "이재오" }));
  };

  return <button onClick={handleLogin}>Login</button>;
};

export default LoginPages;
