import { createBrowserRouter } from "react-router-dom";
import App from "../App";
import Home from "../pages/Home";
import About from "../pages/About";
import AllList from "../components/AllList";
import Yellow from "../components/Yellow";
import Modify from "../components/Modify";
import Dong from "../components/Town";
import Create from "../components/Create";
import View from "../components/View";
import Del from "../components/Del";
import Edit from "../components/Edit";
const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
    children: [
      { index: true, element: <Home /> }, // "/"
      { path: "about", element: <About /> }, // "/about"
      { path: "list", element: <AllList /> },
      { path: "add", element: <Yellow /> },
      { path: "modify", element: <Modify /> },
      { path: "dong", element: <Dong /> },
      { path: "create", element: <Create /> },
      { path: "view", element: <View /> },
      { path: "del", element: <Del /> },
      { path: "edit", element: <Edit /> },
    ],
  },
]);

export default router;
