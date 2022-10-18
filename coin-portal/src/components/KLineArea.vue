<template>
  <div>
    <el-radio-group v-model="level">
      <el-radio-button label="1s"></el-radio-button>
      <el-radio-button label="30s"></el-radio-button>
      <el-radio-button label="15m"></el-radio-button>
    </el-radio-group>
    <v-chart className="chart" :option="option"/>
  </div>
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
      },
      level: "1s"
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
      getRequest(`/exchange/market/${this.symbol}/kline?level=${this.level}`).then(res => {
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