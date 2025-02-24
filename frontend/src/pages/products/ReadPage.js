import React, { useCallback } from "react";
import {
  createSearchParams,
  useNavigate,
  useParams,
  useSearchParams,
} from "react-router-dom";
import ReadComponent from "../../components/products/ReadComponent";


const ReadPage = () => {
  const { pno } = useParams();
  const navigate = useNavigate();
  const [queryParams] = useSearchParams();
  const page = queryParams.get("page") ? parseInt(queryParams.get("page")) : 1;
  const size = queryParams.get("size") ? parseInt(queryParams.get("size")) : 10;
  const queryStr = createSearchParams({ page, size }).toString();


  const moveToModify = useCallback(
    (pno) => {
      navigate({ pathname: `/products/modify/${pno}`, search: queryStr });
    },
    [pno, page, size]
  );

  const moveToList = useCallback(()=>{
    navigate({pathname: `products/list`, search: queryStr})
  },[page, size])

  return (
    <div className="text-3xl font-extrabold">
      products Read Page Component {pno}
      <div>
        {/* <button onClick={() => moveToModify(pno)}> 수정 테스트 </button> */}
        <ReadComponent pno={pno}></ReadComponent>
      </div>
    </div>
  );
};

export default ReadPage;
