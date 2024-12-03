import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { fetchProductById } from "../../apis/productAPI";
import LoadingSpinner from "../../components/commons/LoadingSpinner";
import ErrorAlert from "../../components/commons/ErrorAlert";

interface Product {
  id: string;
  name: string;
  description: string;
  price: number;
  images: { urlImage: string }[];
}

const ProductDetailPage: React.FC = () => {
  const { id } = useParams<{ id: string }>();
  const [product, setProduct] = useState<Product | null>(null);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchProduct = async () => {
      try {
        setIsLoading(true);
        const data = await fetchProductById(id!);
        setProduct(data);
      } catch (err) {
        setError("Không thể tải chi tiết sản phẩm. Vui lòng thử lại.");
      } finally {
        setIsLoading(false);
      }
    };

    fetchProduct();
  }, [id]);

  if (isLoading) {
    return <LoadingSpinner message="Đang tải chi tiết sản phẩm..." />;
  }

  if (error) {
    return <ErrorAlert message={error} />;
  }

  return (
    <div className="container mx-auto my-8">
      <h1 className="text-3xl font-bold text-center mb-6">{product?.name}</h1>
      <p className="text-center">{product?.description}</p>
    </div>
  );
};

export default ProductDetailPage;
