import React from "react";
import { useNavigate } from "react-router-dom";
import { productCategories } from "../../data/categories";

const CategoryMenu: React.FC = () => {
  const navigate = useNavigate();

  const handleCategoryClick = (categoryId: string) => {
    navigate(`/danh-sach-san-pham?category=${categoryId}`);
  };

  return (
    <div className="categories-menu container mx-auto my-8">
      <h3 className="text-center text-2xl font-bold mb-6">Danh Mục Sản Phẩm</h3>
      <div className="grid grid-cols-2 md:grid-cols-4 lg:grid-cols-6 gap-4">
        {productCategories.map((category) => (
          <button
            key={category.name}
            className="flex flex-col items-center bg-gray-100 p-4 rounded-lg shadow hover:bg-gray-200 transition"
            onClick={() => handleCategoryClick(category.name)}
          >
            <span className="text-3xl mb-2">{category.icon}</span>
            <span className="font-medium">{category.name}</span>
          </button>
        ))}
      </div>
    </div>
  );
};

export default CategoryMenu;
