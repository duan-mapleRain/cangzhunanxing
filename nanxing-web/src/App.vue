<template>
    <div id="app">
        <router-view></router-view>
        <head-and-foot :server-name="serverName" :server-status="serverStatus" />
    </div>
</template>

<script>
import HeadAndFoot from '@/components/tools/headAndFoot.vue'

export default {
    name: 'App',
    components: {
        HeadAndFoot
    },
    data() {
        return {
            serverName: '',
            serverStatus: ''
        }
    },
    mounted() {
        setInterval(() => {
            this.checkServer()
        }, 10000)
    },
    methods: {
        checkServer() {
            fetch('http://localhost:8080/', {
                method: 'GET'
            })
                .then(response => response.json())
                .then(responseData => {
                    if (responseData.responseCode === 200) {
                        this.serverName = responseData.data.name
                        this.serverStatus = responseData.data.status
                    } else {
                        this.serverName = "服务器错误"
                        this.serverStatus = "responseData.data.status"
                    }
                })
                .catch(error => {
                    this.serverName = "服务器错误"
                    this.serverStatus = error
                })
        }
    }
}
</script>

<style>
#app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
}
</style>
