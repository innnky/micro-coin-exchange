import axios from "./axios";

export const getRequest = (url, param) => {
    return axios.request({
        url,
        method: 'get',
        params:param
    })
}
export const getRequestWithData = (url, param, data) => {
    return axios.request({
        url,
        method: 'get',
        params:param,
        data:data
    })
}

export const postRequest = (url, param) => {
    return axios.request({
        url,
        method: 'post',
        data:param
    })
}
export const putRequest = (url, param) => {
    return axios.request({
        url,
        method: 'put',
        data:param
    })
}
export const delRequest = (url, param) => {
    return axios.request({
        url,
        method: 'delete',
        data:param
    })
}

export const doLogin = (param) => {
    return axios.request({
        url:'/login',
        method: 'post',
        params: param
    })
}