// import axios from "axios";
// import React, { useState } from "react";

// const APILocation = "http://localhost:8080";

// const LoginPage = () => {
//   const [data, setData] = useState({ email: "", password: "" });

//   const handleChange = (e) => {
//     const { name, value } = e.target;
//     setData((prev) => ({ ...prev, [name]: value }));
//   };

//   const handleSubmit = async (e) => {
//     e.preventDefault();
//     try {
//       const response = await axios.post(`${APILocation}/member/read`, data);
//       console.log("로그인 성공:", response.data);
//       if(response.data) alert("로그인 성공! " + response.data.name + "님 어서오세요!");
//     } catch (error) {
//       console.error("로그인 실패:", error);
//     }
//   };

//   return (
//     <div>
//       <h2>로그인</h2>
//       <form onSubmit={handleSubmit}>
//         <div>
//           <label>이메일</label>
//           <input type="text" name="email" onChange={handleChange} />
//         </div>
//         <div>
//           <label>비밀번호</label>
//           <input type="password" name="password" onChange={handleChange} />
//         </div>
//         <div>
//           <input type="submit" value="전송" />
//         </div>
//       </form>
//     </div>
//   );
// };

// export default LoginPage;
