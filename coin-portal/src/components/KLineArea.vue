<template>
  <v-chart className="chart" :option="option"/>
</template>

<script>
import "echarts";


import VChart, {THEME_KEY} from "vue-echarts";

export default {
  name: "KLineArea",
  components: {
    VChart
  },
  provide: {
    [THEME_KEY]: "dark"
  },
  data() {
    return {
      sdta: [],
      option: {
        xAxis: {
          data: ['2017-10-24', '2017-10-25', '2017-10-26', '2017-10-27']
        },
        yAxis: {},
        series: [
          {
            type: 'candlestick',
            data: [
              [20, 34, 10, 38],
              [40, 35, 30, 50],
              [31, 38, 33, 44],
              [38, 15, 5, 42]
            ]
          }
        ]
      }
    };
  },
  mounted() {
    // setInterval(()=>{
    //   this.sdta.push([38, 15, 5, 42])
    //   this.option.series[0].data = this.sdta
    //   console.log( this.option.series[0].data)
    // },1000*2);
  }
};
</script>

<style scoped>
.chart {
  height: 400px;
  width: 600px;
}
</style>