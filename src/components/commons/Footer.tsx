import React from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faFacebook,
  faYoutube,
  faLinkedin,
} from "@fortawesome/free-brands-svg-icons";

const Footer: React.FC = () => {
  return (
    <footer className="bg-gray-100 py-10">
      <div className="container mx-auto grid grid-cols-3 gap-8">
        {/* Cột 1: Hỗ trợ khách hàng */}
        <div>
          <h3 className="font-bold text-lg mb-4">HỖ TRỢ KHÁCH HÀNG</h3>
          <ul>
            <li className="mb-2">
              <a href="#" className="text-gray-600 hover:text-gray-800">
                Trung tâm trợ giúp
              </a>
            </li>
            <li className="mb-2">
              <a href="#" className="text-gray-600 hover:text-gray-800">
                An toàn mua bán
              </a>
            </li>
            <li className="mb-2">
              <a href="#" className="text-gray-600 hover:text-gray-800">
                Liên hệ hỗ trợ
              </a>
            </li>
          </ul>
        </div>

        {/* Cột 2: Về Chợ Tốt */}
        <div>
          <h3 className="font-bold text-lg mb-4">VỀ CHÚNG TÔI</h3>
          <ul>
            <li className="mb-2">
              <a href="#" className="text-gray-600 hover:text-gray-800">
                Giới thiệu
              </a>
            </li>
            <li className="mb-2">
              <a href="#" className="text-gray-600 hover:text-gray-800">
                Chính sách bảo mật
              </a>
            </li>
            <li className="mb-2">
              <a href="#" className="text-gray-600 hover:text-gray-800">
                Tuyển dụng
              </a>
            </li>
            <li className="mb-2">
              <a href="#" className="text-gray-600 hover:text-gray-800">
                Truyền thông
              </a>
            </li>
            <li className="mb-2">
              <a href="#" className="text-gray-600 hover:text-gray-800">
                Blog
              </a>
            </li>
          </ul>
        </div>

        {/* Cột 3: Liên kết */}
        <div>
          <h3 className="font-bold text-lg mb-4">LIÊN KẾT</h3>
          <div className="flex space-x-4 mb-4">
            <a href="#" className="text-blue-600 hover:text-blue-800">
              <FontAwesomeIcon icon={faFacebook} size="2x" />
            </a>
            <a href="#" className="text-red-600 hover:text-red-800">
              <FontAwesomeIcon icon={faYoutube} size="2x" />
            </a>
            <a href="#" className="text-blue-400 hover:text-blue-600">
              <FontAwesomeIcon icon={faLinkedin} size="2x" />
            </a>
          </div>
          <div>
            <img
              src="/src/assets/img/registered-badge.png"
              alt="Đã đăng ký Bộ Công Thương"
              className="w-32"
            />
          </div>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
