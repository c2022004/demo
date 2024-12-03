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
    <div className={`grid grid-cols-${gridColumns} gap-4`}>
      {products.map((item) => {
        const props = {
          key: item.id,
          id: item.id,
          title: item.name,
          price: item.price,
          description: item.longDescription,
          image: item.images?.[0]?.urlImage || "",
          large: true,
          onclick: () => handleItemDetail(item.id),
        };

        return (
          <div key={item.id} className="border p-4 rounded-lg shadow">
            <ShoesItem {...props} />
            <div className="flex justify-between mt-4">
              <Button primary onClick={() => handleAddToCart(item.id)}>
                Thêm vào giỏ hàng
              </Button>
              <Button outline onClick={() => handleItemDetail(item.id)}>
                Xem chi tiết
              </Button>
            </div>
          </div>
        );
      })}
    </div>
  );
}
