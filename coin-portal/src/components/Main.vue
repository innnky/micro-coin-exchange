<template>
  <el-container style="height: 100%">
    <el-aside width="auto" style="background:#000000;">
      <common-aside/>
    </el-aside>
    <el-container>
      <el-header style="height: 50px; background:#ffffff;">
        <common-header/>
      </el-header>
      <el-main style="background:#e8e8e8;">
        <router-view/></el-main>
    </el-container>
  </el-container>
</template>

<script>
import CommonAside from "@/components/CommonAside";
import CommonHeader from "@/components/CommonHeader";

export default {
  name: "Home",
  components:{
    CommonAside: CommonAside,
    CommonHeader:CommonHeader,
  }
}
</script>

<style lang="less" scoped>
.el-header{
  background: #313131;
}
.el-main{
  padding-top: 0;
}
</style>
