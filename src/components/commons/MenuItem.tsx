import React, { useState } from 'react';

type MenuItemProps = {
    icon: JSX.Element;
    label: string;
    submenu?: string[];
};

const MenuItem: React.FC<MenuItemProps> = ({ icon, label, submenu }) => {
    const [isSubmenuOpen, setIsSubmenuOpen] = useState(false);

    const toggleSubmenu = () => {
        setIsSubmenuOpen(!isSubmenuOpen);
    };

    return (
        <div className="relative">
            <div
                className="flex items-center justify-between p-2 hover:bg-gray-100 cursor-pointer border-b"
                onClick={submenu ? toggleSubmenu : undefined}
            >
                <div className="flex items-center">
                    <div className="mr-2">{icon}</div>
                    <span className="text-gray-700">{label}</span>
                </div>
                {submenu && <div className="text-gray-400">{'>'}</div>}
            </div>
            {isSubmenuOpen && submenu && (
                <div className="absolute left-full top-0 bg-white border border-gray-300 shadow-lg w-48">
                    {submenu.map((item, index) => (
                        <div key={index} className="p-2 hover:bg-gray-100">
                            {item}
                        </div>
                    ))}
                </div>
            )}
        </div>
    );
};

export default MenuItem;
