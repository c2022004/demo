import React, { useEffect, useState } from "react";
import ListProduct from "../../components/commons/ListProduct";
import { fetchProducts } from "../../apis/productAPI"; // Gọi API
import LoadingSpinner from "../../components/commons/LoadingSpinner";
import ErrorAlert from "../../components/commons/ErrorAlert";
import Banner from "../../components/commons/Banner";
import Slideshow from "../../components/commons/Slideshow";
import CategoryMenu from "../../components/commons/CategoryMenu";
import { featuredProducts } from "../../data/product"; // Import dữ liệu tạm

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
    const fetchData = async () => {
      try {
        setIsLoading(true);
        const data = await fetchProducts();
        if (data && data.length > 0) {
          setProducts(data);
        } else {
          console.warn("API không trả về dữ liệu. Sử dụng dữ liệu tạm.");
          setProducts(featuredProducts); // Dữ liệu tạm
        }
      } catch (error) {
        console.error("Không thể kết nối tới API. Hiển thị dữ liệu tạm.");
        setError("Không thể tải danh sách sản phẩm. Hiển thị dữ liệu tạm.");
        setProducts(featuredProducts); // Sử dụng dữ liệu tạm khi lỗi
      } finally {
        setIsLoading(false);
      }
    };

    fetchData();
  }, []);

  if (isLoading) {
    return <LoadingSpinner message="Đang tải nội dung..." />;
  }

  return (
    <div className="container mx-auto my-8">
      {/* Banner */}
      <Banner />

      {/* Slideshow */}
      <Slideshow
        slides={[
          { id: 1, imageUrl: "/assets/slide1.jpg", title: "Giảm giá sốc!" },
          { id: 2, imageUrl: "/assets/slide2.jpg", title: "Mua 1 tặng 1" },
          { id: 3, imageUrl: "/assets/slide3.jpg", title: "Hàng mới về" },
        ]}
      />

      {/* Category Menu */}
      <CategoryMenu />

      {/* Danh sách sản phẩm */}
      <section className="mt-8">
        <h1 className="text-3xl font-bold text-center mb-6">
          Sản phẩm nổi bật
        </h1>
        <ListProduct products={products} />
      </section>
    </div>
  );
};

export default Home;
