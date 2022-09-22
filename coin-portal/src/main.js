import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
import router from './router'
import store from "./store";
import axios from 'axios'
import 'bootstrap/dist/css/bootstrap.min.css'






Vue.config.productionTip = false
Vue.prototype.$http = axios

Vue.use(ElementUI)

router.beforeEach((to, from, next)=>{
  store.commit('getToken')
  const token = store.state.user.token
  if(!token && (to.name !== "login" && to.name !== "Registration")){
    next('login');
  }else{
    next()
  }
})


new Vue({
  store,
  router,
  render: h => h(App),
}).$mount('#app')
