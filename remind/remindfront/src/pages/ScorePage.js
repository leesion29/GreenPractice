import React, { useState } from "react";
import { calcScore } from "../slices/authSlice";
import { useDispatch, useSelector } from "react-redux";

const ScorePage = () => {
  const login = useSelector((state) => state.auth.isLoggedIn);
  const [formData, setFormData] = useState({
    k: 0,
    e: 0,
    m: 0,
    grade: "",
  });

  const dispatch = useDispatch();
  const handleScore = () => {
    if (login) {
      dispatch(
        calcScore({
          math: formData.m,
          eng: formData.e,
          korea: formData.k,
          grade: "",
        })
      );
    } else {
      alert("로그인 해주세요");
    }
  };

  const handleInput = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };
  return (
    <div style={{ backgroundColor: "#bbb", margin: "0 Auto", width: "500px" }}>
      <br />
      <div>
        <form style={{ textAlign: "center" }}>
          <label>
            국어
            <br />
            <input
              onChange={handleInput}
              name="k"
              placeholder="국어 점수를 입력하세요"
              style={{
                outline: "none",
                borderTop: "none",
                borderLeft: "none",
                borderRight: "none",
                backgroundColor: "white",
              }}
            ></input>
            <br />
          </label>
          <br />
          <label>
            영어
            <br />
            <input
              onChange={handleInput}
              name="e"
              placeholder="영어 점수를 입력하세요"
              style={{
                outline: "none",
                borderTop: "none",
                borderLeft: "none",
                borderRight: "none",
                backgroundColor: "white",
              }}
            ></input>
            <br />
          </label>
          <br />
          <label>
            수학
            <br />
            <input
              onChange={handleInput}
              name="m"
              placeholder="수학 점수를 입력하세요"
              style={{
                outline: "none",
                borderTop: "none",
                borderLeft: "none",
                borderRight: "none",
                backgroundColor: "white",
              }}
            ></input>
            <br />
          </label>
          <br />
        </form>
        <br />
        <button
          onClick={handleScore}
          style={{
            background: "#fbca1f",
            fontFamily: "inherit",
            padding: "0.6em 1.3em",
            fontWeight: 900,
            fontSize: "15px",
            border: "3px solid black",
            borderRadius: "0.4em",
            boxShadow: "0.1em 0.1em",
            cursor: "pointer",
            margin: "0 auto",
            textAlign: "center",
            display: "block"
          }}
        >
          데이터 저장
        </button>
      </div>
      <br />
    </div>
  );
};

export default ScorePage;
