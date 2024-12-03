import React from "react";
import bannerImage from "/src/assets/img/giay5.jpg"; // Đảm bảo đường dẫn chính xác

interface BannerProps {
  title?: string;
  subtitle?: string;
  imageUrl?: string;
}

const Banner: React.FC<BannerProps> = ({
  title = "Welcome to King Of Shoes",
  subtitle = "Find your perfect style today!",
  imageUrl = bannerImage, // Sử dụng ảnh nhập
}) => {
  return (
    <div
      className="banner flex items-center justify-center bg-cover bg-center h-64 text-white"
      style={{
        backgroundImage: `url(${imageUrl})`,
      }}
    >
      <div className="text-center bg-black bg-opacity-50 p-5 rounded">
        <h1 className="text-3xl font-bold">{title}</h1>
        <p className="text-lg mt-2">{subtitle}</p>
      </div>
    </div>
  );
};

export default Banner;
