import { DOMAIN } from "."
import axiosJWT from "../config/axiosJWTConfig"

const baseURL = `${DOMAIN}/skeleton/admin/v1/supplier/getall`

export const findAllSuppliers = async ()=>{
    try {
        const response = await axiosJWT.get(`${baseURL}`)
        return response.data
    } catch (error) {
        console.log("lỗi axios" ,error);
        throw new Error
    }

}