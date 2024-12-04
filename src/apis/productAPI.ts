
import axiosJWT from "../config/axiosJWTConfig"
import { DOMAIN } from './index';
import { AddProduct } from './../types/product';
import axios from "axios";

export const findAllProduct = async () => {
    try {
        const response = await axiosJWT.get(`${DOMAIN}/skeleton/public/v1/product`)
        return response.data
    } catch (error) {
        console.error('Error fetching all activities:', error);
        throw error;
    }
}

export const findProductById = async (id: string) => {
    try {
        const response = await axiosJWT.get(`${DOMAIN}/skeleton/public/v1/product/${id}`)
        return response.data
    } catch (error) {
        console.error('Error fetching all activities:', error);
        throw error;
    }
}

export const addProduct = async (data: AddProduct) => {
    try {
        const response = await axiosJWT.post(`${DOMAIN}/skeleton/admin/v1/inventory`, data, {
            headers: {
                'Content-Type': 'multipart/form-data',
            },
        });
        return response.data
    } catch (error) {
        console.error('Lỗi khi thêm mới', error);
        throw error;
    }
}


