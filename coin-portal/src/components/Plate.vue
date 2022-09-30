<template>
  <div style="width: 100%; height: 300px; ">
    <div v-for="obj in sellDepth" class="w-100 d-flex justify-content-between" style="height: 20px" :key="obj.price">
      <div class="" ><span>{{obj.price}}</span></div>
      <div class="bg-danger" :style="{width: obj.percentage}"><span style="float: right">{{ obj.quantity }}</span></div>
    </div>
    <p style="text-align: center;font-size: 30px;margin-bottom: 0">{{marketPrice}}</p>
    <div v-for="obj in buyDepth" class="w-100 d-flex justify-content-between" style="height: 20px" :key="obj.price">
      <div class="" ><span>{{obj.price}}</span></div>
      <div class="bg-info" :style="{width: obj.percentage}"><span style="float: right">{{ obj.quantity }}</span></div>
    </div>
  </div>
</template>

<script>
import {getRequest} from "@/api/data";

export default {
  name: "Plate",
  props: ["symbol"],
  data() {
    return {
      marketPrice: '',
      buyDepth: [
      ],
      sellDepth: [
      ]
    };
  },
  methods: {
    priceChange(){
      this.$emit("price-changed", this.marketPrice)
    },
    refreshMarketPrice(){
      getRequest(`/exchange/market/${this.symbol}/marketprice`).then(res=>{
        this.marketPrice = res.data
        this.$emit("price-changed", this.marketPrice)
      })
    },
    refreshDepthPrice(){
      getRequest(`/exchange/market/${this.symbol}/depth`).then(res=>{
        this.depth = res.data
        let tempBuyList = []
        let tempSellList = []

        let max = 0
        for (let key in res.data.buyItems) {
          let quantity = res.data.buyItems[key];
          tempBuyList.push({price: key, quantity: quantity})
          if(quantity>max){
            max = quantity
          }
        }
        for (let key in res.data.sellItems) {
          let quantity = res.data.sellItems[key];
          tempSellList.push({price: key, quantity: quantity})
          if(quantity>max){
            max = quantity
          }
        }

        if (tempBuyList.length >6){
          tempBuyList = tempBuyList.slice(0,6)
        }
        if (tempSellList.length >6){
          tempSellList = tempSellList.slice(0,6)
        }

        for (let i = 0; i < tempBuyList.length; i++) {
          tempBuyList[i].percentage = parseInt(100 * tempBuyList[i].quantity / max) + "%"
        }
        for (let i = 0; i < tempSellList.length; i++) {
          tempSellList[i].percentage = parseInt(100 * tempSellList[i].quantity / max) + "%"
        }

        this.buyDepth = tempBuyList.reverse()
        this.sellDepth = tempSellList.reverse()

      })
    }

  },
  mounted() {
    setInterval(() => {
      this.refreshMarketPrice()
      this.refreshDepthPrice()
      this.priceChange()
    },1000)
  }
}
</script>

<style scoped>

</style>