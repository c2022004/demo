import { FaHome, FaCar, FaLaptop, FaBriefcase, FaDog, FaUtensils, FaSnowflake, FaCouch, FaBaby, FaTshirt, FaGamepad, FaTools, FaPlane } from 'react-icons/fa';
import React from 'react';

export const menuData = [
    { icon: React.createElement(FaHome), label: 'Bất động sản' },
    { icon: React.createElement(FaCar), label: 'Xe cộ' },
    { icon: React.createElement(FaLaptop), label: 'Đồ điện tử' },
    {
        icon: React.createElement(FaBriefcase), label: 'Việc làm', submenu: [
            'Bán hàng',
            'Nhân viên phục vụ',
            'Tài xế giao hàng xe máy',
            'Tạp vụ',
            'Pha chế',
            'Phụ bếp',
            'Nhân viên kinh doanh',
            'Công nhân',
            'Nhân viên kho vận',
            'Bảo vệ',
            'Chăm sóc khách hàng',
            'Công việc khác'
        ]
    },
    { icon: React.createElement(FaDog), label: 'Thú cưng' },
    { icon: React.createElement(FaUtensils), label: 'Đồ ăn, thực phẩm, và các loại khác' },
    { icon: React.createElement(FaSnowflake), label: 'Tủ lạnh, máy lạnh, máy giặt' },
    { icon: React.createElement(FaCouch), label: 'Đồ gia dụng, nội thất, cây cảnh' },
    { icon: React.createElement(FaBaby), label: 'Mẹ và bé' },
    { icon: React.createElement(FaTshirt), label: 'Thời trang, đồ dùng cá nhân' },
    { icon: React.createElement(FaGamepad), label: 'Giải trí, Thể thao, Sở thích' },
    { icon: React.createElement(FaTools), label: 'Đồ dùng văn phòng, công nông nghiệp' },
    { icon: React.createElement(FaPlane), label: 'Dịch vụ, du lịch' }
];

