import axios from "axios";
import config from "@/config";
import {Message} from 'element-ui';
const baseUrl = process.env.NODE_ENV === 'development' ? config.baseURL.dev : config.baseURL.pro

class HttpRequest{
    constructor(baseUrl) {
        this.baseUrl = baseUrl
    }

    getInsideConfig(){
        return {
            baseURL: this.baseUrl,
            header: {},
            withCredentials: true,
        }
    }
    interceptors(instance){
        instance.interceptors.request.use(function (config) {
            // 在发送请求之前做些什么
            return config;
        }, function (error) {
            // 对请求错误做些什么
            return Promise.reject(error);
        });

        instance.interceptors.response.use(function (response) {
            if(response.data.code === 200 && response.data.message !== ''){
                Message.success(response.data.message);
            }else if(response.data.code === 500 && response.data.message !== ''){
                Message.error(response.data.message);
            }
            return response.data.data;
        }, function (error) {
            // 对响应错误做点什么
            Message.error("发生错误");

            return Promise.reject(error);
        });
    }
    request(options){
        const instance = axios.create()
        options = {...this.getInsideConfig(), ...options}
        this.interceptors(instance)
        return instance(options)
    }
}
export default new HttpRequest(baseUrl)
