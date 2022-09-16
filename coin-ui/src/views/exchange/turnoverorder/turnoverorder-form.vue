<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    append-to-body
    :close-on-click-modal="false"
    @close="closeDialog()"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="id" prop="id" v-if="dataForm.id">
        <el-input v-model="dataForm.id" placeholder="id" disabled></el-input>
    </el-form-item>
    <el-form-item label="交易对符号" prop="symbol">
        <el-input v-model="dataForm.symbol" placeholder="交易对符号"></el-input>
    </el-form-item>
    <el-form-item label="成交价格" prop="price">
        <el-input v-model="dataForm.price" placeholder="成交价格"></el-input>
    </el-form-item>
    <el-form-item label="成交数量" prop="quantity">
        <el-input v-model="dataForm.quantity" placeholder="成交数量"></el-input>
    </el-form-item>
    <el-form-item label="买方订单id" prop="buyOrderId">
        <el-input v-model="dataForm.buyOrderId" placeholder="买方订单id"></el-input>
    </el-form-item>
    <el-form-item label="卖方订单id" prop="sellOrderId">
        <el-input v-model="dataForm.sellOrderId" placeholder="卖方订单id"></el-input>
    </el-form-item>
    <el-form-item label="createTime" prop="createTime" v-if="dataForm.id">
        <el-input v-model="dataForm.createTime" placeholder="createTime" disabled></el-input>
    </el-form-item>
    <el-form-item label="updateTime" prop="updateTime" v-if="dataForm.id">
        <el-input v-model="dataForm.updateTime" placeholder="updateTime" disabled></el-input>
    </el-form-item>
    <el-form-item label="createBy" prop="createBy" v-if="dataForm.id">
        <el-input v-model="dataForm.createBy" placeholder="createBy" disabled></el-input>
    </el-form-item>
    <el-form-item label="updateBy" prop="updateBy" v-if="dataForm.id">
        <el-input v-model="dataForm.updateBy" placeholder="updateBy" disabled></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()" v-if="canSubmit">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
    import {getObj, addObj, putObj} from '@/api/turnoverorder'

    export default {
    data () {
      return {
        visible: false,
        canSubmit: false,
        dataForm: {
          id: '',
          symbol: '',
          price: '',
          quantity: '',
          buyOrderId: '',
          sellOrderId: '',
          createTime: '',
          updateTime: '',
          createBy: '',
          updateBy: '',
        },
        dataRule: {
          symbol: [
            { required: true, message: '交易对符号不能为空', trigger: 'blur' }
          ],

          price: [
            { required: true, message: '成交价格不能为空', trigger: 'blur' }
          ],

          quantity: [
            { required: true, message: '成交数量不能为空', trigger: 'blur' }
          ],

          buyOrderId: [
            { required: true, message: '买方订单id不能为空', trigger: 'blur' }
          ],

          sellOrderId: [
            { required: true, message: '卖方订单id不能为空', trigger: 'blur' }
          ],

        }
      }
    },
    methods: {
      init (id) {
        this.visible = true;
        this.canSubmit = true;
        this.$nextTick(() => {
            this.$refs['dataForm'].resetFields()
            if (id) {
            getObj(id).then(response => {
                this.dataForm = response.data.data
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.canSubmit = false;
            if (this.dataForm.id) {
                putObj(this.dataForm).then(data => {
                    this.$notify.success('修改成功')
                    this.visible = false
                    this.$emit('refreshDataList')
                }).catch(() => {
                    this.canSubmit = true;
                });
            } else {
                addObj(this.dataForm).then(data => {
                    this.$notify.success('添加成功')
                    this.visible = false
                    this.$emit('refreshDataList')
                }).catch(() => {
                    this.canSubmit = true;
                });
            }
          }
        })
      },
      //重置表单
      closeDialog() {
          this.$refs["dataForm"].resetFields()
      }
    }
  }
</script>
