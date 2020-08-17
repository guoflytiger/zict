<template>
  <div>
    <el-container>
      <el-header style="width: 600px;margin: 0 auto;">
        <p>login</p>
      </el-header>
      <el-main style="width: 600px;margin: 0 auto;">
        <el-form ref="form" :model="form" label-width="80px">
          <el-form-item label="用户名" prop="userName" 
          :rules="[{ required: true, message: '请输入用户名', trigger: 'blur' },{ min: 3, max: 8, message: '长度在 3 到 8 个字符', trigger: 'blur'}]">
            <el-input v-model="form.userName" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="passWord" 
          :rules="[{ required: true, message: '请输入密码', trigger: 'blur' },{ min: 6, max: 12, message: '长度在 6 到 12 个字符', trigger: 'blur'}]">
            <el-input type="password" v-model="form.passWord" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="login('form')">登录</el-button>
            <el-button type="primary" @click="onSubmit()">注册</el-button>
            <el-button @click="resetForm('form')">重置</el-button>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>
  </div>
</template>
<script>
export default {
  name: 'Login',
  data() {
    return {
      form: {
        id: '',
        userName: '',
        passWord: '',
      }
    }
  },
  methods: {
    onSubmit() {
      this.$router.push('/regist')
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    login(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.axios.post('/user/login', this.form)
        .then(function (response) {
          console.log(response);
          this.$message({
          message: '登录成功!',
          type: 'success'
        });
        })
        .catch(function (error) {
          console.log(error);
        });
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    }
  }
}
</script>