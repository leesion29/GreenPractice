import React, { useState } from 'react'

const Bun = () => {
const [score, setScore] = 
useState(
    {
        kor: 0,
        eng: 0,
        math: 0
    }
)
const onChangeHandler = (e) =>
    setScore({...score, [e.target.name]: e.target.value});

const total = (a, b, c) =>{
    let k = parseInt(a);
    let e = parseInt(b);
    let m = parseInt(c);

    return k+e+m;
}

const avg = (a, b, c) => {
    let res = total(a,b,c)/3
    return Math.round(res);
}

const grade = (a, b, c) => {
    let gr;
    let res = avg(a, b, c);
    if (res >= 90){gr = "상"}
    else if (res >= 60){gr = "중"}
    else {gr="하"}
    return gr;
}

let data =[];
const save = () => {
    let sno = score.sno-1;
    data[sno] = [score.sno+"번 학생", score.kor, score.eng, score.math, totals, avgs, grades];
    console.log(data);
}

let totals = total(score.kor, score.eng, score.math);
let avgs = avg(score.kor, score.eng, score.math);
let grades =grade(score.kor, score.eng, score.math);
  return (
    
    <div>
        <div className = "p-9 ml-7 mr-56 " style={{border: "solid 1px grey"}}>
        <p className="text-2xl">No : </p> <input style={{border: "solid 1px grey"}} type="text" value={score.sno} onChange={onChangeHandler} name="sno"/><br/><br/><br/>
            국어 : &nbsp;
            <input style={{border: "solid 1px grey"}} type="text" value={score.kor} onChange={onChangeHandler} name="kor"/><br/><br/>
            영어 : &nbsp;
            <input style={{border: "solid 1px grey"}} type="text" value={score.eng} onChange={onChangeHandler} name="eng"/><br/><br/>
            수학 : &nbsp;
            <input style={{border: "solid 1px grey"}} type="text" value={score.math} onChange={onChangeHandler} name="math"/><br/><br/><br/><br/>
            총점 : &nbsp;
            <input style={{border: "solid 1px grey"}} readOnly value={totals} onChange={onChangeHandler} name="total"/><br/><br/>
            평균 : &nbsp;
            <input style={{border: "solid 1px grey"}} readOnly value={avgs} onChange={onChangeHandler} name="avg"/><br/><br/>
            등급 : &nbsp;
            <input style={{border: "solid 1px grey"}} readOnly value={grades} onChange={onChangeHandler} name="grade"/><br/><br/>
        <button className="bg-gray-200" onClick={save}>제출</button>
        </div>
    </div>
  )
}

export default Bun