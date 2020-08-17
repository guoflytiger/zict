import Vue from 'vue'
import App from './App.vue'
import axios from 'axios'
import VueAxios from 'vue-axios'
import ElementUi from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import VueRouter from 'vue-router'
import router from './router/router'
import { Message } from 'element-ui';
Vue.use(VueRouter)
Vue.use(ElementUi)
Vue.use(Message)//消息提示
Vue.prototype.$message = Message
Vue.use(VueAxios, axios)
Vue.config.productionTip = false
new Vue({
  render: h => h(App),
  router
}).$mount('#app')
