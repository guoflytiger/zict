import VueRouter from 'vue-router'
import Regist from '../components/Regist.vue'
import Login from '../components/Login.vue'
export default new VueRouter({
  mode: 'history',
  routes: [
    {
      path: '/home',
      redirect: '/login'
    },
    {
      path: '/regist',
      component: Regist
    },
    {
      path: '/login',
      component: Login
    }
  ]
})