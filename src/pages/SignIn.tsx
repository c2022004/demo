import Button from "../components/commons/Button";
import Image from "../components/commons/Image";
import TextField from "../components/commons/TextField";

interface Logo {
  name: string;
  src: string;
}

function SignIn() {
  const logo: Logo[] = [
    {
      name: "Google",
      src: "https://tse1.mm.bing.net/th?id=OIP.Ti437U7AjYAQtpewkmR9VwHaEK&pid=Api&P=0&h=180",
    },
  ];

  return (
    <div className="flex flex-col w-screen justify-center items-center">
      <div className="w-80">
        <div className="">
          <img src="" alt="" />
        </div>
        <div className="font-bold text-4xl text-left p-2">Đăng ký</div>
        <TextField type="text" label="Username" />
        <TextField type="email" label="Email" />
        <TextField type="password" label="Password" />
        <TextField type="password" label="Confirm password" />
        <div className="p-2">
          <Button outline large primary>
            Đăng nhập
          </Button>
        </div>
        <div className="flex gap-2 ">
          {logo.map((item, index) => (
            <Button
              key={index}
              className="flex bg-white  "
              lefticon={<Image sizes="small" src={item.src} alt={item.name} />}
              children={""}
              small={true}
              flex={true}
            ></Button>
          ))}
        </div>
      </div>
    </div>
  );
}
export default SignIn;
