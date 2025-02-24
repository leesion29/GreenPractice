import React from 'react'
import JuhunRed from './JuhunRed'

const Juhun = () => {
    
  return (
    <div>
        {[
            (i) => alert(i+"사랑"),
            (i) => alert(i+"믿음"),
            (i) => alert(i+"증오"),
            (i) => alert(i+"갈증"),
            (i) => alert(i+"열망"),
            
        ].map((i, idx)=>(
        
        <JuhunRed key={idx} vv = {i}/>))}
    </div>
  )
}

export default Juhun