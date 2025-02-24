import React from "react";

const FetchingModal = () => {
  return (
    <div className={`fixed top-0 flex h-full z-[1055] place-items-center bg-black bg-opacity-20`}>
      <div
        className={`bg-white rounded-3xl opacity-100 min-w-min h-1/4 min-w-[600px] flex justify-center items-center`}>
        <div className="text-4xl font-extrabold text-orange-500 m-20">
          Loading...
        </div>
      </div>
    </div>
  );
};

export default FetchingModal;
