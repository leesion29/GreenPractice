import React from "react";
import { useSelector } from "react-redux";

function Header() {
  const userName = useSelector((state) => state.auth.userName);
  const login = useSelector((state) => state.auth.isLoggedIn);
  const sc1 = useSelector((state) => state.auth.korea);
  const sc2 = useSelector((state) => state.auth.eng);
  const sc3 = useSelector((state) => state.auth.math);

  const total = parseInt(sc1) + parseInt(sc2) + parseInt(sc3);
  const avg = total / 3;
  const grade = avg >= 60 ? "pass" : "non-pass";

  return (
    <div
      style={{
        backgroundColor: "#333",
        color: "white",
        padding: "20px",
        textAlign: "left",
        fontFamily: "Arial, sans-serif",
        borderBottom: "3px solid #fbca1f",
      }}
    >
      <div style={{ fontSize: "20px", fontWeight: "bold" }}>
        Welcome, {userName}
      </div>

      {login ? (
        <div style={{ color: "#00d0ff", marginTop: "10px", lineHeight: "1.6" }}>
          <div style={{ fontWeight: "bold" }}>[Your score]</div>
          <div>
            국어 : {sc1 || "미입력"} | 영어 : {sc2 || "미입력"} | 수학 :{" "}
            {sc3 || "미입력"}
          </div>
          <div>
            총합 : {isNaN(total) ? "미입력" : total} | 평균 :{" "}
            {isNaN(avg) ? "미입력" : Math.round(avg)} | 합격 여부 :{" "}
            {isNaN(avg) ? "미입력" : grade}
          </div>
        </div>
      ) : (
        <div style={{ marginTop: "10px", color: "#f88" }}>로그인 해주세요</div>
      )}
    </div>
  );
}

export default Header;
