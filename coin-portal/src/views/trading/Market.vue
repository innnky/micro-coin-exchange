<template>
  <div>
    <el-table
        :data="tableData"
        style="width: 100%"
        @cell-click="lineClicked">
      <el-table-column
          prop="symbol"
          label="交易对">
      </el-table-column>
      <el-table-column
          prop="price"
          label="价格">
      </el-table-column>
      <el-table-column
          prop="delta24h"
          label="24小时涨跌">
      </el-table-column>
    </el-table>
    <el-pagination
        layout="prev, pager, next"
        :total="total"
        @current-change="currentChanged">
    </el-pagination>
  </div>
</template>

<script>
import {getRequest} from "@/api/data";

export default {
  name: "market",
  data(){
    return {
      tableData: [],
      total: 0,
    }
  },
  created() {
    getRequest("/exchange/market/page",{
      size: 10,
      current:0
    }).then(res => {
      this.tableData = res.data.records
      this.total = res.data.total
    })
  },
  methods: {
    currentChanged(page){
      getRequest("/exchange/market/page",{
        size: 10,
        current:page
      }).then(res => {
        this.tableData = res.data.records
        this.total = res.data.total
      })
    },
    lineClicked(row, column, cell, event){
      row, column, cell, event
      console.log(row.symbol)
      this.$router.push(`/trade?symbol=${row.symbol}`)
    }

  }
}
</script>

<style scoped>

</style>