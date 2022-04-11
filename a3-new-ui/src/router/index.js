import Vue from 'vue'
import VueRouter from 'vue-router'
import RegisterPage from '../components/RegisterPage.vue'
import LoginPage from '../components/LoginPage.vue'
import HomeView from '../views/HomeView.vue'
import CourseView from '../components/CourseView.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'RegisterPage',
    component: RegisterPage
  },
  {
    path: '/Login',
    name: 'LoginPage',
    component: LoginPage
  },
  {
    path: '/Homeview',
    name: 'HomeView',
    component: HomeView
  },
  {
    path: '/Courseview',
    name: 'CourseView',
    component: CourseView
  },
  {
    path: '/comments/:code',
    name: 'CommentsView',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/CommentsView.vue')
  },
  {
    path: '/books/:code',
    name: 'BookView',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/BookView.vue')
  },
  {
    path: '/log',
    name: 'log',
    component: () => import('../views/LogView.vue')
  },
  {
    path: '/add',
    name: 'add',
    component: () => import('../views/AddAlbum.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
