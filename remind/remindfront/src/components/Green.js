import React from "react";
import axios from "axios";

const APILocation = "http://localhost:8080";
const Green = () => {
  const arr = [
    { id: 3, name: "hong" },
    { id: 13, name: "hong1" },
    { id: 23, name: "hong2" },
    { id: 33, name: "hong3" },
    { id: 43, name: "hong4" },
  ];

  const f = async () => {
    const res = await axios.post(`${APILocation}/home`, {
      id: 3,
      name: "홍길동",
    });

    console.log(res.data);
  };

  return (
    <div>
      블루
      <button onClick={f}>눌러라</button>
    </div>
  );
};

export default Green;
