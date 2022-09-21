<template>
  <el-container style="height: 100%">
    <el-header style="height: 60px; background:#ffffff;"><common-header></common-header></el-header>
    <el-main style="background:#e8e8e8;">
      <router-view/>
    </el-main>
  </el-container>
</template>

<script>
import CommonHeader from "@/components/CommonHeader";

export default {
  name: "Home",
  components:{
    CommonHeader:CommonHeader,
  }
}
</script>

<style lang="less" scoped>
.el-header{
  background: #313131;
  margin: 0;
  padding: 0;
}
.el-main{
  padding-top: 0;
}
</style>
