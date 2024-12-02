import React, { useEffect, useState } from "react";
import TextField from "../../components/commons/TextField";
import SelectBox from "../../components/commons/SelectBox";
import Button from "../../components/commons/Button";
import { category } from "../../types/categoryTypes";
import { findAllCategory } from "../../apis/categoryAPI";
import { SelectBoxType } from "../../types/optionSelecBox";
import { findAllSuppliers } from "../../apis/supplierAPI";
import { SupplierDTO } from "../../types/supplierType";

function AddProduct() {

  const [imagePreview, setImagePreview] = useState<string>(""); // State để lưu URL của ảnh preview
  const [category, setCategory] = useState<category[]>([])
  const [supplier, setSupplier] = useState<SupplierDTO[]>([])
  const [optionCategory, setOptionCategory] = useState<SelectBoxType[]>([])
  const [optionSupplier, setOptionSupplier] = useState<SelectBoxType[]>([])
  const handleImageChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const file = event.target.files?.[0];
    if (file) {
      const imageUrl = URL.createObjectURL(file);
      setImagePreview(imageUrl);
    }
  };

  useEffect(() => {
    const fetchAllCategory = async () => {
      try {
        const response = await findAllCategory()
        setCategory(response.data)
        const options = response.data.map((item: category) => ({
          value: item.id,
          label: item.name,
          disable: item.isDeleted,
        }));
        setOptionCategory(options);
      } catch (error) {
        console.log("Lỗi khi lấy category", error);
        return null
      }
    }

    //fetch supplier 
    const fetchAllSuppliers = async () => {
      try {
        const response = await findAllSuppliers()
        console.log("Suppliers : ", response.data);
        setSupplier(response.data)
        const options = response.data.map((item: SupplierDTO) => ({
          value: item.id,
          label: item.supplierName,
        }));

        setOptionSupplier(options)
        return response.data
      } catch (error) {
        console.log("Lỗi khi lấy supplier", error);
        throw new Error

      }
    }

    fetchAllSuppliers()
    fetchAllCategory()

  }, [])


  return <>
    <form action="" className="flex" >
      <div className="w-1/3">
        <TextField placeholder="Example" name="product-id" id="product-id" type="text" label="Tên sản phẩm" />
        <div className="ml-2 mr-2">
          <SelectBox customClassName="w-full" options={optionCategory} defaultOptionValue={"Loại sản phẩm"} name="category-id" id="category-id" label="Loại sản phẩm" />
        </div>
        <TextField placeholder="Example" name="product-name" id="product-name" type="text" label="Loại sản phẩm" />
        <TextField placeholder="Example" name="product-price" id="product-price" type="number" label="Giá sản phẩm" />
        <TextField placeholder="Example" name="product-shortdescription" id="product-shortdescription" type="text" label="Mô tả ngắn" />
        <TextField placeholder="Example" name="product-longdescription" id="product-longdescription" type="text" label="Mô tả dài" />
        <TextField placeholder="Example" name="product-size" id="product-size" type="number" label="Kích thước" />
      </div>
      <div className="w-1/3">
        <TextField placeholder="Example" name="max-1uantity" id="max-quantity" type="text" label="Số lượng tối đa" />
        <div className="ml-2 mr-2">
          <SelectBox customClassName="w-full" options={optionSupplier} defaultOptionValue={"Chọn nhà cung cấp"} name="supplier-id" id="supplier-id" label="Nhà cung cấp" />
        </div>
        <TextField placeholder="Example" name="mini-quantity" id="mini-quantity" type="number" label="Số lượng tối thiểu" />
        <TextField placeholder="Example" name="quantity-in-stock" id="quantity-in-stoct" type="number" label="Số lượng hiện tại" />
        <TextField placeholder="Example" name="product-color" id="product-color" type="text" label="Màu" />
        <TextField placeholder="Example" name="product-image" id="product-image" type="file" label="Hình ảnh" className="w-1/2" onChange={handleImageChange} />
        <Button className="float-right mr-3 bg-blue-200 hover:bg-blue-400">Thêm sản phẩm</Button>
      </div>
      <div className="w-96 p-4 h-96">
        <img src={imagePreview} id="display-imageproduct" />
      </div>
    </form>
  </>;
}

export default AddProduct;
