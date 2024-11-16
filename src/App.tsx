import { Link, Outlet } from "react-router-dom";
import Login from "./pages/LoginPage";
function Header() {
  const page = [
    { path: "/trang-chu", name: "Trang chủ" },
    { path: "/dang-nhap", name: "Đăng nhập" },
    { path: "/dang-ky", name: "Đăng ký" },
  ];
  return (
    <div className="w-full ">
      {page.map((item) => (
        <Link to={item.path} children={item.name} />
      ))}
      <Outlet />
    </div>
  );
}

export default Header;
