import React from "react";
import { useNavigate, useParams } from "react-router-dom";
import ModifyComponent from "../../components/todo/ModifyComponent";

const ModifyPage = () => {
  const {tno} = useParams();
  console.log("수정 페이지 : ", tno);

  return (
    <div className="p-4 w-full bg-white">
      <div className="text-3xl font-extrabold">Todo Modify Page</div>
      <ModifyComponent tno = {tno} ></ModifyComponent>
    </div>
  );
};

export default ModifyPage;
