/*import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import "./index.css";
import App from "./App.tsx";
import Login from "./pages/LoginPage.tsx";
import SignIn from "./pages/SignIn.tsx";
import NotFound from "./routes/NotFound.tsx";
import Home from "./pages/HomePage.tsx";

createRoot(document.getElementById("root")!).render(
  <StrictMode>
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<App />}>
          <Route path="/trang-chu" element={<Home />} />
          <Route path="/dang-nhap" element={<Login />} />
          <Route path="/dang-ky" element={<SignIn />} />
        </Route>
        <Route path="*" element={<NotFound />} />
      </Routes>
    </BrowserRouter>
  </StrictMode>
);*/

import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App'; // Gọi `App` từ file `App.tsx`
import './index.css'; // Nếu bạn đang sử dụng Tailwind hoặc CSS khác

ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);















