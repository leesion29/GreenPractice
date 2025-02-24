import { useEffect, useState } from "react";
import { API_SERVER_HOST, getList } from "../../api/productsApi";
import useCustomMove from "../../hooks/useCustomMove";
import PageComponent from "../common/PageComponent";
import FetchingModal from "../common/FetchingModal";
const host = API_SERVER_HOST;
const initState = {
  dtoList: [],
  pageNumList: [],
  pageRequestDTO: null,
  prev: false,
  next: false,
  totoalCount: 0,
  prevPage: 0,
  nextPage: 0,
  totalPage: 0,
  current: 0,
};

const ListComponent = () => {
  const { page, size, refresh, moveToList, moveToRead } = useCustomMove(); //refresh

  //serverData는 나중에 사용
  const [serverData, setServerData] = useState(initState);
  const [fetching, setFetching] = useState(false);

  useEffect(() => {
    getList({ page, size }).then((data) => {
      setFetching(true);
      console.log("getList");
      console.log(data);
      setServerData(data);
      setFetching(false);
    });
  }, [page, size, refresh]);

  return (
    <div className="border-2 border-blue-100 mt-10 mr-2 ml-2">
      <h1>제품 목록 컴포넌트</h1>
      {fetching ? <FetchingModal /> : <></>}
      <div className="flex flex-wrap mx-auto justify-center p-6">
        {serverData.dtoList.map((i) => (
          <div
            key={i.tno}
            className="w-full min-w-[400px]  p-2 m-2 rounded shadow-md"
            onClick={() => moveToRead(i.pno)} //이벤트 처리 추가
          >
            <div className="flex flex-col h-full">
              <div className="font-extrabold text-2xl p-2 w-full">{i.pno}</div>
              <div className="text-2xl m-1 w-full flex flex-col">
                <div className="w-full overflow-hidden">
                  <img
                    alt="product"
                    className="m-auto rounded-md w-60"
                    src={`${host}/api/products/view/s_${i.uploadFileNames[0]}`}
                  ></img>
                </div>
                <div className="bottom-0 font-extrabold bg-white">
                  <div className="text-center p-1">이름 : {i.pname}</div>
                  
                </div>

                <div className="bottom-0 font-extrabold bg-white">
                <div className="text-center p-1">가격 : {i.price}</div>
                </div>

              </div>
            </div>
          </div>
        ))}
      </div>
      <PageComponent
        serverData={serverData}
        movePage={moveToList}
      ></PageComponent>
    </div>
  );
};

export default ListComponent;
