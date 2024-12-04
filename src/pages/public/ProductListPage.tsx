import React, { useEffect, useState } from "react";
import { fetchProducts } from "../../apis/productAPI"; // Không thay đổi
import ListProduct from "../../components/commons/ListProduct";
import LoadingSpinner from "../../components/commons/LoadingSpinner"; // Component loading
import ErrorAlert from "../../components/commons/ErrorAlert"; // Component lỗi

// Dữ liệu tạm được khai báo trong cùng file
const temporaryProducts = [
  {
    id: "1",
    name: "Giày Thể Thao Nam Bitis Hunter",
    price: 854000,
    images: [{ urlImage: "/src/assets/img/shoe1.jpg" }],
    shortDescription: "Giày thể thao cao cấp.",
    longDescription: "Giày thể thao phù hợp cho mọi lứa tuổi.",
  },
  {
    id: "2",
    name: "Giày Thể Thao Nữ Bitis Hunter",
    price: 754000,
    images: [{ urlImage: "/src/assets/img/shoe2.jpg" }],
    shortDescription: "Giày thể thao nữ hiện đại.",
    longDescription: "Giày thể thao nữ với thiết kế trẻ trung.",
  },
];

export interface Product {
  id: string;
  name: string;
  price: number;
  images: { urlImage: string }[];
  shortDescription: string;
  longDescription: string;
}

const ProductListPage: React.FC = () => {
  const [products, setProducts] = useState<Product[]>([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        setIsLoading(true);
        const data = await fetchProducts(); // Gọi API từ productAPI.ts
        setProducts(data); // Cập nhật danh sách sản phẩm nếu API thành công
      } catch (error) {
        console.error("Lỗi khi gọi API:", error);
        setError("Không thể tải danh sách sản phẩm. Hiển thị dữ liệu tạm.");
        setProducts(temporaryProducts); // Sử dụng dữ liệu tạm nếu gặp lỗi
      } finally {
        setIsLoading(false);
      }
    };

    fetchData();
  }, []);

  if (isLoading) {
    return <LoadingSpinner message="Đang tải danh sách sản phẩm..." />;
  }

  if (error) {
    return <ErrorAlert message={error} />;
  }

  return (
    <div className="container mx-auto my-8">
      <h1 className="text-3xl font-bold text-center mb-6">Danh Sách Sản Phẩm</h1>
      <ListProduct products={products} />
    </div>
  );
};

export default ProductListPage;
