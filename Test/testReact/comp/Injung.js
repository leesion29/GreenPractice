import React, { useState } from "react";

const Injung = () => {
    const [cnt, setCnt] = useState(10);
    const increase = () => setCnt(cnt + 1);
    const decrease = () => setCnt(cnt - 1);

  return (
    <div>
      <button onClick={increase} className="bg-orange-400 text-3xl">+</button>&nbsp;
      <button onClick={decrease} className="bg-blue-400 text-3xl">-</button>
      <div className="text-4xl">{cnt}</div>
    </div>
  );
};

export default Injung;
