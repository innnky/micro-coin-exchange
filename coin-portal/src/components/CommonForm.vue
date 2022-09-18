<template>
<el-form ref="form" label-width="100px" :model="form" :inline="inline">
  <el-form-item v-for="item in formLabel" :key="item.label">
    <el-input v-if="item.type === 'input'" :placeholder="'请输入'+item.label" v-model="form[item.model]"></el-input>
    <el-switch v-if="item.type === 'switch'"  v-model="form[item.model]"></el-switch>
    <el-date-picker v-if="item.type === 'date'" type="date" value-format="yyyy-MM-dd" placeholder="选择日期" v-model="form[item.model]"></el-date-picker>
    <el-select v-if="item.type === 'select'"  placeholder="请选择" v-model="form[item.model]">
      <el-option v-for="it in item.opts" :key="it.value" :label="it.label" :value="it.value"></el-option>
    </el-select>
  </el-form-item>
  <el-form-item><slot></slot></el-form-item>
</el-form>
</template>

<script>
export default {
  props:{
    formLabel:Array,
    form:Object,
    inline:Boolean
  },
  name: "CommonForm"
}
</script>

<style lang="less" scoped>
.el-form-item{
  margin-top: 10px;
  margin-bottom: 10px;
}
</style>
