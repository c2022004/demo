/*import React, { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import { BrowserRouter } from "react-router-dom";
import "./index.css";
import App from "./App";

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
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './index.css';
import Checkout from './pages/user/Checkout';
import Home from './pages/public/Home';
import ProductList from './pages/public/ProductListPage';
import Cart from './pages/user/Cart';
import ProductDetail from './pages/public/ProductDetail';

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/checkout" element={<Checkout />} />
        <Route path="/List" element={<ProductList/>} />
        <Route path="/Cart" element={<Cart/>} />
        <Route path="/Detail" element={<ProductDetail/>} />
      </Routes>
    </Router>
  </React.StrictMode> 
);


















