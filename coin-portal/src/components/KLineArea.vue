<template>
  <v-chart className="chart" :option="option"/>
</template>

<script>
import "echarts";


import VChart, {THEME_KEY} from "vue-echarts";
import {getRequest} from "@/api/data";

export default {
  name: "KLineArea",
  props: ["symbol"],
  components: {
    VChart
  },
  provide: {
    [THEME_KEY]: "dark"
  },
  data() {
    return {
      xs: [],
      kls: [],
      sdta: [],
      option: {
        xAxis: {
          data: [1,2,3,4]
        },
        yAxis: {},
        series: [
          {
            type: 'candlestick',
            data: []
          }
        ]
      }
    };
  },
  mounted() {
    setInterval(()=>{
      // this.sdta.push([38, 15, 5, 42])
      // this.option.series[0].data = this.sdta
      // console.log( this.option.series[0].data)
      this.refreshKline()
    },1000*2);
  },
  methods: {
    refreshKline(){
      getRequest(`/exchange/market/${this.symbol}/kline?level=1s`).then(res => {
        let xs = []
        let kls = []
        let data = res.data;
        for (let i = 0; i < data.length; i++) {
          xs.push("")
          kls.push([data[i].open, data[i].close, data[i].high, data[i].low])
        }
        // this.xs.push('')
        this.option.xAxis.data=xs
        this.option.series[0].data = kls
      })
    }
  }
};
</script>

<style scoped>
.chart {
  height: 400px;
  width: 600px;
}
</style>