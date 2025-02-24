import React, { useState } from "react";
// Ban에서 받아온 Props
const Bun = ({ no, v, calcGrade }) => {
  const [math, setMath] = useState(0);
  const [eng, setEng] = useState(0);
  const [korea, setKorea] = useState(0);

  const handleChange = (e) => {
    // 객체 구조 분해 할당
    const { name, value } = e.target; // e.target -> 현재 발생하는 이벤트

    const numValue = Number(value);

    if (name === "math") {
      setMath(numValue);
    } else if (name === "eng") {
      setEng(numValue);
    } else if (name === "korea") {
      setKorea(numValue);
    }

    v(no, { [name]: numValue });
  };

  const total = math + eng + korea;
  const avg = total / 3;

  return (
    <div>
      <h1>{no} 번 학생</h1>
      <form>
        <div>
          <label>국어</label>
          <input
            type="number"
            name="korea"
            value={korea}
            onChange={handleChange}
          />
        </div>
        <div>
          <label>수학</label>
          <input
            type="number"
            name="math"
            value={math}
            onChange={handleChange}
          />
        </div>
        <div>
          <label>영어</label>
          <input
            type="number"
            name="eng"
            value={eng}
            onChange={handleChange}
          />
        </div>
        <div>
          <label>총점</label>
          <input type="number" name="total" value={total} readOnly />
        </div>
        <div>
          <label>평균</label>
          <input type="number" name="avg" value={avg} readOnly />
        </div>
        <div>
          <label>등급</label>
          <input
            type="text"
            name="grade"
            value={calcGrade(avg)}
            readOnly
          />
        </div>
      </form>
      <hr style={{ borderTop: "3px solid black" }} />

    </div>
  );
};

export default Bun;
