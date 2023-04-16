<template>
    <router-view></router-view>
    <head>
        <title>管理系统</title>
    </head>
    <body>
    <h1 @click="click">点击登录</h1>
    </body>
</template>

<script>
import router from "@/router";
export default {
    name: 'Main',
    computed: {
        hasValidToken() {
            const token = localStorage.getItem('token');
            if (token) {
                const expiration = localStorage.getItem('expiration');
                if (expiration && new Date(expiration) > new Date()) {
                    // token存在且未过期
                    return true;
                } else {
                    // token过期或没有设置过期时间
                    localStorage.removeItem('token');
                    localStorage.removeItem('expiration');
                }
            }
            // token不存在或已过期
            return false;
        }
    },
    methods: {
        click() {
            if (this.hasValidToken) {
                // token存在且未过期，刷新数据
                // TODO: 刷新数据的逻辑
            } else {
                // token不存在或已过期，导航到登录页面
                router.push('/login');
            }
        }
    }
}
</script>

<style scoped>

</style>