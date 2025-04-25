import React from "react";
import { useSelector } from "react-redux";

function Header() {
  const userName = useSelector((state) => state.auth.userName);
  return <div>Welcome, {userName}</div>;
}

export default Header;
