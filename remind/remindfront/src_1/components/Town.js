import React from 'react'
import { useNavigate } from 'react-router-dom';

const Town = () => {
    const nav = useNavigate();
  return (
    <div><h3>동사무소 주소록</h3>
    <hr/>
    <button onClick={()=>{nav("/create")}}>등록</button>&nbsp;
    <button onClick={()=>{nav("/view")}}>조회</button>&nbsp;
    <button onClick={()=>{nav("/del")}}>삭제/수정</button>&nbsp;
    </div>
  )
}

export default Town