import React, { useState } from "react";
import axios from "axios";
import { useEffect } from "react";
const APILocation = "http://localhost:8080";

const Green = () => {
useEffect(() => {
  const f = async () => {
    const res = await axios.post(
      `${APILocation}/home`, {
        id : 3, 
        name : "홍길동",
      }
    );
    console.log(res.data);
  };
  f();
}, []);
  return (
    <div>
    </div>
  );
};

export default Green;
