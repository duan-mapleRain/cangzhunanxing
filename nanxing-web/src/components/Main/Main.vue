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
    methods: {
        click() {
            router.push('/login')
        }
    }
}
</script>

<style scoped>

</style>