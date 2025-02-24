import React, { useCallback } from "react";
import { Navigate, useNavigate, useParams } from "react-router-dom";
import ModifyComponent from "../../components/products/ModifyComponent";


const ModifyPage = () => {
  const {pno, page, size} = useParams();

const moveToModify = useCallback(
  (pno) => {
    navigate({pathname : `/products/modify/${pno}`, search: queryStr});
  },
  [pno, page, size]
);
const moveToList = useCallback(() => {
  navigate({pathname : `/products/modify/${pno}`, search: queryStr});
});
return(
  <div>
    <div>
      {/* Todo Read Page components{pno} */}
      <ModifyComponent pno = {pno}></ModifyComponent>
    </div>
  </div>
)
};

export default ModifyPage;
