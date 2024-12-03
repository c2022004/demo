import React, { useEffect, useState } from "react";
import { findAllProduct } from "../../apis/productAPI";
import ListProduct from "../../components/commons/ListProduct";
import LoadingSpinner from "../../components/commons/LoadingSpinner"; // Component loading
import ErrorAlert from "../../components/commons/ErrorAlert"; // Component lỗi
import { featuredProducts } from "../../data/product"; // Import dữ liệu tạm

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
  const [isLoading, setIsLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        setIsLoading(true);
        const response = await findAllProduct();
        const data = response || []; // Đảm bảo dữ liệu không bị null

        if (data.length > 0) {
          setProducts(data);
        } else {
          console.warn("API không trả về dữ liệu. Hiển thị dữ liệu tạm.");
          setProducts(featuredProducts); // Fallback dữ liệu tạm
        }
      } catch (err) {
        console.error("Lỗi khi gọi API:", err);
        setError("Không thể tải danh sách sản phẩm. Hiển thị dữ liệu tạm.");
        setProducts(featuredProducts); // Fallback dữ liệu tạm trong trường hợp lỗi
      } finally {
        setIsLoading(false);
      }
    };

    fetchProducts();
  }, []);

  // Xử lý trạng thái tải dữ liệu
  if (isLoading) {
    return <LoadingSpinner message="Đang tải danh sách sản phẩm..." />;
  }

  // Xử lý lỗi
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
