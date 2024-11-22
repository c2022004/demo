import React, { useState } from "react";

import axios from "axios";
import TextField from "../../components/commons/TextField";
import Button from "../../components/commons/Button";
import Image from "../../components/commons/Image";

interface Logo {
  name: string;
  src: string;
}

// interface LoginProps {
//   username?: string;
//   currency?: string;
//   type?: string;
// }

function Login() {
  const api = "/api/login";
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const logo: Logo[] = [
    {
      name: "Facebook",
      src: "https://tse2.mm.bing.net/th?id=OIP.cOz92GK9w_2_VxUIWBL0ngHaHa&pid=Api&P=0&h=180",
    },
    {
      name: "Google",
      src: "https://tse4.mm.bing.net/th?id=OIP.D6P-BO32wCApcPIIjt6p5wHaHa&pid=Api&P=0&h=180",
    },
  ];

  const handleUsename = (e: React.ChangeEvent<HTMLInputElement>) => {
    setUsername(e.target.value);
  };

  const handlePassword = (e: React.ChangeEvent<HTMLInputElement>) => {
    setPassword(e.target.value);
  };

  const handleLogin = async () => {
    try {
      const response = await axios.post(api, { username, password });
      if (response.data.token) {
        localStorage.setItem("auth_token", response.data.token);
        console.log("Đăng nhập thành công");
      } else {
        console.log("Đăng nhập thất bại");
      }
    } catch (error: unknown) {
      alert("Đăng nhập thất bại. Vui lòng kiểm tra lại thông tin.");
      console.error(error);
    }
  };

  return (
    <div className="flex flex-col w-screen justify-center items-center">
      <div className="back fixed top-0 right-0">
        <Button className="p-3 text-xl text-black" to="/">
          X
        </Button>
      </div>
      <div className="w-80">
        <div className="">
          <img src="" alt="" />
        </div>
        <div className="font-bold text-4xl text-left p-2">Đăng nhập</div>
        <TextField
          tabIndex={1}
          type="text"
          onChange={handleUsename}
          label="Username"
        />
        <TextField
          tabIndex={2}
          type="password"
          onChange={handlePassword}
          label="Password"
        />
        <div className="p-2">
          <Button outline large primary onClick={handleLogin}>
            Đăng nhập
          </Button>
        </div>
        <div className="flex gap-2 ">
          {logo.map((item, index) => (
            <Button
              key={index}
              className="flex bg-white "
              lefticon={<Image sizes="small" src={item.src} alt={item.name} />}
              children={item.name}
              flex={true}
            ></Button>
          ))}
        </div>
      </div>
    </div>
  );
}

export default Login;
