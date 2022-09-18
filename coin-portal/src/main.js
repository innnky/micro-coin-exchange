import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
import router from './router'
import store from "./store";
import axios from 'axios'
import 'bootstrap/dist/css/bootstrap.min.css'
// import ECharts from 'vue-echarts'
// import 'echarts/lib/chart/line'
// Vue.component('chart', ECharts)
//





import ECharts from 'vue-echarts'
import { use } from 'echarts/core'

// import ECharts modules manually to reduce bundle size
import {
  CanvasRenderer
} from 'echarts/renderers'
import {
  BarChart
} from 'echarts/charts'
import {
  GridComponent,
  TooltipComponent
} from 'echarts/components'

use([
  CanvasRenderer,
  BarChart,
  GridComponent,
  TooltipComponent
]);

// register globally (or you can do it locally)
Vue.component('v-chart', ECharts)







Vue.config.productionTip = false
Vue.prototype.$http = axios

Vue.use(ElementUI)
//
// router.beforeEach((to, from, next)=>{
//   store.commit('getToken')
//   const token = store.state.user.token
//   if(!token && (to.name !== "login" && to.name !== "Registration")){
//     next('login');
//   }else{
//     next()
//   }
// })
new Vue({
  store,
  router,
  render: h => h(App),
}).$mount('#app')
