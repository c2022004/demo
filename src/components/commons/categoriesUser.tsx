export const userCategories = [
    {
      name: 'Đơn mua',
      href: '/don-mua',
      icon: '🛒',
    },
    {
      name: 'Đơn bán',
      href: '/don-ban',
      icon: '📦',
    },
    {
      name: 'Tiện ích',
      subcategories: [
        { name: 'Tin đăng đã lưu', href: '/tin-dang-luu' },
        { name: 'Tìm kiếm đã lưu', href: '/tim-kiem-luu' },
        { name: 'Đánh giá từ tôi', href: '/danh-gia' },
      ],
      icon: '❤️',
    },
    {
      name: 'Dịch vụ trả phí',
      subcategories: [
        { name: 'Đồng Tốt', href: '/dong-tot' },
        { name: 'Gói PRO', href: '/goi-pro' },
        { name: 'Lịch sử giao dịch', href: '/lich-su-giao-dich' },
      ],
      icon: '💰',
    },
  ];
  