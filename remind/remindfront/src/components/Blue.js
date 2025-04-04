import React, { useState } from "react";
import axios from "axios";

const APILocation = "http://localhost:8080";
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
    setCnt((cnt) => cnt + 1);
    const { id, name } = arr[cnt % 5]; //5로 나눈 나머지를  index로 함
    const res = await axios.get(`${APILocation}/home/a?id=${id}&name=${name}`);
    // const { data } = res;
    //문1) 버튼을 누를때마다 arr 배열의 값을 하나씩 꺼내어 backend 에서 확인
    //배열이 5개만 있으니까 5가되면 0이되도록 하라
    // console.log(cnt);
    console.log(res.data);
  };

  return (
    <div>
      블루
      <button onClick={f}>눌러라</button>
    </div>
  );
};

export default Blue;
