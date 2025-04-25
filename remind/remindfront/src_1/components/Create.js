import axios from 'axios';
import React, { useState } from 'react'

const APILocation = "http://localhost:8080";
const Create = () => {
  const [d, setD] = useState({
    no : 0,
    jumin : "000000-0000000",
    phone : "000-0000-0000",
    address : "",
    age : 0,
    familycnt : 0,
    name : "",
    dad : "",
    mom : ""
  })

  const c = async () => {
    const res = await axios.post(`${APILocation}/town`, d);
  }
  const handler = (e) => {
    const { name, value } = e.target;
    console.log(name, value);
    setD((i) => ({ ...i, [name]: (value) }));
  };
  return (
    <div>
      <h3>데이터 추가</h3>
      <form>
      <div>
          <label>주민번호</label>
          <input name="jumin" value={d.jumin} onChange={handler} />
          <label>폰번호</label>
          <input name="phone" value={d.phone} onChange={handler} />
          <label>주소</label>
          <input name="address" value={d.address} onChange={handler} />
          <label>나이</label>
          <input name="age" value={d.age} onChange={handler} />'
          <label>가족 수</label>
          <input name="familycnt" value={d.familycnt} onChange={handler} />
          <label>이름</label>
          <input name="name" value={d.name} onChange={handler} />
          <label>아버지 성함</label>
          <input name="dad" value={d.dad} onChange={handler} />
          <label>어머니 성함</label>
          <input name="mom" value={d.mom} onChange={handler} />
        </div>
        <button type = "submit" onClick={c}>제출</button>
      </form>
      <hr/>

    </div>
  )
}

export default Create