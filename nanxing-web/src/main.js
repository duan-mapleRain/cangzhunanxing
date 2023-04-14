import {createApp} from 'vue'
import App from "@/App.vue";
import Login from '@/components/Login/Login.vue'
import Main from "@/components/Main/Main.vue";
import router from "./router"

const app = createApp(App);

app.use(router);

app.mount('#app')
app.mount(Login)
app.mount(Main)



