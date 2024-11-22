import React, { useState } from 'react';
import NavigationMenu from '../commons/NavigationMenu';
import Image from '../commons/Image';
import SearchBar from '../commons/SearchBar';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBell, faCommentDots, faBagShopping, faTable, faUser } from '@fortawesome/free-solid-svg-icons';
import { productCategories } from '../commons/categoriesProduct';
import { userCategories } from '../commons/categoriesUser';

const Header: React.FC = () => {
  const [isCategoryMenuOpen, setIsCategoryMenuOpen] = useState(false);
  const [isAccountMenuOpen, setIsAccountMenuOpen] = useState(false);

  return (
    <header className="bg-white shadow-md py-2">
      <div className="container mx-auto flex items-center justify-between px-4">
        {/* Logo */}
        <div className="flex items-center space-x-4">
          <Image alt="Chợ Tốt Logo" />
        </div>

        {/* Navigation Menu */}
        <div className="relative">
          <button
            className="flex items-center font-bold text-blue-500"
            onClick={() => setIsCategoryMenuOpen(!isCategoryMenuOpen)}
          >
            Danh mục
          </button>
          {isCategoryMenuOpen && (
            <div className="absolute top-full left-0 bg-white shadow-md mt-2 w-48">
              <NavigationMenu categories={productCategories} />
            </div>
          )}
        </div>

        {/* Search Bar */}
        <div className="flex-1 mx-4">
          <SearchBar
            placeholder="Tìm kiếm sản phẩm trên Chợ Tốt"
            onSearch={(query) => console.log('Từ khóa tìm kiếm:', query)}
          />
        </div>

        {/* Icons */}
        <div className="flex items-center space-x-4">
          <button
            className="p-2 bg-gray-100 rounded-full hover:bg-gray-200 focus:outline-none focus:ring-2 focus:ring-blue-500"
            aria-label="Thông báo"
          >
            <FontAwesomeIcon icon={faBell} className="w-5 h-5 text-gray-700" />
          </button>

          <button
            className="p-2 bg-gray-100 rounded-full hover:bg-gray-200 focus:outline-none focus:ring-2 focus:ring-blue-500"
            aria-label="Tin nhắn"
          >
            <FontAwesomeIcon icon={faCommentDots} className="w-5 h-5 text-gray-700" />
          </button>

          <button
            className="p-2 bg-gray-100 rounded-full hover:bg-gray-200 focus:outline-none focus:ring-2 focus:ring-blue-500"
            aria-label="Giỏ hàng"
          >
            <FontAwesomeIcon icon={faBagShopping} className="w-5 h-5 text-gray-700" />
          </button>

          <button
            className="p-2 bg-gray-100 rounded-full hover:bg-gray-200 focus:outline-none focus:ring-2 focus:ring-blue-500"
            aria-label="Quản lý"
          >
            <FontAwesomeIcon icon={faTable} className="w-5 h-5 text-gray-700" />
          </button>
        </div>

        {/* Account Menu and Post Button */}
        <div className="relative flex items-center space-x-4">
          <div className="relative">
            <button
              className="flex items-center px-4 py-2 border rounded-md hover:bg-gray-100"
              onClick={() => setIsAccountMenuOpen(!isAccountMenuOpen)}
            >
              <FontAwesomeIcon icon={faUser} className="w-5 h-5 text-gray-700" />
              <span className="ml-2 text-gray-700">Tài khoản</span>
            </button>
            {isAccountMenuOpen && (
              <div className="absolute top-full right-0 bg-white shadow-md mt-2 w-48">
                <ul>
                  {userCategories.map((item) => (
                    <li
                      key={item.name}
                      className="px-4 py-2 hover:bg-gray-100 cursor-pointer"
                    >
                      <a href={item.href}>{item.name}</a>
                    </li>
                  ))}
                </ul>
              </div>
            )}
          </div>

          <button className="ml-4 bg-orange-500 text-white px-4 py-2 rounded hover:bg-orange-600">
            ĐĂNG TIN
          </button>
        </div>
      </div>
    </header>
  );
};

export default Header;
