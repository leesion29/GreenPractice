import React from "react";
import JuhunRed from "./JuhunRed";

const Yunjae = () => {
  return (
    <div>
      {[
        { fn: (i) => console.log(i + "사랑") },
        { fn: (i) => console.log(i + "믿음") },
        { fn: (i) => console.log(i + "증오") },
        { fn: (i) => console.log(i + "갈증") },
        { fn: (i) => console.log(i + "열망") },
      ].map((i, idx) => (
        <JuhunRed key={idx} vv={i} idx={idx} />
      ))}
    </div>
  );
};

export default Yunjae;
