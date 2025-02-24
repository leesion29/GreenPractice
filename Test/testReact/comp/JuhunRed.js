import React from 'react'

const JuhunRed = ({vv, idx}) => {
  console.log(vv);
  const { fn } = vv;
  function f(){
    //{vv}.vv(1)
    return "";
  }
  return (
    <div>
      <button onClick={()=>fn("갈증")}>눌러라, {idx}</button>
    </div>
  )
}

export default JuhunRed