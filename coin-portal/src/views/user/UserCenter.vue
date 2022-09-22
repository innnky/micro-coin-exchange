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
      quantity: 0
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
        console.log(this.coins)
      })
    },
    doCharge(){
      //TODO 校验输入金额数据 以及浮点数精度问题
      postRequest("/exchange/userassets/charge", {
        coinId:this.coinId,
        quantity: this.quantity
      })

    }
  },
  mounted() {
    this.getCoins()
  }
}
</script>

<style scoped>

</style>