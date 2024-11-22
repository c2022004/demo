/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
    "./node_modules/flowbite-react/lib/**/*.{js,ts,jsx,tsx}", // Thêm dòng này vì bạn đang dùng flowbite
  ],
  theme: {
    extend: {
      colors: {
        primary: "var(--primary)",
      },
      width: {
        default: "960px",
      },
    },
  },
  plugins: [
    require("flowbite/plugin"), // Thêm plugin flowbite
  ],
};
