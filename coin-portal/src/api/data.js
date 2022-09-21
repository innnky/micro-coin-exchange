import axios from "./axios";
import qs from 'qs';
import store from "@/store";
export const getRequest = (url, param) => {
    store.commit('getToken')
    const token = store.state.user.token

    return axios.request({
        url,
        method: 'get',
        params:param,
        headers:{
            authorization: `Bearer ${token}`
        }
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
    store.commit('getToken')
    const token = store.state.user.token
    return axios.request({
        url,
        method: 'post',
        data:param,
        headers:{
            authorization: `Bearer ${token}`
        }
    })
}
export const putRequest = (url, param) => {
    store.commit('getToken')
    const token = store.state.user.token
    return axios.request({
        url,
        method: 'put',
        data:param,
        headers:{
            authorization: `Bearer ${token}`
        }
    })
}
export const delRequest = (url, param) => {
    store.commit('getToken')
    const token = store.state.user.token
    return axios.request({
        url,
        method: 'delete',
        data:param,
        headers:{
            authorization: `Bearer ${token}`
        }
    })
}

export const doLogin = (d) => {

    return axios.request({
        url:'/auth/oauth2/token?grant_type=password&scope=server',
        method: 'post',
        data: qs.stringify(d),
        auth: {
            username: "portal",
            password: "portal"
        },
    })
}