import React, { useEffect, useState } from "react";

const Red = () => {
  const [inputdata, setInputData] = useState([]);
  const [data, setData] = useState([]);
  useEffect(() => {
    fetch("http://localhost:5000/data")
      .then((i) => i.json())
      .then((i) => setData(i))
      .then((i) => setInputData(i))
      .catch((error) => console.error("데이터 불러오기 오류:", error));
  }, []);

  return (
    <div>
      <form action={"/data"} method="post">
        <input placeholder="입력하셈" name="inputdata"></input><button type="submit">제출</button>
      </form>
      <h1>데이터 프레임 출력</h1>
      <table border="1">
        <thead>
          <tr>
            <th>내용</th>
            <th>url 주소</th>
          </tr>
        </thead>
        <tbody>
          {data.map((i, idx) => (
            <tr key={idx}>
              <td>{i.text}</td>
              <td>{i.href}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default Red;
