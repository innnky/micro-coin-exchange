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
      <h3>注册账号</h3>
      <el-form-item
          label="邮箱账号"
          label-width="80px"
          prop="username"
          class="username"
      >
        <el-input type="input" v-model="form.username" auto-complete="off" placeholder="请输入账号"></el-input>
      </el-form-item>
      <el-form-item
          label="密码"
          label-width="80px"
          prop="password">
        <el-input type="password" v-model="form.password" auto-complete="off" placeholder="请输入密码"></el-input>
      </el-form-item>
      <el-form-item
          label="确认密码"
          label-width="80px"
          prop="passwordConfirm">
        <el-input type="password" v-model="form.passwordConfirm" auto-complete="off" placeholder="请再次输入密码"></el-input>
      </el-form-item>
      <el-form-item
          label="姓名"
          label-width="80px"
          prop="name">
        <el-input type="input" v-model="form.name" auto-complete="off" placeholder="请输入姓名"></el-input>
      </el-form-item>
      <el-form-item
          label="学号"
          label-width="80px"
          ref="number"
          prop="number">
        <el-input type="input" v-model="form.number" auto-complete="off" placeholder="请输入"></el-input>
      </el-form-item>

      <el-form-item
        label="教师/学生"
        label-width="80px"
        prop="type">
        <el-radio-group v-model="form.type" @change="changeRole">
          <el-radio label="teacher">教师</el-radio>
          <el-radio label="student">学生</el-radio>
        </el-radio-group>
      </el-form-item>
<!--      <el-form-item class="login-submit">-->
        <el-button type="primary" @click="register" class="login-submit ">注册</el-button>

<!--      </el-form-item>-->
<!--      <el-form-item class="login-submit">-->
        <el-button type="primary" @click="login" class="login-submit">登录</el-button>

<!--      </el-form-item>-->
    </el-form>
  </div>
</template>

<script>

import {postRequest} from "@/api/data";

export default {
  name: "Registration",
  data() {

    return {
      username: '',
      form: {
        username: "",
        password: "",
        passwordConfirm: "",
        type: "student"
      },
      rules: {
        username: [
          {required: true, message: "请输入用户名", trigger: "blur"},
          {min: 3, message: "用户名长度不能小于3位", trigger: "blur"}
        ],
        password: [
          {required: true, message: "请输入密码", trigger: "blur"},
        ],
        passwordConfirm: [
          {required: true, message: "请输入确认密码", trigger: "blur"},
          {validator: this.checkPasswords, trigger: "blur"}
        ],
        name: [
          {required: true, message: "请输入姓名", trigger: "blur"},
        ],
        number: [
          {required: true, message: "请输入", trigger: "blur"},
        ],
      }
    }
  },
  methods: {
    register() {
      // const token = 'asdasdasda';
      // doLogin({
      //   username: this.form.username,
      //   password: this.form.password
      // }).then(res => {
      //   this.$store.commit("setToken", token)
      //   res
      //   this.$router.push("/AdminHomePage")
      //
      // })
      //校验
      this.$refs.form.validate(valid => {
        if (valid) {
          if(this.form.type === "teacher") {
            postRequest("/user/registerTeacher", {
              username: this.form.username,
              password: this.form.password,
              name: this.form.name,
              number: this.form.number,
            })

          }else if (this.form.type === "student") {
            postRequest("/user/registerStudent", {
              username: this.form.username,
              password: this.form.password,
              name: this.form.name,
              number: this.form.number,
            })
          }
        }
      })


    },
    changeRole() {
      if (this.form.type === "teacher") {
        this.$refs.number.label = "教师号";
      } else {
        this.$refs.number.label = "学号";
      }
    },
    checkPasswords(rule, value, callback) {
      if (value === this.form.password) {
        callback()
      } else {
        callback(new Error("两次输入密码不一致"))
      }
    },
    login() {
      this.$router.push("/login")
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
