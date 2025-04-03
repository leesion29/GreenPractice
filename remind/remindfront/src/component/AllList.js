import axios from "axios";
import React, { useState } from "react";
const APILocation = "http://localhost:8080";

// 문제3 > 전체 데이터 조회
const AllList = () => {
  const [sc, setSc] = useState([]);
  const [no, setNo] = useState("");
  const v = async () => {
    // 여기를 채우세요
    // 컨트롤러와 연동하여 리스트를 가져오도록 합니다.(responseEntity dtoList)
    const res = await axios.get(`${APILocation}/basic/list`);
    setSc(res.data);
  };
  const f = async () => {
    // 여기를 채우세요
    // 컨트롤러와 연동하여 리스트를 가져오도록 합니다.(responseEntity dtoList)
    const res = await axios.get(`${APILocation}/basic/filter`);
    setSc(res.data);
  };
  const d = async (num) => {
    const res = await axios.delete(`${APILocation}/basic/${num}`);
  };
  return (
    <div>
      <hr />
      AllList
      <br />
      <button onClick={f}>국어 50점 이상만 보기</button>
      <button onClick={v}>전체 데이터 조회</button>
      <hr />
      <table>
        <tr>
          <th>번호</th>
          <th>국</th>
          <th>영</th>
          <th>수</th>
          <th>총점</th>
          <th>평균</th>
          <th>등급</th>
        </tr>
        {sc &&
          sc.map((i) => (
            <tr>
              <td>{i.no}</td>
              <td>{i.korea}</td>
              <td>{i.eng}</td>
              <td>{i.math}</td>
              <td>{i.total}</td>
              <td>{i.avg}</td>
              <td>{i.grade}</td>
            </tr>
          ))}
      </table>
      
      <div>
        <button
          onClick={() => {
            const num = prompt("삭제할 번호 입력해");
            setNo(num);
            d(num);
          }}
        >
          삭제
        </button>
      </div>
    </div>
  );
};

export default AllList;
