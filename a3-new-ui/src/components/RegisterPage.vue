<template>
  <div class="register">
    <div><h1>Register Page</h1></div>
    <div>
    <el-form label-position="top" :rules="rules" ref="ruleForm" label-width="100px" :model="userinfo">
      <el-form-item label="UserName" prop="username">
        <el-input v-model="userinfo.username"></el-input>
      </el-form-item>
      <el-form-item label="Password" prop="password">
        <el-input v-model="userinfo.password" type="password"></el-input>
      </el-form-item>
    
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">Create</el-button>
        <el-button @click="resetForm('ruleForm')">Reset</el-button>
        <el-button @click="goLogin">Already have account</el-button>
      </el-form-item>
    </el-form>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'RegisterPage',
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
            url: 'http://localhost:8899/user/register',
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
            if (res.status == 200) {
              this.$notify({
                title: "Success",
                message: res.data,
                type: "success",
              });
            }
          })
          .catch(() => {
            this.$notify.error({
              title: "Error",
              message: "UserName is duplicate",
            });
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
    goLogin() {
      this.$router.push({path: '/Login'});
    }
  },
}
</script>

<style>

</style>