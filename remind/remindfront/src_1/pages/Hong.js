import axios from "axios";
import React, { useEffect, useState } from "react";

const Hong = () => {

  const [hongData, setHongData] = useState([]);

  const v = async () => {
    const { data } = await axios.get(`http://localhost:8080/hong/find`);
    setHongData(data);
    console.log(data);
  };

  const f = async () => {
    const { data } = await axios.get(`http://localhost:8080/hong/filter`);
    setHongData(data);
    console.log("필터된 데이터 : " + data);
  };
  useEffect(() => {
    v();
  }, []);

  return (
    //background: linear-gradient(to right, #005ac1, #a0eacb);
    <body style={{ background: "linear-gradient(to right, #fafca7, #fdc7ff)" }}>
      <div
        style={{
          maxWidth: "50rem",
          margin: "2.5rem auto 0",
          padding: "2rem",
          backgroundColor: "white",
          boxShadow:
            "0 1px 3px rgba(0, 0, 0, 0.1), 0 1px 2px rgba(0, 0, 0, 0.06)",
          borderRadius: "0.375rem",
        }}
      >
        <h1 style={{ textAlign: "center", fontFamily: "YOnepickTTF-Bold",
            fontSize: "3rem", color:"#db9239"}}>
          홍길동 찾기 <button onClick={f}>50 ~ 70세만 보기</button>&nbsp;
          <button onClick={v}>전체보기</button>{" "}
        </h1>
        <hr style={{ border: "#fdc7ff dashed 1px", width: "65%" }} />
        <br />
        {Array.isArray(hongData) &&
          hongData.map((i, idx) => (
            <div>
              <table
                style={{
                  borderCollapse: "collapse",
                  height: "70px",
                  width: "600px",
                  margin: "auto",
                }}
              >
                <thead key={idx}>
                  <tr style={{ backgroundColor: "#f0a03e", color: "white" }}>
                    <th style={{ border: "solid #bbb 1px" }}>식별번호</th>
                    <th style={{ border: "solid #bbb 1px" }}>이메일</th>
                    <th style={{ border: "solid #bbb 1px" }}>나이</th>
                    <th style={{ border: "solid #bbb 1px" }}>이름</th>
                    <th style={{ border: "solid #bbb 1px" }}>비밀번호</th>
                    <th style={{ border: "solid #bbb 1px" }}>주소</th>
                    <th style={{ border: "solid #bbb 1px" }}>도시</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td style={{ border: "solid #bbb 1px" }}>{i.mno}</td>
                    <td style={{ border: "solid #bbb 1px" }}>{i.email}</td>
                    <td style={{ border: "solid #bbb 1px" }}>{i.age}</td>
                    <td style={{ border: "solid #bbb 1px" }}>{i.name}</td>
                    <td style={{ border: "solid #bbb 1px" }}>{i.password}</td>
                    <td style={{ border: "solid #bbb 1px" }}>{i.address}</td>
                    <td style={{ border: "solid #bbb 1px" }}>{i.city}</td>
                  </tr>
                </tbody>
                <br />
              </table>
            </div>
          ))}
      </div>
      <br/><br/>
    </body>
  );
};

export default Hong;
