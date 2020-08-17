<template>
  <div class="hello">
    <el-container>
      <el-header style="width: 600px;margin: 0 auto;">
        <p>regist</p>
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
          <el-form-item label="确认密码" prop="checkPass" 
          :rules="[{ required: true, message: '请输入确认密码', trigger: 'blur' },{ validator: validatePass, trigger: 'blur' },{ min: 6, max: 12, message: '长度在 6 到 12 个字符', trigger: 'blur'}]">
            <el-input type="password" v-model="form.checkPass" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="年龄" prop="age" 
          :rules="[{ validator: validateAge, trigger: 'blur' }]">
            <el-input v-model.number="form.age"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit('form')">注册</el-button>
            <el-button type="primary" @click="login()">登录</el-button>
            <el-button @click="resetForm('form')">重置</el-button>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>
  </div>
</template>

<script>
export default {
  name: 'Regist',
  data() {
      return {
        form: {
          id: '',
          userName: '',
          passWord: '',
          checkPass: '',
          age: ''
        }
      }
    },
    methods: {
      validatePass(rule,value,callback) {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.form.passWord) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      },
      validateAge(rule,value,callback) {
        if (value < 18) {
          callback(new Error('必须年满18岁'));
        } else {
          callback();
        }
      },
      onSubmit(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.axios.post('/user/regist', this.form)
          .then(function (response) {
            console.log(response);
          })
          .catch(function (error) {
            console.log(error);
          });
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      login() {
        this.$router.push('/home')
      }
    }
}
</script>
<style scoped>
</style>
