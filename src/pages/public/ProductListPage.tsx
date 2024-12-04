import React, { useEffect, useState } from "react";
import ListProduct from "../../components/commons/ListProduct";
import LoadingSpinner from "../../components/commons/LoadingSpinner";
import ErrorAlert from "../../components/commons/ErrorAlert";

// Dữ liệu tạm
const temporaryProducts = [
  {
    id: "1",
    name: "Giày Thể Thao Nam Biti's Hunter",
    price: 854000,
    images: [{ urlImage: "/src/assets/img/shoe1.jpg" }],
    shortDescription: "Giày thể thao cao cấp.",
    longDescription: "Giày thể thao phù hợp cho mọi lứa tuổi.",
  },
  {
    id: "2",
    name: "Giày Nike Air Max",
    price: 1150000,
    images: [{ urlImage: "/src/assets/img/shoe2.jpg" }],
    shortDescription: "Thoải mái và thời trang.",
    longDescription: "Dành cho các hoạt động thể thao chuyên nghiệp.",
  },
];

const ProductListPage: React.FC = () => {
  const [products, setProducts] = useState(temporaryProducts);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  // Không gọi API và luôn sử dụng dữ liệu tạm
  useEffect(() => {
    setProducts(temporaryProducts);
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
