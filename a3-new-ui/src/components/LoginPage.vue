<template>
  <div class="login">
    <div><h1>Login Page</h1></div>
    <div>
    <el-form label-position="top" :rules="rules" ref="ruleForm" label-width="100px" :model="userinfo">
      <el-form-item label="UserName" prop="username">
        <el-input v-model="userinfo.username"></el-input>
      </el-form-item>
      <el-form-item label="Password" prop="password">
        <el-input v-model="userinfo.password" type="password"></el-input>
      </el-form-item>
    
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">Login</el-button>
        <el-button @click="resetForm('ruleForm')">Reset</el-button>
        <el-button @click="goRegist">Go Register</el-button>
      </el-form-item>
    </el-form>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'LoginPage',
  data() {
    return {
      userinfo: {
        username: null,
        password: null
      },
      rules: {
        username: [
          { required: true, message: 'Please input user name', trigger: 'blur' },
          { min: 5, max: 15, message: 'Length should be 5 to 15', trigger: 'blur' }
        ],
        password: [
          { required: true, message: 'Please input password', trigger: 'blur' },
          { min: 5, max: 20, message: 'Length should be 5 to 20', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios({
            method: 'post',
            url: 'http://localhost:8899/user/login',
            data: {
                username: this.userinfo.username,
                password: this.userinfo.password
            },
            transformRequest: [
                function (data) {
                  let ret = ''
                  for (let it in data) {
                      ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
                  }
                  ret = ret.substring(0, ret.lastIndexOf('&'));
                  return ret
                }
              ],
              headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
              }
          })
          .then((res) => {
            if (res.data.status == 200) {
              this.$notify({
                title: "Success",
                message: "Login success",
                type: "success",
              });
              localStorage.setItem('username', this.userinfo.username);
              localStorage.setItem('token',res.data.entity.token);
              if(this.userinfo.username != "admin"){
                setTimeout(() => {
                this.$router.push({path : '/Homeview'})
                },1000);
              } else {
                setTimeout(() => {
                this.$router.push({path : '/Managecomment'})
                },1000);
              } 
            } else {
              this.$notify.error({
                title: "Error",
                message: "UserName or Password is invalid",
              });
            }
          });
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    goRegist() {
      this.$router.push({path: '/Register'});
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  },
}
</script>

<style>

</style>