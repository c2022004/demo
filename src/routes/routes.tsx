import adminRoutes from "../config/adminRoutes";
import userRoutes from "../config/userRoutes";
import Home from "../pages/public/Home";
import addToCart from "../pages/user/AddToCart";
import Checkout from "../pages/user/Checkout";
import Profile from "../pages/user/Profile";
import addCategory from "../pages/admin/AddCategory";
import AddProduct from "../pages/admin/AddProduct";
import DisableUser from "../pages/admin/DisableUser";
import ReportUser from "../pages/admin/ReportUser";
import publicRoutes from "../config/pulicRoutes";
import SignIn from "../pages/public/SignIn";
import OnlyContentLayout from "../components/layouts/OnlyContentLayout";
import DefaultLayout from "../components/layouts/DefaultLayout";
import Login from "../pages/public/Login";
import Comment from "../pages/user/Comment";

// Cấu hình routes theo role và layout
const publicConfig = [
  {
    path: publicRoutes.home,
    component: Home,
    layout: DefaultLayout,
    isPrivate: false,
  },
  {
    path: publicRoutes.login,
    component: Login,
    layout: OnlyContentLayout,
    isPrivate: false,
  },
  {
    path: publicRoutes.signIn,
    component: SignIn,
    layout: OnlyContentLayout,
    isPrivate: false,
  },
];

const userConfig = [
  {
    path: userRoutes.home,
    component: Home,
    layout: DefaultLayout,
    isPrivate: false,
  },
  {
    path: userRoutes.profile,
    component: Profile,
    layout: DefaultLayout,
    isPrivate: true,
  },
  {
    path: userRoutes.addToCart,
    component: addToCart,
    layout: DefaultLayout,
    isPrivate: true,
  },
  {
    path: userRoutes.checkout,
    component: Checkout,
    layout: DefaultLayout,
    isPrivate: true,
  },
  {
    path: userRoutes.comment,
    component: Comment,
    layout: DefaultLayout,
    isPrivate: true,
  },
];

const adminConfig = [
  {
    path: adminRoutes.addCategory,
    component: addCategory,
    layout: DefaultLayout,
    isPrivate: true,
  },
  {
    path: adminRoutes.addProduct,
    component: AddProduct,
    layout: DefaultLayout,
    isPrivate: true,
  },
  {
    path: adminRoutes.disableUser,
    component: DisableUser,
    layout: DefaultLayout,
    isPrivate: true,
  },
  {
    path: adminRoutes.reportUser,
    component: ReportUser,
    layout: DefaultLayout,
    isPrivate: true,
  },
];

export { userConfig, adminConfig, publicConfig };
