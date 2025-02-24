import React from "react";

const Display = ({ data }) => {
    return (
        <div>
            <hr style={{ borderTop: "3px solid red" }} />
            <h2>학생 성적</h2>
            {data.map((scoreData, index) => (
                <div
                    key={scoreData.studentNo || index}
                    style={{ border: "1px solid #ccc", margin: "10px", padding: "10px" }}
                >
                    <h3>{scoreData.studentNo} 번 학생</h3>
                    <p>국어: {scoreData.korea || 0}</p>
                    <p>수학: {scoreData.math || 0}</p>
                    <p>영어: {scoreData.eng || 0}</p>
                    <p>총점: {scoreData.total || 0}</p>
                    <p>평균: {scoreData.avg || 0}</p>
                    <p>등급: {scoreData.grade || ""}</p>
                </div>
            ))}
        </div>
    );
};

export default Display;
