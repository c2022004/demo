import React, { useEffect, useState } from "react";
import { fetchProducts } from "../../apis/productAPI";
import ListProduct from "../../components/commons/ListProduct";
import LoadingSpinner from "../../components/commons/LoadingSpinner";
import ErrorAlert from "../../components/commons/ErrorAlert";
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
  const [error, setError] =useState<string | null>(null);

  useEffect(() => {
    const fetchProductsList = async () => {
      try {
        setIsLoading(true);
        const data = await fetchProducts();
        if (data && data.length > 0) {
          setProducts(data);
        } else {
          setProducts(featuredProducts); // Sử dụng dữ liệu tạm
        }
      } catch (err) {
        setError("Không thể tải danh sách sản phẩm. Hiển thị dữ liệu tạm.");
        setProducts(featuredProducts); // Sử dụng dữ liệu tạm
      } finally {
        setIsLoading(false);
      }
    };

    fetchProductsList();
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
