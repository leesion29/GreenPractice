import React from "react";
import { useNavigate } from "react-router-dom";

const ModifyPage = ({ tno }) => {
  const navigate = useNavigate();

  const moveToRead = () => {
    navigate({ pathname: `/todo/read/${tno}` });
  };

  const moveToList = () => {
    navigate({ pathname: `/todo/list` });
  };
  return (
    <div>
      ModifyPage
      <hr />
      <button onClick={moveToRead}>조회페이지로 이동</button>
      <hr />
      <button onClick={moveToList}>수정페이지로 이동</button>
    </div>
  );
};

export default ModifyPage;
