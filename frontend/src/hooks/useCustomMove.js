import { useState } from "react";
import {
  createSearchParams,
  useNavigate,
  useSearchParams,
} from "react-router-dom";
const getNum = (param, defaultValue) => {
  if (!param) return defaultValue;
  return parseInt(param);
};
const useCustomMove = () => {
  const navigate = useNavigate();
  const [refresh, setRefresh] = useState(false); //추가
  const [queryParams] = useSearchParams();
  const page = getNum(queryParams.get("page"), 1);
  const size = getNum(queryParams.get("size"), 10);
  const queryDefault = createSearchParams({ page, size }).toString();
  
  const moveToModify = (num) => {
    console.log(queryDefault);
    navigate({
      pathname: `../modify/${num}`,
      search: queryDefault, //수정시 기존의 쿼리 스트링 유지를 위해
    });
  };
  const moveToRead = (num) => {
    console.log(queryDefault);
    navigate({
      pathname: `../read/${num}`,
      search: queryDefault, //수정시 기존의 쿼리 스트링 유지를 위해
    });
  };
  const moveToList = (pageParam) => {
    let queryStr = "";
    if (pageParam) {
      const pageNum = getNum(pageParam.page, 1);
      const sizeNum = getNum(pageParam.size, 10);
      queryStr = createSearchParams({
        page: pageNum,
        size: sizeNum,
      }).toString();
    } else {
      queryStr = queryDefault;
    }
    navigate({
      pathname: `../list`,
      search: queryStr,
    });
    setRefresh(!refresh);
  };
  return { moveToList, moveToModify, moveToRead, page, size, refresh };
};
export default useCustomMove;
