import axios from "axios";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
const APILocation = "http://localhost:8080";

const Del = () => {
  const navigate = useNavigate();
  const [d, setD] = useState([]);

  const v = async () => {
    const { data } = await axios.get(`${APILocation}/town/v`);
    setD(data);
    console.log(data);
  };
  const r = async (e, n) => {
    console.log(n);
    const { data } = await axios.delete(`${APILocation}/town/${n}`);
    setD(data);
  };
  useEffect(() => {
    v();
  }, []);
  const m = async (e, i) => {
    const { data } = await axios.get(`${APILocation}/town/${i}`);
    navigate("/edit", { state: { data } });
  };

  return (
    <div>
      <h3>동사무소 데이터 삭제/ 수정</h3>
      <p>삭제하거나 수정하고 싶은 대상의 버튼을 클릭하세요!</p>
      <hr />
      <table>
        <tr>
          <th>번호</th>
          <th>주민번호</th>
          <th>폰번호</th>
          <th>주소</th>
          <th>나이</th>
          <th>가족 수</th>
          <th>母</th>
          <th>父</th>
        </tr>

        {d &&
          d.map((dd) => (
            <tr>
              <td>
                <button
                  onClick={(e) => {
                    r(e, dd.no);
                  }}
                >
                  {dd.no}
                </button>
              </td>
              <td>{dd.jumin}</td>
              <td>{dd.address}</td>
              <td>{dd.age}</td>
              <td>{dd.familycnt}</td>
              <td>{dd.name}</td>
              <td>{dd.dad}</td>
              <td>{dd.mom}</td>
              <button
                onClick={(e) => {
                  m(e, dd.no);
                }}
              >
                edit
              </button>
            </tr>
          ))}
      </table>
    </div>
  );
};

export default Del;
