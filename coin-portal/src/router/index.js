import Vue from "vue";
import VueRouter from 'vue-router'
import Main from "@/components/Main";
// import login from "@/views/login";

import Registration from "@/views/common/Registration";
import Login from "@/views/common/Login";
import MyEchart from "@/components/MyEchart";
import Trade from "@/views/trading/Trade";
import Index from "@/views/Index";
import Market from "@/views/trading/Market";
import UserCenter from "@/views/user/UserCenter";
Vue.use(VueRouter)
const routes = [
    {
        path:'/',
        name:'Main',
        component: Main,
        redirect:"/index",
        children:[
            {
                path:"/index",
                component: Index
            },
            {
                path: "/trade",
                component: Trade
            },
            {
                path: "/market",
                component: Market
            },
            {
                path: "/usercenter",
                component: UserCenter
            }

        ]
    },
    {
        path: '/login',
        name:'login',
        component: Login
    },
    {
        path: "/registration",
        name: 'registration',
        component: Registration
    },
    {
        path: "/echarts",
        component: MyEchart
    },
]
const router = new VueRouter({
    mode:"history",
    routes
})

export default router
