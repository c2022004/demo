import React, { useEffect, useState } from "react";
import ListProduct from "../../components/commons/ListProduct";
import LoadingSpinner from "../../components/commons/LoadingSpinner";
import ErrorAlert from "../../components/commons/ErrorAlert";
import Banner from "../../components/commons/Banner";
import Slideshow from "../../components/commons/Slideshow";
import CategoryMenu from "../../components/commons/CategoryMenu";
import { featuredProducts } from "../../data/product";
import { findAllProduct } from "../../apis/productAPI"; // Sử dụng phương thức đúng

export interface Product {
  id: string;
  name: string;
  price: number;
  images: { urlImage: string }[];
  shortDescription: string;
  longDescription: string;
}

const Home: React.FC = () => {
  const [products, setProducts] = useState<Product[]>([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchProductData = async () => {
      try {
        setIsLoading(true);
        const data = await findAllProduct(); // Sử dụng phương thức chính xác
        setProducts(data);
      } catch (error) {
        console.error("API Error:", error);
        setError("Không thể tải danh sách sản phẩm. Hiển thị dữ liệu tạm.");
        setProducts(featuredProducts); // Hiển thị dữ liệu tạm nếu API lỗi
      } finally {
        setIsLoading(false);
      }
    };

    fetchProductData();
  }, []);

  if (isLoading) {
    return <LoadingSpinner message="Đang tải danh sách sản phẩm..." />;
  }

  if (error) {
    return <ErrorAlert message={error} />;
  }

  return (
    <div className="container mx-auto my-8">
      <Banner />
      <Slideshow
        slides={featuredProducts.map((product, index) => ({
          id: index,
          imageUrl: product.images[0]?.urlImage || "",
          title: product.name,
        }))}
      />
      <CategoryMenu />
      <h1 className="text-3xl font-bold text-center mb-6">Danh Sách Sản Phẩm</h1>
      <ListProduct products={products} />
    </div>
  );
};

export default Home;
