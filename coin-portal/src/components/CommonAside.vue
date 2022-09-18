<template>
  <el-menu default-active="1-4-1" background-color="#334054" text-color="#D0D0D0FF" active-text-color="#4875ab" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose"
           :collapse="isCollapsed">
    <div  v-for="item in this.menu"  :key="item.vuePath">
      <el-menu-item  @click="clickMenu(item)" :index="item.vuePath" v-if="item.children.length===0">
        <i :class="'el-icon-'+item.icon"></i>
        <span slot="title">{{item.menuName}}</span>
      </el-menu-item>
      <el-submenu v-if="item.children.length>0" :index="item.vuePath">
        <template slot="title">
          <i :class="'el-icon-'+item.icon"></i>
          <span slot="title">{{item.menuName}}</span>
        </template>
        <el-menu-item-group>
          <el-menu-item @click="clickMenu(j)" v-for="j in item.children" :index="j.vuePath" :key="j.vuePath">{{j.menuName}}</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
    </div>


  </el-menu>
</template>
<style lang="less" scoped>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
span{
  font-family: "微软雅黑", Arial, sans-serif;
  font-weight: normal;
}
.el-menu{
  height: 100%;
  border: none;
  h3{
    color: #d0d0d0;
    text-align: center;
    line-height: 48px;
  }
}
</style>

<script>
import {data} from "@/data";
import {getRequest} from "@/api/data";

export default {
  data() {
    return {
      menu: data.students.menu,
      // menu: data.admin.menu,
    };
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    clickMenu(item){
      this.$router.push(item.vuePath)
      this.$store.commit('selectMenu', item)
    }

  },
  computed: {
    noChildren(){
      return this.menu.filter(data=>data.children.length===0)
    },
    hasChildren(){
      return this.menu.filter(data=>data.children.length!==0)
    },
    isCollapsed(){
      return this.$store.state.tab.isCollapsed
    }

  },
  mounted() {
    getRequest("/user/menu/").then((res)=>{
          this.menu=res
        }
    )
  }
}
</script>
