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
    <el-form-item label="币种名称" prop="coinName">
        <el-input v-model="dataForm.coinName" placeholder="币种名称"></el-input>
    </el-form-item>
    <el-form-item label="币种系统余额" prop="balance">
        <el-input v-model="dataForm.balance" placeholder="币种系统余额"></el-input>
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
    import {getObj, addObj, putObj} from '@/api/coin'

    export default {
    data () {
      return {
        visible: false,
        canSubmit: false,
        dataForm: {
          id: '',
          coinName: '',
          balance: '',
          createTime: '',
          updateTime: '',
          createBy: '',
          updateBy: '',
        },
        dataRule: {
          coinName: [
            { required: true, message: '币种名称不能为空', trigger: 'blur' }
          ],

          balance: [
            { required: true, message: '币种系统余额不能为空', trigger: 'blur' }
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
