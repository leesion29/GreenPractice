import axios from "axios";
import React, { useState } from "react";
const APILocation = "http://localhost:8080";

const Yellow = () => {
  const [sc, setSc] = useState({
    math: 0,
    eng: 0,
    korea: 0,
  });
  const f = async () => {
    const res = await axios.post(`${APILocation}/basic`, sc);
    const { data } = res;
    console.log(data);
  };


  const handler = (e) => {
    const {name, value} = e.target;
    console.log(name, value);
    setSc((i)=> ({...i, [name]: Number(value)}));
  };
  return (
    <div>
      Yellow
      <h1>성적 입력 프로그램</h1>
      <form method="post">
        <div>
          <label>국어</label>
          <input name = "korea" value={sc.korea} onChange={handler}/>
          <label>영어</label>
          <input name = "eng" value={sc.eng} onChange={handler}/>
          <label>수학</label>
          <input name = "math" value={sc.math} onChange={handler}/>
        </div>
      </form>
      <button type="submit" onClick={f}>
        전송
      </button>
    </div>
  );
};

export default Yellow;
