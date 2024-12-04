import React, { useState } from "react";
import { useNavigate } from "react-router-dom"; // Import useNavigate
import Button from "../../components/commons/Button"; // Import Button component

const Checkout: React.FC = () => {
  const navigate = useNavigate(); // Sử dụng navigate để chuyển trang
  const [voucherCode, setVoucherCode] = useState("");
  const [selectedVoucher, setSelectedVoucher] = useState("");
  const [paymentMethod, setPaymentMethod] = useState<string | null>(null);

  const handleVoucherApply = () => {
    console.log("Mã giảm giá đã áp dụng:", voucherCode);
  };

  const handleVoucherSelect = (value: string) => {
    setSelectedVoucher(value);
  };

  const handlePaymentMethodChange = (method: string) => {
    setPaymentMethod(method);
  };

  const handleConfirmOrder = () => {
    if (paymentMethod === "cod") {
      alert("Đặt hàng thành công!");
      navigate("/"); // Chuyển về trang Home
    } else if (paymentMethod === "bank") {
      alert("Vui lòng quét mã QR ngân hàng để thanh toán.");
    } else if (paymentMethod === "momo") {
      alert("Vui lòng quét mã QR MoMo để thanh toán.");
    } else {
      alert("Vui lòng chọn phương thức thanh toán!");
    }
  };

  const handleBackToCart = () => {
    navigate("/gio-hang"); // Chuyển về trang Giỏ hàng
  };

  return (
    <div className="container mx-auto p-6">
      

      {/* Thông tin giao hàng */}
      <div className="mb-8 bg-gray-100 p-6 rounded-lg shadow">
        <h1 className="text-2xl font-bold mb-6">Thông tin giao hàng</h1>
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

      {/* Nút hành động */}
      <div className="flex justify-between">
        <Button primary onClick={handleBackToCart}>
          Quay lại giỏ hàng
        </Button>
        <Button primary onClick={handleConfirmOrder}>
          Xác nhận thanh toán
        </Button>
      </div>

      {/* Hiển thị hình ảnh mã QR nếu cần */}
      {paymentMethod === "bank" && (
        <div className="mt-6">
          <h3 className="text-lg font-bold mb-2">Mã QR Ngân hàng</h3>
          <img
            src="/src/assets/img/bank-qr.png"
            alt="Bank QR Code"
            className="w-64 h-64 mx-auto"
          />
        </div>
      )}
      {paymentMethod === "momo" && (
        <div className="mt-6">
          <h3 className="text-lg font-bold mb-2">Mã QR MoMo</h3>
          <img
            src="/src/assets/img/momo-qr.png"
            alt="MoMo QR Code"
            className="w-64 h-64 mx-auto"
          />
        </div>
      )}
    </div>
  );
};

export default Checkout;
