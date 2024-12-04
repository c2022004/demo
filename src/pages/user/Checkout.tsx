import React, { useState } from "react";

const Checkout: React.FC = () => {
  const [voucherCode, setVoucherCode] = useState("");
  const [selectedVoucher, setSelectedVoucher] = useState("");
  const [paymentMethod, setPaymentMethod] = useState("");

  const handleVoucherApply = () => {
    console.log("Mã giảm giá đã áp dụng:", voucherCode);
  };

  const handleVoucherSelect = (value: string) => {
    setSelectedVoucher(value);
  };

  const handlePaymentMethodChange = (method: string) => {
    setPaymentMethod(method);
  };

  return (
    <div className="container mx-auto p-6">
      <h1 className="text-2xl font-bold mb-6">Thông tin giao hàng</h1>

      {/* Thông tin giao hàng */}
      <div className="mb-8 bg-gray-100 p-6 rounded-lg shadow">
        <p className="text-sm mb-2">
          Bạn đã có tài khoản?{" "}
          <a href="/login" className="text-blue-500">
            Đăng nhập
          </a>
        </p>

        <form className="grid grid-cols-2 gap-4">
          <input
            type="text"
            placeholder="Họ và tên"
            className="border rounded-lg p-2 w-full"
          />
          <input
            type="text"
            placeholder="Số điện thoại"
            className="border rounded-lg p-2 w-full"
          />
          <input
            type="email"
            placeholder="Email"
            className="border rounded-lg p-2 w-full"
          />
          <input
            type="text"
            placeholder="Địa chỉ"
            className="border rounded-lg p-2 w-full col-span-2"
          />
          <select
            className="border rounded-lg p-2 w-full"
            defaultValue="Chọn tỉnh / thành"
          >
            <option disabled>Chọn tỉnh / thành</option>
            <option>Hà Nội</option>
            <option>TP Hồ Chí Minh</option>
          </select>
          <select
            className="border rounded-lg p-2 w-full"
            defaultValue="Chọn quận / huyện"
          >
            <option disabled>Chọn quận / huyện</option>
            <option>Quận 1</option>
            <option>Quận 2</option>
          </select>
          <select
            className="border rounded-lg p-2 w-full col-span-2"
            defaultValue="Chọn phường / xã"
          >
            <option disabled>Chọn phường / xã</option>
            <option>Phường 1</option>
            <option>Phường 2</option>
          </select>
        </form>
      </div>

      {/* Giỏ hàng */}
      <div className="mb-8 bg-gray-100 p-6 rounded-lg shadow">
        <h2 className="text-xl font-bold mb-4">Giỏ hàng</h2>
        <div className="border-b pb-4 mb-4">
          <div className="flex items-center space-x-4">
            <img
              src="https://via.placeholder.com/80"
              alt="Product"
              className="w-20 h-20 object-cover"
            />
            <div>
              <h3 className="font-bold">Giày Thể Thao Nam Bitis</h3>
              <p className="text-sm text-gray-600">Xanh Nhớt / 39</p>
            </div>
            <p className="ml-auto font-bold">854,000 đ</p>
          </div>
        </div>

        <div className="flex items-center justify-between mb-4">
          <select
            className="border rounded-lg p-2"
            defaultValue="Chọn loại voucher"
          >
            <option disabled>Chọn loại voucher</option>
            <option>Khuyến mãi</option>
            <option>Giảm giá</option>
          </select>
          <input
            type="text"
            placeholder="Mã giảm giá"
            value={voucherCode}
            onChange={(e) => setVoucherCode(e.target.value)}
            className="border rounded-lg p-2 w-64"
          />
          <button
            onClick={handleVoucherApply}
            className="bg-gray-800 text-white px-4 py-2 rounded-lg"
          >
            Sử dụng
          </button>
        </div>

        <div className="flex space-x-2">
          {["Giảm 40,000 đ", "Giảm 80,000 đ"].map((item) => (
            <button
              key={item}
              onClick={() => handleVoucherSelect(item)}
              className={`px-4 py-2 rounded-lg border ${
                selectedVoucher === item
                  ? "border-red-500 text-red-500"
                  : "border-gray-300"
              }`}
            >
              {item}
            </button>
          ))}
        </div>

        <div className="mt-6">
          <div className="flex justify-between">
            <span className="text-gray-600">Tạm tính</span>
            <span className="font-bold">854,000 đ</span>
          </div>
          <div className="flex justify-between">
            <span className="text-gray-600">Phí vận chuyển</span>
            <span className="font-bold">0 đ</span>
          </div>
          <div className="flex justify-between border-t pt-4 mt-4">
            <span className="font-bold">Tổng cộng</span>
            <span className="font-bold text-red-500">854,000 đ</span>
          </div>
        </div>
      </div>

      {/* Phương thức thanh toán */}
      <div className="mb-8 bg-gray-100 p-6 rounded-lg shadow">
        <h2 className="text-xl font-bold mb-4">Phương thức thanh toán</h2>
        <div className="space-y-4">
          <label className="flex items-center space-x-4">
            <input
              type="radio"
              name="paymentMethod"
              value="cod"
              onChange={(e) => handlePaymentMethodChange(e.target.value)}
              className="h-5 w-5"
            />
            <span>Thanh toán khi nhận hàng (COD)</span>
          </label>
          <label className="flex items-center space-x-4">
            <input
              type="radio"
              name="paymentMethod"
              value="bank"
              onChange={(e) => handlePaymentMethodChange(e.target.value)}
              className="h-5 w-5"
            />
            <span>Chuyển khoản ngân hàng</span>
          </label>
          <label className="flex items-center space-x-4">
            <input
              type="radio"
              name="paymentMethod"
              value="momo"
              onChange={(e) => handlePaymentMethodChange(e.target.value)}
              className="h-5 w-5"
            />
            <span>Thanh toán qua MoMo</span>
          </label>
        </div>
      </div>

      {/* Nút tiếp tục */}
      <div className="flex justify-end">
        <button className="bg-red-500 text-white px-6 py-3 rounded-lg">
          Thanh toán
        </button>
      </div>
    </div>
  );
};

export default Checkout;
