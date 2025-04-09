import axios from "axios";
import React, { useEffect, useState } from "react";
import "../App.css";

const APILocation = "http://localhost:8080";

const DongsamusoList = () => {
  const [d, setD] = useState([]);

  useEffect(() => {
    const dd = async () => {
      const { data } = await axios.get(`${APILocation}/dong/dd`);
      setD(data);
    };
    dd();
  }, []);

  return (
    <div>
      <h3
        style={{
          textAlign: "center",
          fontSize: "1.5rem",
          color: "#376cab",
          marginTop: "20px",
        }}
      >
        <h2
          style={{
            fontFamily: "YOnepickTTF-Bold",
            fontSize: "2rem",
          }}
        >
          Let's see dongsamusoList data!
        </h2>
      </h3>
      <hr />
      <table
        style={{
          width: "80%",
          margin: "auto",
          borderCollapse: "collapse",
          boxShadow: "0 0 10px rgba(0,0,0,0.1)",
          fontFamily: "Segoe UI, sans-serif",
        }}
      >
        <thead>
          <tr>
            <th
              style={{
                border: "1px solid #ccc",
                padding: "10px",
                backgroundColor: "#4a90e2",
                color: "white",
              }}
            >
              번호
            </th>
            <th
              style={{
                border: "1px solid #ccc",
                padding: "10px",
                backgroundColor: "#4a90e2",
                color: "white",
              }}
            >
              이름
            </th>
            <th
              style={{
                border: "1px solid #ccc",
                padding: "10px",
                backgroundColor: "#4a90e2",
                color: "white",
              }}
            >
              국어
            </th>
            <th
              style={{
                border: "1px solid #ccc",
                padding: "10px",
                backgroundColor: "#4a90e2",
                color: "white",
              }}
            >
              영어
            </th>
            <th
              style={{
                border: "1px solid #ccc",
                padding: "10px",
                backgroundColor: "#4a90e2",
                color: "white",
              }}
            >
              수학
            </th>
            <th
              style={{
                border: "1px solid #ccc",
                padding: "10px",
                backgroundColor: "#4a90e2",
                color: "white",
              }}
            >
              합
            </th>
            <th
              style={{
                border: "1px solid #ccc",
                padding: "10px",
                backgroundColor: "#4a90e2",
                color: "white",
              }}
            >
              평균
            </th>
            <th
              style={{
                border: "1px solid #ccc",
                padding: "10px",
                backgroundColor: "#4a90e2",
                color: "white",
              }}
            >
              가치
            </th>
          </tr>
        </thead>
        <tbody>
          {d.map((item, idx) => (
            <tr
              key={idx}
              style={{
                backgroundColor: idx % 2 === 0 ? "#f9f9f9" : "white",
                transition: "background-color 0.2s",
              }}
              onMouseEnter={(e) =>
                (e.currentTarget.style.backgroundColor = "#eef5ff")
              }
              onMouseLeave={(e) =>
                (e.currentTarget.style.backgroundColor =
                  idx % 2 === 0 ? "#f9f9f9" : "white")
              }
            >
              <td
                style={{
                  border: "1px solid #ccc",
                  padding: "10px",
                  textAlign: "center",
                }}
              >
                {item.no}
              </td>
              <td
                style={{
                  border: "1px solid #ccc",
                  padding: "10px",
                  textAlign: "center",
                }}
              >
                {item.name}
              </td>
              <td
                style={{
                  border: "1px solid #ccc",
                  padding: "10px",
                  textAlign: "center",
                }}
              >
                {item.korea}
              </td>
              <td
                style={{
                  border: "1px solid #ccc",
                  padding: "10px",
                  textAlign: "center",
                }}
              >
                {item.eng}
              </td>
              <td
                style={{
                  border: "1px solid #ccc",
                  padding: "10px",
                  textAlign: "center",
                }}
              >
                {item.math}
              </td>
              <td
                style={{
                  border: "1px solid #ccc",
                  padding: "10px",
                  textAlign: "center",
                }}
              >
                {item.total}
              </td>
              <td
                style={{
                  border: "1px solid #ccc",
                  padding: "10px",
                  textAlign: "center",
                }}
              >
                {item.avg}
              </td>
              <td
                style={{
                  border: "1px solid #ccc",
                  padding: "10px",
                  textAlign: "center",
                }}
              >
                {item.price}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default DongsamusoList;
