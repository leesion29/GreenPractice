import axios from "axios";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

const PostAndComment = () => {
  const [p, setP] = useState([]);
  const nav = useNavigate();
  useEffect(() => {
    const getPost = async () => {
      const { data } = await axios.get(`http://localhost:8080/post/list`);
      setP(data);
      console.log(data);
    };
    getPost();
  }, []);
  return (
    <div>
      <h1 style={{textAlign:"center", fontFamily: "YOnepickTTF-Bold", color : "#434775"}}>Post</h1>
      <hr />&nbsp;
      <table style={{ border: "1px solid black", borderCollapse:"collapse", margin:"auto", width:"50%"}}>
        <thead>
          <tr style={{background: "lightblue"}}>
            <th style={{ border: "1px solid black", padding:"10px"}}>번호</th>
            <th style={{ border: "1px solid black", padding:"10px" }}>제목</th>
            <th style={{ border: "1px solid black", padding:"10px" }}>내용</th>
          </tr>
        </thead>
        <tbody>
          {p.map((i) => (
            <tr key={i.id}>
              <td style={{ border: "1px solid black", padding:"10px", backgroundColor:"#e0dede"}}>{i.id}</td>
              <td style={{ border: "1px solid black", padding:"10px" }}>{i.title}</td>
              <td style={{ border: "1px solid black", padding:"10px", backgroundColor:"#f5f3df" }}>{i.content} <button style = {{float:"right"}} onClick={()=>{nav(`/a/${i.id}`)}}>이동</button></td>
            </tr>
          ))}
        </tbody>
      </table>
      <div>&nbsp;</div>
    </div>
  );
};

export default PostAndComment;
