import React from "react";
import bannerImage from "/src/assets/img/giay5.jpg"; // Đảm bảo đường dẫn ảnh chính xác

interface BannerProps {
  title?: string;
  subtitle?: string;
  imageUrl?: string;
}

const Banner: React.FC<BannerProps> = ({
  title = "Welcome to King Of Shoes",
  subtitle = "Find your perfect style today!",
  imageUrl = bannerImage, // Sử dụng ảnh mặc định
}) => {
  return (
    <div
      className="banner flex items-center justify-center bg-cover bg-center h-[300px] md:h-[400px] lg:h-[500px] text-white"
      style={{
        backgroundImage: `url(${imageUrl})`,
      }}
    >
      <div className="text-center bg-black bg-opacity-50 p-5 rounded-lg">
        <h1 className="text-3xl md:text-5xl font-bold">{title}</h1>
        <p className="text-lg md:text-xl mt-2">{subtitle}</p>
      </div>
    </div>
  );
};

export default Banner;
