<template>
  <div>
    <el-menu
        :default-active="activeIndex2"
        class="el-menu-demo"
        mode="horizontal"
        @select="handleSelect"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b">
      <el-menu-item index="1">首页</el-menu-item>
      <el-menu-item index="2">交易市场</el-menu-item>
      <el-menu-item index="3">用户中心</el-menu-item>
<!--      <el-menu-item index="3" disabled>消息中心</el-menu-item>-->
<!--      <el-menu-item index="4" class="float-end">登录</el-menu-item>-->
<!--      <el-menu-item index="5" class="float-end">注册</el-menu-item>-->

    </el-menu>
  </div>
</template>

<script>

export default {
  name: "CommonHeader",
  data() {
    return {
      activeIndex: '1',
      activeIndex2: '1'
    }
  },
  methods: {
    handleSelect(key, keyPath) {
      if(key === "1"){
        this.$router.push("/index")
      }
      else if(key === "2"){
        this.$router.push("/market")
      }else  if(key === "3"){
        this.$router.push("/usercenter")
      }
      console.log(key, keyPath);
    }
  },
  mounted() {

  },
  computed: {

  }
}
</script>

<style lang="less" scoped>
.el-menu{
  width: 100%;
}
</style>
