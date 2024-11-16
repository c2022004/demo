import React from "react";
import TextField from "../components/commons/TextField";
import Button from "../components/commons/Button";
import Image from "../components/commons/Image";

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
  const logo: Logo[] = [
    {
      name: "Facebook",
      src: "https://tse2.mm.bing.net/th?id=OIP.cOz92GK9w_2_VxUIWBL0ngHaHa&pid=Api&P=0&h=180",
    },
    {
      name: "Google",
      src: "https://tse3.mm.bing.net/th?id=OIP.wcCr61uvvLKRnFSMCYpoogHaEK&pid=Api&P=0&h=180",
    },
  ];

  return (
    <div className="flex flex-col w-screen justify-center items-center">
      <div className="w-80">
        <div className="">
          <img src="" alt="" />
        </div>
        <div className="font-bold text-4xl text-left p-2">Đăng nhập</div>
        <TextField type="text" label="Username" />
        <TextField type="password" label="Password" />
        <div className="p-2">
          <Button outline large primary>
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
              small={true}
              flex={true}
            ></Button>
          ))}
        </div>
      </div>
    </div>
  );
}

export default Login;
