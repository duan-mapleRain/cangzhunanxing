<template>
    <router-view></router-view>
    <div class="login">
        <h1>南星</h1>
        <form @submit.prevent="login">
            <div class="form-group">
                <label for="account">用户名</label>
                <input type="text" id="account" v-model="account">
            </div>
            <div class="form-group">
                <label for="password">密码</label>
                <input type="password" id="password" v-model="password">
            </div>
            <button type="submit">登录</button>
        </form>
    </div>
</template>


<script>
import router from "@/router";
import {md5} from "@/utils/md5";

export default {
    // eslint-disable-next-line vue/multi-word-component-names
    name: 'Login',
    methods: {
        login() {
            const loginInfo = {
                account: this.account,
                password: md5(this.password + "cangzhu")
            };
            fetch("http://localhost:8080/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(loginInfo)
            })
                .then(response => response.json())
                .then(responseData => {
                        if (responseData.responseCode === 200) {
                            alert(responseData.message);
                            localStorage.setItem('Authorization', "Bearer " + responseData.data.token)
                            // 登录成功，跳转到首页
                            router.push('/')
                        } else {
                            alert(responseData.responseCode + responseData.message);
                        }
                    }
                )
                // eslint-disable-next-line no-unused-vars
                .catch(error => {
                    alert("服务器错误");
                });
        }
    }
};
</script>

<style scoped>
.login {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 100vh;
}

form {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 1rem;
    margin-top: 1rem;
}

.form-group {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 0.5rem;
}

label {
    font-weight: bold;
}

input {
    padding: 0.5rem;
    border: 1px solid #ccc;
    border-radius: 0.25rem;
    font-size: 1rem;
    text-align: center;
}

button {
    padding: 0.5rem 1rem;
    background-color: #007bff;
    color: #fff;
    border: none;
    border-radius: 0.25rem;
    font-size: 1rem;
    cursor: pointer;
}

.error {
    color: red;
    margin-top: 1rem;
}
</style>
