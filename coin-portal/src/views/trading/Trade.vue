<template>
  <div>
    <div class="container">
      <div class="row">
        <div class="col-8"><k-line-area style="height: 800px; width: 100%"/></div>
        <div class="col-4">
          <el-card class="box-card m-2">
            <plate/>
          </el-card>
          <el-card class="box-card m-2">
            <el-switch
                class="m-2"
                style="display: block"
                v-model="side"
                active-color="#13ce66"
                inactive-color="#ff4949"
                active-text="买入"
                inactive-text="卖出">
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
                v-model="input" placeholder="成交价格" style="width: 60%;"></el-input><el-button >当前价格</el-button>
            <el-input
                class="m-2"
                v-model="input" placeholder="成交数量" style="width: 60%;"></el-input>
            <div class="mx-2"><span>余额:</span></div>
            <br>
            <el-button
                class="m-2"
                @click="changeData">下单</el-button>
          </el-card>
        </div>
      </div>
    </div>





  </div>
</template>

<script>
import KLineArea from "@/components/KLineArea";
import Plate from "@/components/Plate";

export default {
  name: "Trade",
  components:{
     KLineArea,Plate
  }
}
</script>

<style scoped>

</style>