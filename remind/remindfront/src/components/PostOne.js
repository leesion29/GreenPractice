import axios from "axios";
import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

const APILocation = "http://localhost:8080";

const PostOne = () => {
  const { postId } = useParams();
  const [score, setScore] = useState({});

  useEffect(() => {
    const fetchData = async () => {
      try {
        const { data } = await axios.get(`${APILocation}/post/a/${postId}`);
        console.log("data:", data);
        setScore(data);
      } catch (error) {
        console.error("게시글 데이터를 불러오는 데 실패했습니다:", error);
      }
    };
    fetchData();
  }, [postId]);

  return (
    <div>
      <h1>제목: {score.title}</h1>
      <p>게시글 내용: {score.content}</p>
      <hr />
      <ul>
        {Array.isArray(score.comments) &&
          score.comments.map((i, idx) => (
            <li key={idx}>{i.content}</li>
        ))}
      </ul>
    </div>
  );
};

export default PostOne;
