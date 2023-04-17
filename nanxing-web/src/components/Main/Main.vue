<template>
    <router-view></router-view>
    <head>
        <title>管理系统</title>
    </head>
    <body>
    <h1 @click="click">点击退出</h1>
    </body>
</template>

<script>
import router from "@/router";

export default {
    name: 'Main',



    methods: {
        click() {
            var message = "是否退出"
            if (confirm(message) === true) {
                router.push('/login')
            }
        }
    }


}

function noToken() {
    alert("尚未登录，请登录");
    router.push('/login')
}
</script>

<style scoped>

</style>