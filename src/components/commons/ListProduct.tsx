import React, { useEffect, useState } from "react";
import ShoesItem from './../../components/commons/ShoesItem';
import Image from './../../components/commons/Image';
import { useNavigate } from "react-router-dom";

interface Product {
  id: string;
  title: string;
  image: string;
}

interface ListProductProps{
  products: Product[]
}

export default function ListProduct({products}: ListProductProps)  {
  const gridColumns = Math.min(4, products.length);
  const navigete = useNavigate()
 
  const handleItemDetail = (id: string) => {

    navigete(`/chi-tiet-san-pham?id=${id}`)
  }
  return (
    <div className={`grid grid-cols-${gridColumns} gap-4`}>
        {products.map((item) => <ShoesItem id={item.id} title={item.title} image={item.image} large onclick={(() => handleItemDetail(item.id))} />)}
    </div>
  )
}
