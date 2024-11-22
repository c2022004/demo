/*import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import { BrowserRouter } from "react-router-dom";
import "./index.css";
import App from "./App.tsx";

const root = createRoot(document.getElementById("root")!);

root.render(
  <StrictMode>
    <BrowserRouter>
      <App />
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















