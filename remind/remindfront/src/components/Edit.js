import axios from "axios";
import React, { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";

const APILocation = "http://localhost:8080";

const Edit = () => {
  const loc = useLocation();
  const {
    state: { data },
  } = loc;

  const [d, setD] = useState({
    no: 0,
    jumin: "",
    phone: "",
    address: "",
    age: 0,
    familycnt: 0,
    name: "",
    dad: "",
    mom: "",
  });

  useEffect(() => {
    if (data) {
      setD({
        no: data.no,
        jumin: data.jumin,
        phone: data.phone,
        address: data.address,
        age: data.age,
        familycnt: data.familycnt,
        name: data.name,
        dad: data.dad,
        mom: data.mom,
      });
    }
  }, [data]);

  const handler = (e) => {
    const { name, value } = e.target;
    setD((prev) => ({
      ...prev,
      [name]: name === "age" || name === "familycnt" ? Number(value) : value,
    }));
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await axios.put(`${APILocation}/town/modify/${d.no}`, d);
      console.log("수정 성공:", res.data);
    } catch (error) {
      console.error("수정 실패:", error);
    }
  };

  return (
    <div>
      <h3>데이터 수정</h3>
      <form onSubmit={onSubmit}>
        <div>
          <label>주민번호</label>
          <input name="jumin" value={d.jumin} onChange={handler} />
          <label>폰번호</label>
          <input name="phone" value={d.phone} onChange={handler} />
          <label>주소</label>
          <input name="address" value={d.address} onChange={handler} />
          <label>나이</label>
          <input name="age" value={d.age} onChange={handler} />
          <label>가족 수</label>
          <input name="familycnt" value={d.familycnt} onChange={handler} />
          <label>이름</label>
          <input name="name" value={d.name} onChange={handler} />
          <label>아버지 성함</label>
          <input name="dad" value={d.dad} onChange={handler} />
          <label>어머니 성함</label>
          <input name="mom" value={d.mom} onChange={handler} />
        </div>
        <button type="submit">수정</button>
      </form>
    </div>
  );
};

export default Edit;
