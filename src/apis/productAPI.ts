import axios from "axios";

const BASE_URL = "http://localhost:8088/skeleton"; // Đổi theo cấu hình backend của bạn

export const fetchProducts = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/api/v1/products`);
    return response.data;
  } catch (error) {
    console.error("Error fetching products:", error);
    throw error;
  }
};
