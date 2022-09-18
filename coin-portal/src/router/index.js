import Vue from "vue";
import VueRouter from 'vue-router'
import Main from "@/components/Main";
// import login from "@/views/login";

import Registration from "@/views/common/Registration";
import Login from "@/views/common/Login";
import MyEchart from "@/components/MyEchart";
Vue.use(VueRouter)
const routes = [
    {
        path:'/',
        name:'Main',
        component: Main,
        children:[]
    },
    {
        path: '/login',
        name:'login',
        component: Login
    },
    {
        path: "/Registration",
        name: 'Registration',
        component: Registration
    },
    {
        path: "/echarts",
        component: MyEchart
    }
]
const router = new VueRouter({
    mode:"history",
    routes
})

export default router
