import axios from "axios";
import React, { useState } from "react";
const APILocation = "http://localhost:8080";
const View = () => {
  const [d, setD] = useState([]);

  const v = async () => {
    const { data } = await axios.get(`${APILocation}/town/v`);
    setD(data);
    console.log(data);
  };

  return (
    <div>
      <h3>동사무소 데이터 조회</h3>
      <button onClick={v}>데이터 조회</button>
      <hr />
      <table>
        <tr>
          <th>번호</th>
          <th>주민번호</th>
          <th>폰번호</th>
          <th>주소</th>
          <th>나이</th>
          <th>가족 수</th>
          <th>이름</th>
          <th>母</th>
          <th>父</th>
        </tr>

        {d &&
          d.map((d) => (
            
            <tr>
                <td>{d.no}</td>
                <td>{d.jumin}</td>
                <td>{d.address}</td>
                <td>{d.age}</td>
                <td>{d.familycnt}</td>
                <td>{d.name}</td>
                <td>{d.dad}</td>
                <td>{d.mom}</td>
            </tr>
          ))}
      </table>
    </div>
  );
};

export default View;
