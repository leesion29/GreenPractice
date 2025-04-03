import { useEffect } from "react";
import "./App.css";
import axios from "axios";
import Green from "./component/Green";
import Yellow from "./component/Yellow";
import AllList from "./component/AllList";

const APILocation = "http://localhost:8080";
function App() {
  return <><Yellow/>
  <AllList/></>
}

export default App;
