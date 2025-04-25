import { Link, Outlet } from "react-router-dom";

function App() {
  return (
    <>
      <nav>
        <Link to="/">홈</Link> | <Link to="/about">소개</Link> |{" "}
        <Link to ="list">전체조회</Link> | <Link to="/add">추가</Link> | <Link to="/dong">동사무소 조회</Link> |
        <Link to= "dlist">DongsamusoList</Link> | <Link to= "post">PostAndComment</Link> 
      </nav>
      <Outlet /> {/* 자식 컴포넌트가 여기에 렌더링됨 */}
    </>
  );
}

export default App;
