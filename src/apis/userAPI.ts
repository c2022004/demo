import axiosJWT from "../config/axiosJWTConfig";
import { SignIn } from "../types/userTypes";
import { DOMAIN } from './index';

const apiSigIn = "/skeleton/public/v1/user";
export const addUser=async (signIn:SignIn) =>{
    try {
        const response = await axiosJWT.post(`${DOMAIN}${apiSigIn}`,{
            email:signIn.email, 
            password: signIn.password,
            dateOfBirth: signIn.dateOfBirth
        })
    } catch (error) {
        console.log("lỗi khi đăng ký", error);
        throw new Error
    }
}