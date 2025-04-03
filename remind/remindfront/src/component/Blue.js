import React, { useState } from "react";
import axios from "axios";
import { useEffect } from "react";
const APILocation = "http://localhost:8080";

const func = (cnt) => {
  if (cnt >= 4) {
    return 0;
  } else {
    let num = cnt + 1;
    return num;
  }
};
const Blue = () => {
  const arr = [
    { id: 3, name: "hong" },
    { id: 13, name: "hong1" },
    { id: 23, name: "hong2" },
    { id: 33, name: "hong3" },
    { id: 43, name: "hong4" },
  ];
  const [cnt, setCnt] = useState(0);
  const f = async () => {
    setCnt((cnt) => func(cnt));
    //ans1> 버튼을 누를 때마다 배열의 값을 하나씩 꺼내어 백엔드에서 확인
    const res = await axios.get(
      `${APILocation}/home/print?id=${arr[cnt].id}&name=${arr[cnt].name}`
    );

    console.log(res.data);
    console.log("cnt = ", cnt);
  };

  return (
    <div>
      블루
      <button onClick={f}>눌러라</button>
      <h1>{cnt}</h1>
    </div>
  );
};

export default Blue;
