<template>
  <div>
    <div class="container">
      <div class="row">
        <div class="col-8"><k-line-area style="height: 800px; width: 100%"/></div>
        <div class="col-4">
          <el-card class="box-card m-2">
            {{symbol}}
            <plate @price-changed="priceChanged"/>
          </el-card>
          <el-card class="box-card m-2">
            <el-switch
                class="m-2"
                style="display: block"
                v-model="side"
                active-color="#13ce66"
                inactive-color="#ff4949"
                active-text="买入"
                active-value="BUY"
                inactive-text="卖出"
                inactive-value="SELL">
            </el-switch>
            <el-select
                class="m-2"
                v-model="type" placeholder="挂单方式">
              <el-option
                  label="限价单"
                  value="limit">

              </el-option>
              <el-option
                  label="市价单"
                  value="market"></el-option>
            </el-select>
            <el-input
                class="m-2"
                v-model="price" placeholder="成交价格" style="width: 60%;" v-if="type ==='limit'"></el-input>
            <el-input
                class="m-2"
                v-model="marketPrice" placeholder="成交价格" style="width: 60%;" v-if="type ==='market'" disabled></el-input>
              <el-button @click="fillMarketPrice" v-if="type ==='limit'">当前价格</el-button>
            <el-input
                class="m-2"
                v-model="quantity" placeholder="成交数量" style="width: 60%;"></el-input>
            <div class="mx-2"><span>需花费:{{quantity*price}}</span></div>
            <div class="mx-2"><span>余额:{{balance}}</span></div>
            <br>
            <el-button
                class="m-2"
                @click="makeOrder">下单</el-button>
          </el-card>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import KLineArea from "@/components/KLineArea";
import Plate from "@/components/Plate";
import {getRequest, postRequest} from "@/api/data";

export default {
  name: "Trade",
  components:{
     KLineArea,Plate
  },
  data(){
    return {
      symbol: '',
      balance: 0,
      marketPrice: 0,
      side: "BUY",
      type: "limit",
      price: 0,
      quantity: 0,
      tradePlate: {}
    }
  },
  mounted() {
    this.symbol = this.$route.query.symbol;
    if(this.symbol.length > 0) {
      getRequest("/exchange/userassets/available", {
        symbol: this.symbol
      }).then(res => {
        this.balance = res.data
      })
    }
  },
  methods: {
    priceChanged(price){
      this.marketPrice = price
    },
    fillMarketPrice(){
      this.price = this.marketPrice
    },
    makeOrder(){
      const data = {
        symbol:this.symbol,
        side:this.side,
        type:this.type,
        price:this.price,
        quantity:this.quantity
      }
      postRequest("/exchange/order/new", data)
    }
  }
}
</script>

<style scoped>

</style>