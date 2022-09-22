<template>
  <div style="width: 100%; height: 300px; ">
    <div v-for="i in [1,2,3,4,5,6]" class=" w-100" style="height: 20px" :key="i">
      <div style="width: 60%; height: 20px" class="bg-info ms-auto">
        <p style="text-align: end; ">aaa</p>
      </div>
    </div>
    <p style="text-align: center;font-size: 30px;margin-bottom: 0">{{marketPrice}}</p>
    <div v-for="i in [1,2,3,4,5,6]" class="w-100" style="height: 20px" :key="i">
      <div :style="{width: '80%'}" class="bg-danger ms-auto h-100">
        <p style="text-align: end; ">aaa</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Plate",
  data() {
    return {
      marketPrice: 666
    };
  },
  methods: {
    priceChange(){
      // this.marketPrice = 1000
      this.$emit("price-changed", this.marketPrice)
    }

  },
  mounted() {
    this.priceChange()
  }
}
</script>

<style scoped>

</style>