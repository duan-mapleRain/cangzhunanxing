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

function noToken() {
    alert("尚未登录，请登录");
    router.push('/login')
}

function exit() {
    localStorage.removeItem("token");
    localStorage.removeItem("expiration");
    router.push('/login')
}

export default {
    // eslint-disable-next-line vue/multi-word-component-names
    name: 'Main',
    computed: {
        hasValidToken() {
            const token = localStorage.getItem('Authorization');
            if (token) {
                //与服务器通信验证token是否过期
                // eslint-disable-next-line vue/no-async-in-computed-properties
                 fetch("http://localhost:8080/verify", {
                     method: "POST",
                     headers: {
                         "Authorization": token
                     }
                 })
                     .then(response => response.json())
                     .then(responseData => {
                             if (responseData.responseCode === 200) {
                                 // token有效
                                 return true;
                             } else {
                                 // token过期
                                 return false;
                             }
                         }
                     )
                     // eslint-disable-next-line no-unused-vars
                     .catch(error => {
                         // 服务器错误
                         return false;
                     });
                return true;
            }else {
                // 无token
                noToken();
            }
        }
    },
    methods: {
        click() {
            exit();
        }
    }
};


</script>

<style scoped>

</style>