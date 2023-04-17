import {createApp} from 'vue'


import {create, NButton} from 'naive-ui'
import router from "./router"
import App from "@/App.vue";
import Login from '@/components/Login/Login.vue'
import Main from "@/components/Main/Main.vue";

const app = createApp(App);
const naive = create({
    components: [NButton]
})


app.use(router);
app.use(naive)

app.mount('#app')
app.mount(Login)
app.mount(Main)



