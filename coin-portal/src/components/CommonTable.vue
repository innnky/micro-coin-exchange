<template>
  <div class="common-table">
    <div class="row justify-content-between">
      <div class="col-3">      <i class="el-icon-search"></i><span class="smalltitle">筛选查询</span></div>
      <div class="col-1 ">      <el-button  class="" size="small">查询</el-button></div>
    </div>
    <el-table :data="tableData" height="90%" stripe>
      <el-table-column v-for="item in tableLabel" :key="item.prop" :label="item.label" :prop="item.prop"
                       :width="item.width?item.width:125" show-overflow-tooltip>
        <!--      <template slot-scope="scope">-->
        <!--        <span style="margin-left: 10px">{{scope.row[item.prop]}}</span>-->
        <!--      </template>-->
      </el-table-column>
      <el-table-column label="操作" min-width="180">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
          <el-button size="mini" type="danger" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination class="pager" layout="prev, pager, next" :total="conf.total" :current-page.sync="conf.page"
                   @current-change="changePage"></el-pagination>

  </div>
</template>

<script>
export default {
  props: {
    tableData: Array,
    tableLabel: Array,
    conf: Object
  },
  data() {
    return {
      maxpage: 6000
    }
  },
  methods: {
    handleEdit(row) {
      // console.log(row);
      this.$emit('edit', row)
    },
    handleDelete(row) {
      this.$emit('del', row)
    },
    changePage(page) {
      this.$emit('changePage', page)
    }
  },
  name: "CommonTable"
}
</script>

<style lang="less" scoped>
.common-table {
  height: calc(100% - 150px);
  background-color: white;
  position: relative;
  padding: 20px;
  .pager {
    position: absolute;
    bottom: 0;
    right: 20px;
  }
}
</style>
