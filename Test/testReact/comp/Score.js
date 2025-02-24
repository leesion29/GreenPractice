import React, { useState } from 'react'

const Score = () => {
    const [a, setA] = useState([
        {'no':1,math:30,eng:50},
        {'no':2,math:35,eng:70},
        {'no':3,math:37,eng:100},
        {'no':4,math:80,eng:90},
    ]);
    // v 객체를 추가(데이터 갱신)
    const update = () => {
        setA( j => j.map(i=>{
            if(i.no===2){ //  객체 i의 no가 2라면 {'no':2,math:35,eng:70}
                console.log("확인 => " , i); // i 출력
                var tt = {...i, ...{'v': 77}} // 기존 객체에 v라는 객체를 추가한 것을 변수 tt에 저장
            } else { // 그렇디 않다면
                tt = i; // 값을 바꾸지 않는다
            }
            return tt; // 변수 tt를 리턴한다.
        })
    )
 }
  return (
    <div>
        <button onClick={update} className='bg-blue-400'>변경</button>
        {a. map((i) => (
            <div key={i.no}>
            {i.no},{i.math},{i.v}
        </div>
        ))}
    </div>
  )
}

export default Score