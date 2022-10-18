<template>
  <div>
    <div class="container">
      <div class="row">
        <div class="col-8">
          <k-line-area style="height: 800px; width: 100%" :symbol="symbol"/>
        </div>
        <div class="col-4">
          <el-card class="box-card m-2">
            {{symbol}}
            <plate @price-changed="priceChanged" :symbol="symbol"/>
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
                inactive-value="SELL"
                @change="getBalance">
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
            <div class="mx-2"><span>需花费:{{getNeeds()}}</span></div>
            <div class="mx-2"><span>余额:{{balance}}</span></div>
            <br>
            <el-button
                class="m-2"
                @click="makeOrder">下单</el-button>
          </el-card>
        </div>
      </div>
    </div>
    <el-table
        :data="dataList"
        border>
      <el-table-column
          prop="id"
          header-align="center"
          align="center"
          label="id">
      </el-table-column>
      <el-table-column
          prop="userId"
          header-align="center"
          align="center"
          label="用户id">
      </el-table-column>
      <el-table-column
          prop="symbol"
          header-align="center"
          align="center"
          label="交易对符号">
      </el-table-column>
      <el-table-column
          prop="side"
          header-align="center"
          align="center"
          label="交易方向">
      </el-table-column>
      <el-table-column
          prop="type"
          header-align="center"
          align="center"
          label="交易类型">
      </el-table-column>
      <el-table-column
          prop="price"
          header-align="center"
          align="center"
          label="交易价格">
      </el-table-column>
      <el-table-column
          prop="quantity"
          header-align="center"
          align="center"
          label="交易数量">
      </el-table-column>
      <el-table-column
          prop="orderStatus"
          header-align="center"
          align="center"
          label="委托单状态">
      </el-table-column>
      <el-table-column
          prop="createTime"
          header-align="center"
          align="center"
          label="createTime">
      </el-table-column>
      <el-table-column
          prop="updateTime"
          header-align="center"
          align="center"
          label="updateTime">
      </el-table-column>
      <el-table-column
          prop="createBy"
          header-align="center"
          align="center"
          label="createBy">
      </el-table-column>
      <el-table-column
          prop="updateBy"
          header-align="center"
          align="center"
          label="updateBy">
      </el-table-column>
      <el-table-column
          header-align="center"
          align="center"
          label="操作">

      </el-table-column>
    </el-table>
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
      tradePlate: {},
      dataList:[]
    }
  },
  mounted() {

    this.getBalance()
    this.createTimers()
    this.getMyOpenOrders()
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
      postRequest("/exchange/order/new", data).then(res=>{
        res
        this.getBalance()
      })
    },
    createTimers(){
      setInterval(() => {
        // this.refreshMarketPrice()
      },1000)
    },
    getMyOpenOrders(){
      getRequest(`/exchange/order/my?symbol=${this.symbol}`).then(res=>{
        this.dataList = res.data
      })
    },
    getBalance(){
      this.symbol = this.$route.query.symbol;
      if(this.symbol.length > 0) {
        getRequest("/exchange/userassets/available", {
          symbol: this.symbol,
          side: this.side
        }).then(res => {
          this.balance = res.data
        })
      }
    },
    getNeeds(){
      if (this.side ==="BUY"){
        return this.quantity*this.price
      }
      else {
        return this.quantity
      }
    }

  }
}
</script>

<style scoped>

</style>