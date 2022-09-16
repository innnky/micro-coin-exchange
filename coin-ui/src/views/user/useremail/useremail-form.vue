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
    <el-form-item label="邮件目的地" prop="sendTo">
        <el-input v-model="dataForm.sendTo" placeholder="邮件目的地"></el-input>
    </el-form-item>
    <el-form-item label="邮件发送方" prop="sendFrom">
        <el-input v-model="dataForm.sendFrom" placeholder="邮件发送方"></el-input>
    </el-form-item>
    <el-form-item label="邮件标题" prop="title">
        <el-input v-model="dataForm.title" placeholder="邮件标题"></el-input>
    </el-form-item>
    <el-form-item label="邮件内容" prop="content">
        <el-input v-model="dataForm.content" placeholder="邮件内容"></el-input>
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
    import {getObj, addObj, putObj} from '@/api/useremail'

    export default {
    data () {
      return {
        visible: false,
        canSubmit: false,
        dataForm: {
          id: '',
          userId: '',
          sendTo: '',
          sendFrom: '',
          title: '',
          content: '',
          createTime: '',
          updateTime: '',
          createBy: '',
          updateBy: '',
        },
        dataRule: {
          userId: [
            { required: true, message: '用户id不能为空', trigger: 'blur' }
          ],

          sendTo: [
            { required: true, message: '邮件目的地不能为空', trigger: 'blur' }
          ],

          sendFrom: [
            { required: true, message: '邮件发送方不能为空', trigger: 'blur' }
          ],

          title: [
            { required: true, message: '邮件标题不能为空', trigger: 'blur' }
          ],

          content: [
            { required: true, message: '邮件内容不能为空', trigger: 'blur' }
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
