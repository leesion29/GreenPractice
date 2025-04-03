import React from "react";
import axios from "axios";
import { useEffect } from "react";
const APILocation = "http://localhost:8080";
const Red = () => {
  const arr = [
    { id: 3, name: "hong" },
    { id: 13, name: "hong1" },
    { id: 23, name: "hong2" },
    { id: 33, name: "hong3" },
    { id: 43, name: "hong4" },
  ];
  useEffect(() => {
    const f = async () => {
      //문 1)  a?id=3&name=hong  =>의 값을  arr에서 꺼내어 전달하고 backend에서 확인함
      const res = await axios.get(`${APILocation}/home/print?id=3&name=hong`);
      // const { data } = res;
      console.log(res.data);
    };
    f();
  }, []);
  return <div>레드</div>;
};

export default Red;
