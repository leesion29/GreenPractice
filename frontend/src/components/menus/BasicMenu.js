import React from "react";
import { useSelector } from "react-redux";
import { Link } from "react-router-dom";

const BasicMenu = () => {
  const loginState = useSelector((state) => state.loginSlice);
  return (
    <nav id="navbar" className="flex bg-blue-300">
      <div className="w-4/5 bg-gray-500">
        <ul className="flex p-4 bg-gray-500">
          <li className="pr-6 text-2xl">
            <Link to={"/"}>MAIN</Link>
          </li>
          <li className="pr-6 text-2xl">
            <Link to={"/about"}>About</Link>
          </li>
          {loginState.email ? ( //로그인사용자만 출력되는 메뉴
            <>
              <li className="pr-6 text-2xl">
                <Link to={"/todo/"}>todo</Link>
              </li>
              <li className="pr-6 text-2xl">
                <Link to={"/products/"}>Products</Link>
              </li>
            </>
          ) : (
            <></>
          )}
        </ul>
      </div>
      <div className="w-1/5 flex justify-end bg-orange-300 p-4 font-medium">
        {!loginState.email ? (
          <div className="text-white text-sm m-1 rounded">
            <Link to={"/member/login"}>Login</Link>
          </div>
        ) : (
          <></>
        )}
      </div>
    </nav>
  );
};

export default BasicMenu;
