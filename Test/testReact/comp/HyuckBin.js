import React from 'react'

const HyuckBin = ({fu, vv}) => {
    const click = () => {
        alert("결과: " + fu(34) + vv(23, {"a": [1, 2, 3, 4]})); //3727
    }
  return (
    <div>
        <button onClick={click}>눌러라</button>
    </div>
  )
}

export default HyuckBin