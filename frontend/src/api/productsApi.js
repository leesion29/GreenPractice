import axios from "axios";

export const API_SERVER_HOST = "http://localhost:8080";
const prefix = `${API_SERVER_HOST}/api/products`;
//p253
export const postAdd = async (productObj) => {
  const header = { Headers: { "Content-Type": "multipart/form-data" } };
  const res = await axios.post(`${prefix}/`, productObj, header);
  return res.data;
};

export const getList = async (pageParam) => {
  console.log(pageParam);
  const { page, size } = pageParam;
  const res = await axios.get(`${prefix}/list`, { params: { page, size } });
  return res.data;
};

export const getOne = async (pno) => {
  const res = await axios.get(`${prefix}/${pno}`);
  return res.data;
};

export const deletOne = async (pno) => {
  const res = await axios.delete(`${prefix}/${pno}`);
  return res.data;
};

export const putOne = async (pno, product) => {
  console.log("putOne api product:", product);
  const header = { headers: { "Content-Type": "multipart/form-data" } };
  const res = await axios.put(`${prefix}/${pno}`, product, header);
  return res.data;
};
