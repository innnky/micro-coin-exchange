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
    <el-form-item label="用户id" prop="userId">
        <el-input v-model="dataForm.userId" placeholder="用户id"></el-input>
    </el-form-item>
    <el-form-item label="交易对符号" prop="symbol">
        <el-input v-model="dataForm.symbol" placeholder="交易对符号"></el-input>
    </el-form-item>
    <el-form-item label="交易方向" prop="side">
        <el-input v-model="dataForm.side" placeholder="交易方向"></el-input>
    </el-form-item>
    <el-form-item label="交易类型" prop="type">
        <el-input v-model="dataForm.type" placeholder="交易类型"></el-input>
    </el-form-item>
    <el-form-item label="交易价格" prop="price">
        <el-input v-model="dataForm.price" placeholder="交易价格"></el-input>
    </el-form-item>
    <el-form-item label="交易数量" prop="quantity">
        <el-input v-model="dataForm.quantity" placeholder="交易数量"></el-input>
    </el-form-item>
    <el-form-item label="委托单状态" prop="orderStatus">
        <el-input v-model="dataForm.orderStatus" placeholder="委托单状态"></el-input>
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
    import {getObj, addObj, putObj} from '@/api/order'

    export default {
    data () {
      return {
        visible: false,
        canSubmit: false,
        dataForm: {
          id: '',
          userId: '',
          symbol: '',
          side: '',
          type: '',
          price: '',
          quantity: '',
          orderStatus: '',
          createTime: '',
          updateTime: '',
          createBy: '',
          updateBy: '',
        },
        dataRule: {
          userId: [
            { required: true, message: '用户id不能为空', trigger: 'blur' }
          ],

          symbol: [
            { required: true, message: '交易对符号不能为空', trigger: 'blur' }
          ],

          side: [
            { required: true, message: '交易方向不能为空', trigger: 'blur' }
          ],

          type: [
            { required: true, message: '交易类型不能为空', trigger: 'blur' }
          ],

          price: [
            { required: true, message: '交易价格不能为空', trigger: 'blur' }
          ],

          quantity: [
            { required: true, message: '交易数量不能为空', trigger: 'blur' }
          ],

          orderStatus: [
            { required: true, message: '委托单状态不能为空', trigger: 'blur' }
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
