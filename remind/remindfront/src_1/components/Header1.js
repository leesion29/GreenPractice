import React from "react";
import { useSelector } from "react-redux";

const Header1 = () => {
  const userName = useSelector((state) => state.auth.userName);
  return <div>Header1,{userName}</div>;
};

export default Header1;
