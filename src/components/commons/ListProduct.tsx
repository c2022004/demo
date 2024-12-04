import { useNavigate } from "react-router-dom";
import { Product } from "../../pages/public/Home";
import ShoesItem from './../../components/commons/ShoesItem';
import Button from './../../components/commons/Button';

interface ListProductProps {
  products: Product[];
}

export default function ListProduct({ products }: ListProductProps) {
  const gridColumns = Math.min(4, products.length);
  const navigate = useNavigate();


  const handleItemDetail = (id: string) => {
    navigate(`/chi-tiet-san-pham?id=${id}`);
  };

  const handleAddToCart = (id: string) => {
    console.log(`Thêm sản phẩm ${id} vào giỏ hàng`);
    // Thực hiện logic thêm vào giỏ hàng tại đây
  };

  return (
    <div className={`grid grid-cols-${gridColumns} gap-3`}>
      {products.map((item, index) => {
        const props = {
          id: item.id,
          title: item.name,
          price: item.price,
          description: item.longDescription,
          image: item.images?.[0]?.urlImage || "",
          large: true,
          onclick: () => handleItemDetail(item.id),
        };

        return <ShoesItem {...props} key={index} />;
      })}
    </div>
  );
}
