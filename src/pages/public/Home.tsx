import React, { useEffect, useState } from "react";
import ShoesItem from './../../components/commons/ShoesItem';
import Image from './../../components/commons/Image';
import ListProduct from "../../components/commons/ListProduct";

interface Product {
  id: string;
  title: string;
  image: string;
}

function Home() {

  
  const [product, setProduct] = useState<Product[]>([])

  const listProduct: Product[] = [
    {
      id: "ksjflw",
      title: "giầy da",
      image: "https://tse4.mm.bing.net/th?id=OIP.S5JMx5xm1EZtsIXm9_XfxQHaHa&pid=Api&P=0&h=180",
    },
    {
      id: "ksjfkjlw",
      title: "giầy da",
      image: "https://tse4.mm.bing.net/th?id=OIP.S5JMx5xm1EZtsIXm9_XfxQHaHa&pid=Api&P=0&h=180",
    },
    {
      id: "ksjfeulw",
      title: "giầy da",
      image: "https://tse4.mm.bing.net/th?id=OIP.S5JMx5xm1EZtsIXm9_XfxQHaHa&pid=Api&P=0&h=180",
    },
    {
      id: "ksjfldfw",
      title: "giầy da",
      image: "https://tse4.mm.bing.net/th?id=OIP.S5JMx5xm1EZtsIXm9_XfxQHaHa&pid=Api&P=0&h=180",
    },
    {
      id: "ks2345jflw",
      title: "giầy da",
      image: "https://tse4.mm.bing.net/th?id=OIP.S5JMx5xm1EZtsIXm9_XfxQHaHa&pid=Api&P=0&h=180",
    }
  ]

  useEffect(() => {
    setProduct(listProduct)
  }, [])

  return <>
  <ListProduct products={listProduct}/>
  </>
}

export default Home;
