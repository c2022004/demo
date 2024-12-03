import React, { useEffect, useState } from "react";
import ListProduct from "../../components/commons/ListProduct";
import Slideshow from "../../components/commons/Slideshow";
import Banner from "../../components/commons/Banner";
import Footer from "../../components/commons/Footer";
import CategoryMenu from "../../components/commons/CategoryMenu";
import { findAllProduct } from "../../apis/productAPI"; // Giữ nguyên API
import { featuredProducts } from "../../data/product";

export interface ImageData {
  urlImage: string;
}

export interface Product {
  id: string;
  images: ImageData[]; // Sử dụng mảng ImageData thay vì string[]
  name: string;
  price: number;
  shortDescription: string;
  longDescription: string;
}

function Home() {
  const [product, setProduct] = useState<Product[]>([]);

  useEffect(() => {
    const fetchAllProduct = async () => {
      try {
        const response = await findAllProduct();
        // Nếu API trả về dữ liệu, sử dụng dữ liệu từ API
        if (response.data && response.data.length > 0) {
          setProduct(response.data);
        } else {
          // Nếu không có dữ liệu từ API, sử dụng dữ liệu tạm
          console.warn("API không trả về dữ liệu, sử dụng dữ liệu tạm.");
          setProduct(featuredProducts);
        }
      } catch (error) {
        console.error("Lỗi khi gọi API:", error);
        // Trong trường hợp lỗi, sử dụng dữ liệu tạm
        setProduct(featuredProducts);
      }
    };
    fetchAllProduct();
  }, []);

  const slides = [
    { id: 1, imageUrl: "/src/assets/img/giay1.jpg", title: "Slide 1" },
    { id: 2, imageUrl: "/src/assets/img/giay2.jpg", title: "Slide 2" },
    { id: 3, imageUrl: "/src/assets/img/giay4.jpg", title: "Slide 3" },
  ];

  return (
    <>
        {/* Banner */}
        <div className="banner-section">
          <Banner />
        </div>

        {/* Slideshow */}
        <div className="slideshow-section my-8">
          <Slideshow slides={slides} />
        </div>

        {/* Menu Danh Mục */}
        <CategoryMenu />

        {/* Sản phẩm nổi bật */}
        <div className="featured-products-section container mx-auto my-12">
          <h2 className="text-center text-2xl font-bold mb-6">Sản Phẩm Nổi Bật</h2>
          <ListProduct products={product} />
        </div>
    </>
  );
}

export default Home;
