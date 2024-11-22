import React, { useState } from "react";
import * as XLSX from "xlsx";
import axios from "axios";
interface Product {
  name: string;
  image: string;
  price: number;
  available: boolean;
  categoryId: number;
}

function File() {
  const [data, setData] = useState<Product[]>([]);

  const handleFileimport = (e: React.ChangeEvent<HTMLInputElement>) => {
    //lấy file đã được chọn
    const files = e.target.files;

    if (!files || files.length === 0) {
      console.log("Vui lòng chọn file");
      return;
    }

    //lấy phần tử đầu
    const file = files[0];

    const reader = new FileReader();
    reader.onload = (e) => {
      const data = e.target?.result;
      if (!data) return;
      const workBook = XLSX.read(data, { type: "binary" });
      const sheetName = workBook.SheetNames[0];
      const sheet = workBook.Sheets[sheetName];
      const workSheet = XLSX.utils.sheet_to_json(sheet) as Product[];

      setData(workSheet);
      console.log(workSheet);
    };

    reader.readAsBinaryString(file);
  };

  const handleSaveFile = async () => {
    try {
      const response = await axios.post(
        "http://localhost:8081/api/product",
        data
      );
      console.log(JSON.stringify(response.data));
    } catch (err) {
      console.log("Lỗi khi gửi dữ liệu", err);
    }
  };

  return (
    <div>
      <label htmlFor="file-product">Import Excel file</label>
      <input
        type="file"
        name="file-product"
        id="file-product"
        accept=".xlsx, .xls" // Chỉ chấp nhận file Excel
        onChange={handleFileimport}
      />

      <button onClick={handleSaveFile}>Lưu file </button>
    </div>
  );
}

export default File;
