import axios from "axios";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
const APILocation = "http://localhost:8080";
const AllList = () => {
  const [scores, setScores] = useState([]);
  const navigate = useNavigate();
  //문3) 전체 데이터 조회
  const v = async () => {
    //여기는  채우세요
    //controller 와 연동 (localhost:8080/basic/list)로 데이터 가지고 옴
    //ResponseEntity<List<ScoreDTO>>
    const { data } = await axios.get(`${APILocation}/basic/list`);
    setScores(data);
    console.log(data);
  };

  const modifyHandler = async (e, i) => {
    //수정이지만 먼저 데이터를 조회하는 기능을 여기에 작성
    console.log(i);
    const { data } = await axios.get(`${APILocation}/basic/${i}`);
    console.log(data);
    navigate("/modify",{state : {data}})
  };

  const removeHandler = async (e, v) => {
    console.log(v);
    const { data } = await axios.delete(`${APILocation}/basic/${v}`);
    console.log(data);
    setScores(data);
  };
  return (
    <div>
      AllList
      <button onClick={v}>전체 데이터 조회 </button>
      <hr />
      <table>
        <tr>
          <th>번호</th>
          <th>국어</th>
          <th>영어</th>
          <th>수학</th>
          <th>총점</th>
          <th>평균</th>
          <th>등급</th>
        </tr>

        {scores &&
          scores.map((i) => (
            <tr>
              <td>{i.no}</td>
              <td>{i.korea}</td>
              <td>{i.eng}</td>
              <td>{i.math}</td>
              <td>{i.total}</td>
              <td>{i.avg}</td>
              <td>{i.grade}</td>
              <td>
                <button onClick={(e) => modifyHandler(e, i.no)}>{i.no}수정</button>
              </td>
              <td>
                <button onClick={(e) => removeHandler(e, i.no)}>삭제</button>
              </td>
            </tr>
          ))}
      </table>
      {/*  ScoreDTO에 no를 추가하고 no는 데이터가 들어올때마다 1씩 증가되도록하고 삭제버튼을 누르면
            axios.delete(url) //controller DeleteMapping 
        */}
    </div>
  );
};

export default AllList;
