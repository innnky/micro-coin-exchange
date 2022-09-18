<template>
  <div class="t">
    <el-tag v-for="(tag, index) in tags"
            :key="tag.name" size="small" :closable="tag.name!=='home'"
            @click="clickMenu(tag)"
            @close="closeTag(tag,index)"
            :effect="$route.name === tag.name?'dark':'plain'">{{ tag.label }}
    </el-tag>
  </div>
</template>

<script>
import {mapState, mapMutations} from "vuex";

export default {
  name: "CommonTags",
  computed: {
    ...mapState({
      tags: state => state.tab.tabList
    })
  },
  methods:{
    clickMenu(tag) {
      this.$router.push(tag.path)
    },
    closeTag(tag,index){
      // console.log(i);
      const length = this.tags.length -1
      this.deleteMenu(tag)
      if (tag.name !== this.$route.name){
        return;
      }else if(index === length){
        this.$router.push(this.tags[index-1].name)
      }else {
        this.$router.push(this.tags[index].name)
      }
    },
    ...mapMutations({
      deleteMenu:'deleteMenu'
    })
  }
}
</script>

<style lang="less" scoped>
.t{
  padding: 10px;
  .el-tag{
    margin-right: 5px;
    cursor: pointer;
  }
}

</style>
