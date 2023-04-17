<template>
    <router-view></router-view>
    <footer id="bottom">
        <h1 id="run_status">未连接服务器</h1>
    </footer>
</template>

<script>

//在页面下方固定一个footer，每秒刷新服务器运行状态，如果服务器运行正常则显示“系统运行正常”，否则显示服务器返回的错误信息
export default {
    name: 'App',
    beforeMount() {
        setInterval(() => {
            this.checkServer()
        }, 1000 * 10)
    },
    methods: {
        checkServer() {
            fetch("http://localhost:8080/", {
                method: "GET",
            }).then(response => response.json())
                .then(responseData => {
                    if (responseData.responseCode === 200) {
                        document.getElementById("run_status").innerHTML = "系统运行正常";
                    } else {
                        document.getElementById("run_status").innerHTML = responseData.message;
                    }
                })
        }
    },
    components: {}
}
</script>

<style>

</style>
