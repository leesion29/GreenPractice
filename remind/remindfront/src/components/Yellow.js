import React, { useState } from "react";
import axios from "axios";

const APILocation = "http://localhost:8080";
const Yellow = () => {
  const [score, setScore] = useState({
    math: 0,
    eng: 0,
    korea: 0,
  });
  const f = async () => {
    console.log(score);
    const res = await axios.post(`${APILocation}/basic`, score);
    const { data } = res;
    console.log(data);
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
        </div><br />
        <button type="submit" onClick={f}>
          전송
        </button>
      </form>
    </div>
  );
};

export default Yellow;
