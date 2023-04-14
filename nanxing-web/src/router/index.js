import {createRouter,createWebHashHistory} from 'vue-router'


const routes = [
    {
        path: '/login',
        name: 'login',
        component: () => import('@/components/Login/Login.vue')
    },
    {
        path: '/',
        name: 'main',
        component: () => import('@/components/Main/Main.vue')
    }
]

export const router = new createRouter({
    history:createWebHashHistory(),
    routes:routes
})

export default router
