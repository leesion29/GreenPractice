import axios from "axios";
import React, { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
const APILocation = "http://localhost:8080";
const ModifyComponent = () => {
  const loc = useLocation();
  const [score, setScore] = useState({ korea: 0, math: 0, eng: 0 });
  const {
    state: { data },
  } = loc;
  
  useEffect(() => {
    if (data) setScore({ korea: data.korea, math: data.math, eng: data.eng });
  }, [data]);
  const f = async (e, i) => {
    console.log(i);
    e.preventDefault();
    setScore(data);
    const res = await axios.put(`${APILocation}/basic/modify/${i}`, score);
    console.log(res);
  };
  const handler = (e) => {
    const { name, value } = e.target;
    console.log(name, value);
    setScore((i) => ({ ...i, [name]: Number(value) }));
  };
  
  return (
    <div>
      <h1>성적입력프로그램</h1>
      <form>
        <div>
          <label>국어</label>
          <input name="korea" value={score.korea} onChange={handler} />
        </div>

        <div>
          <label>수학</label>
          <input name="math" value={score.math} onChange={handler} />
        </div>

        <div>
          <label>영어</label>
          <input name="eng" value={score.eng} onChange={handler} />
        </div>
        <button type="submit" onClick={(e) => f(e, score.no)}>
          전송
        </button>
      </form>
    </div>
  );
};

export default ModifyComponent;
