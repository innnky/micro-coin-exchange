<template>
<div>
  <el-form>
    <el-form-item label="充值币种">
    <el-select v-model="coinId" placeholder="请选择">
      <el-option
          v-for="item in coins"
          :key="item.value"
          :label="item.label"
          :value="item.value">
      </el-option>
    </el-select>
    </el-form-item>
    <el-form-item label="数量">
      <el-input v-model="quantity"></el-input>
    </el-form-item>
    <el-button @click="doCharge">充值</el-button>
  </el-form>
  我的余额
  <el-table :data="userAssets">
    <el-table-column prop="name" label="币种名称">
    </el-table-column>
    <el-table-column prop="amount" label="数量"></el-table-column>
  </el-table>

</div>
</template>

<script>
import {getRequest, postRequest} from "@/api/data";

export default {
  name: "UserCenter",
  data(){
    return {
      coinId: '',
      coins: [],
      quantity: 0,
      userAssets: []
    }
  },
  methods: {
    getCoins(){
      getRequest('/exchange/coin/page', {

      }).then(res =>{
        this.coins =  res.data.records.map(a=>{
          return {
            value :a.id,
            label: a.coinName
          }
        })
      })
    },
    doCharge(){
      //TODO 校验输入金额数据 以及浮点数精度问题
      postRequest("/exchange/userassets/charge", {
        coinId:this.coinId,
        quantity: this.quantity
      }).then(()=>{
        this.getAllCoins()
      })

    },
    getAllCoins(){
      getRequest("/exchange/userassets/user").then(res=>{
        this.userAssets = res.data
      })
    }
  },
  mounted() {
    this.getCoins()
    this.getAllCoins()
  }
}
</script>

<style scoped>

</style>