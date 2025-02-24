import React from "react";
import { useSearchParams } from "react-router-dom";
import ListComponent from "../../components/menus/todo/ListComponent";

const ListPage = () => {
  const [queryParams] = useSearchParams();
  console.log(queryParams);
  const page = queryParams.get("page") || 1;
  const size = queryParams.get("size") || 10;
  return (
    <div className="p-4 w-full bg-orange-200">
      <div className="text-3xl font-extrabold">
      Todo List 페이지
      </div>
      <ListComponent />
  </div>
  );
};

export default ListPage;
// url에 따라다녀야 하는 정보
// 현재 페이지, 페이지 당 몇 개의 데이터
// 검색을 하면 검색어와 title, 내용, writer는
// url에 따라 다녀야 한다