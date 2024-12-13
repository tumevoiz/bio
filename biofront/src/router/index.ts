import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/AboutView.vue'),
    },
    {
      path: '/messages',
      name: 'messages',
      component: () => import('../views/MessagesView.vue'),
      children: [
        {
          path: 'channel/:id',
          name: 'channel',
          component: import('../views/ChannelView.vue'),
        },
      ]
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('../views/Login/LoginView.vue'), // Lazy-loading LoginView
    },
    {
      path: '/register',
      name: 'register',
      component: () => import('../views/Register/RegisterView.vue'), // Lazy-loading RegisterView
    },
  ],
})

// Middleware do autoryzacji
router.beforeEach((to, from, next) => {
  const requiresAuth = to.matched.some(record => record.meta.requiresAuth)

  const token = localStorage.getItem('token')

  if (requiresAuth && !token) {
    next('/login')
  } else if ((to.name === 'login' || to.name === 'register') && token) {
    next('/')
  } else {
    next()
  }
})


export default router
