import { ReactNode } from "react";
import { Outlet } from "react-router-dom";

interface LoginProps {
  children: ReactNode;
}

function OnlyContentLayout({ children }: LoginProps) {
  return <div>{children || <Outlet />}</div>;
}

export default OnlyContentLayout;
