import React, { useState } from 'react';
import MenuItem from './MenuItem';
import { menuData } from './MenuData';

const Menu: React.FC = () => {
    const [isMenuOpen, setIsMenuOpen] = useState(false);

    const toggleMenu = () => {
        setIsMenuOpen(!isMenuOpen);
    };

    return (
        <div className="relative">
            <button
                onClick={toggleMenu}
                className="bg-yellow-500 text-white px-4 py-2 rounded flex items-center"
            >
                <span className="mr-2">Danh mục</span>
                <span className="material-icons">menu</span>
            </button>
            {isMenuOpen && (
                <div className="absolute top-full mt-2 bg-white w-64 border border-gray-300 rounded shadow-lg">
                    {menuData.map((item, index) =>
                        React.createElement(MenuItem, {
                            key: index,
                            icon: item.icon,
                            label: item.label,
                            submenu: item.submenu
                        })
                    )}
                </div>
            )}
        </div>
    );
};

export default Menu;
