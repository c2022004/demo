import React from "react";

interface Category {
  id: number;
  name: string;
  iconUrl: string; // Đường dẫn icon hoặc ảnh
}

const categories: Category[] = [
  { id: 1, name: "Nike", iconUrl: "/src/assets/img/nike-icon.png" },
  { id: 2, name: "Adidas", iconUrl: "/src/assets/img/adidas-icon.png" },
  { id: 3, name: "Puma", iconUrl: "/src/assets/img/puma-icon.png" },
  { id: 4, name: "Converse", iconUrl: "/src/assets/img/converse-icon.png" },
  { id: 5, name: "New Balance", iconUrl: "/src/assets/img/new-balance-icon.png" },
];

const CategoryMenu: React.FC = () => {
  return (
    <div className="categories-menu-section container mx-auto my-6">
      <h3 className="text-center text-xl font-semibold mb-4">Danh Mục</h3>
      <div className="flex justify-center gap-6 flex-wrap">
        {categories.map((category) => (
          <div
            key={category.id}
            className="flex flex-col items-center w-24 hover:scale-105 transition"
          >
            {/* Icon hoặc ảnh */}
            <img
              src={category.iconUrl}
              alt={category.name}
              className="w-16 h-16 mb-2"
            />
            {/* Tên danh mục */}
            <span className="text-gray-700 text-sm font-medium">{category.name}</span>
          </div>
        ))}
      </div>
    </div>
  );
};

export default CategoryMenu;
