<template>
  <div class="home">
    <div>
      <h1>Welcome {{username}} </h1>
      <div style="position:absolute;top:10px;right:10px;"><el-button @click="logOut">Log Out</el-button></div>
    </div>
    <div>
      <h2>Please select one or more course that you are interested :</h2>
      <el-checkbox-group v-model="checkCourses">
        <el-checkbox v-for="course in courses" :label="course.courseNum" :key="course.courseNum">
          {{course.courseNum}}
        </el-checkbox>
      </el-checkbox-group>
    </div>
    <div>
      <h2>Selected courses :</h2>
      {{checkCourses}}
    </div>
    <div v-if="checkCourses.length>0" style="margin-top:15px"><el-button @click="selectClass">Confirm</el-button></div>

  </div>
</template>

<script>
import axios from 'axios';
export default {
  name: 'HomeView',
  data() {
    return {
      username: null,
      courses: null,
      checkCourses: []
    }
  },
  created() {
    this.username = localStorage.getItem('username');
    axios.get('http://localhost:8899/courses/getAllCourse',
    {
      headers: {
        'token': localStorage.getItem('token'),
      }
    })
    .then(res => {
      this.courses = res.data;
      localStorage.setItem('courses', JSON.stringify(res.data));
    });

    axios.get('http://localhost:8899/user/enrollment/'+localStorage.getItem('username'),
    {
      headers: {
        'token': localStorage.getItem('token'),
      }
    })
    .then(res => {
      let cList = res.data.split(",");
      this.checkCourses = cList.filter((id) => id!="");
    });
  },
  methods: {
    logOut() {
      localStorage.clear();
      this.$router.push('/');
    },
    selectClass() {
      if(this.checkCourses.length>0) {
        let classes = "";
        for(let i=0;i<this.checkCourses.length;i++) {
          classes += this.checkCourses[i]+ ","
        }
        axios({
          method: 'put',
          url: 'http://localhost:8899/user/enrollment/'+localStorage.getItem('username'),
          data: {
              enrollment: classes 
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
              'token': localStorage.getItem('token'),
              'Content-Type': 'application/x-www-form-urlencoded'
            }
        })
        .then((res) => {
          if (res.status == 200) {
            this.$notify({
              title: "Success",
              message: "courses enrolled",
              type: "success",
            });
            setTimeout(() => {
              this.$router.push({path:'/CourseView'});
            },1000);
          }
        })
        .catch(() => {
          this.$notify.error({
            title: "Error",
            message: "Something is wrong",
          });
        });
      }
    }
  },
}
</script>

<style scoped>
.home {
  height: 100%;
}
</style>