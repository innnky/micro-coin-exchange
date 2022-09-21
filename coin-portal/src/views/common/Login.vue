<template>
  <div class="container">
    <el-form
        :model="form"
        status-icon
        :rules="rules"
        ref="form"
        label-width="100px"
        class="login-container"
    >
      <h4>登录</h4>
      <br>      <el-form-item
        label="用户名"
        label-width="80px"
        prop="username"
        class="username"
    >
      <el-input type="input" v-model="form.username" auto-complete="off" placeholder="请输入账号"></el-input>
    </el-form-item>
      <el-form-item
          label="密码"
          label-width="80px"
          prop="password"
      >
        <el-input type="password" v-model="form.password" auto-complete="off" placeholder="请输入密码"></el-input>
      </el-form-item>
      <!--      <el-form-item class="login-submit">-->
      <el-button type="primary" @click="login" class="login-submit">登录</el-button>
      <!--      </el-form-item>-->
      <!--      <el-form-item class="login-submit">-->
      <el-button type="primary" @click="reg" class="login-submit">注册</el-button>
      <!--      </el-form-item>-->
    </el-form>
  </div>
</template>

<script>
import {doLogin, getRequest} from "@/api/data";
import {encryption} from "@/util";
export default {
  name: "login",
  data() {
    return {
      username: '',
      form: {
        username: "abc",
        password: "123"
      },
      rules: {
        username: [
          {required: true, message: "请输入用户名", trigger: "blur"},
          {min: 3, message: "用户名长度不能小于3位", trigger: "blur"}
        ],
        password: [
          {required: true, message: "请输入密码", trigger: "blur"},
        ]
      }
    }
  },
  methods: {
    login() {
      // const token = 'asdasdasda';
      // doLogin({
      //   username: this.form.username,
      //   password: this.form.password
      // }).then(res => {
      //   // alert(res)
      //   if(res.token!== undefined){
      //     this.$store.commit("setToken", res.token)
      //
      //     this.$router.push("/StudentAccount")
      //   }
      //
      // })
      const user = encryption({
        data: {
          username: this.form.username,
          password: this.form.password

        },
        key: 'thanks,pig4cloud',
        param: ['password']
      })
      doLogin(user).then(res => {
        console.log(res)
          if(res.access_token !== undefined){
            this.$store.commit("setToken", res.access_token)
            console.log(res.access_token)
            this.$router.push("/trade")
          }

      })
    },
    reg() {
      // this.$router.push("/Registration")
      getRequest("/user/user/info",{})
    }
  }
}
</script>

<style lang="less" scoped>
.container {
  width: 100%;
  height: 100%;
  display: flex;

}

.login-container {
  border-radius: 15px;
  background-clip: padding-box;
  margin: auto auto;

  width: 350px;
  padding: 35px;
  background-color: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}

h3 {
  margin: 0px auto 40px auto;
  text-align: center;
  color: #505458;

}

.login-submit {
  margin: 10px auto 0 auto;
  width: 100%;
}
</style>
