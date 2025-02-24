import React, { useState } from "react";
import Bun from "./Bun";
import Display from "./Display";

// 객체배열
const initialData = [1, 2, 3, 4, 5].map(no => ({
  studentNo: no,
  math: 0,
  eng: 0,
  korea: 0,
  total: 0,
  avg: 0,
  grade: ""
}));

const Ban = () => {
  const [data, setData] = useState(initialData);

// 성적을 바탕으로 등급을 결정하는 부분
  const calcGrade = (avg) => {
    if (avg >= 90) return "A";
    if (avg >= 80) return "B";
    if (avg >= 70) return "C";
    if (avg >= 60) return "D";
    return "F";
  };


  const handler = (no, newData) => {
    setData(prevData =>
      prevData.map(student => {
        if (student.studentNo === no) {
          const updatedStudent = {
            ...student,
            ...newData,
          };

          const math = updatedStudent.math || 0;
          const eng = updatedStudent.eng || 0;
          const korea = updatedStudent.korea || 0;
          updatedStudent.total = math + eng + korea;
          updatedStudent.avg = updatedStudent.total / 3;
          updatedStudent.grade = calcGrade(updatedStudent.avg);
          return updatedStudent;
        }
        return student;
      })
    );
  };

  return (
    <div> 
      {data.map(student => (
        <div key={student.studentNo}>
          {/* Props를 Bun으로 넘긴다 */}
          <Bun no={student.studentNo} v={handler} calcGrade={calcGrade} />
        </div>
      ))}
      <Display data={data} />
    </div>
  );
};

export default Ban;
