<template>
  <div class="course">
    <el-container>
      <el-header style="text-align:center;height:150px">
        <h1>Course List</h1>
        <div style="position:absolute;top:10px;right:10px;"><el-button @click="logOut">Log Out</el-button></div>
        <div style="position:absolute;top:10px;left:10px;"><el-button @click="back">Back</el-button></div>
        <el-row>
          <el-col :span="12" :offset="6">
            <el-input
              placeholder="Enter anything of Course"
              prefix-icon="el-icon-search"
              v-model="searchValue"
              @input="doSearch">
            </el-input>
          </el-col>
          <el-col :span="6">
            <!-- <div style="padding:0px;margin:0px;width:70px;top:15px;right:15px;position:absolute;">
              <el-button icon="el-icon-document-add" circle size="mini" @click="addAlbum"></el-button>
              <el-button icon="el-icon-document" circle size="mini" ></el-button>
            </div> -->
          </el-col>
        </el-row>
      </el-header>
      <el-main>
        <div v-if="courses.length>0">
          <el-row v-for="course in courses" :key="course.courseNum">
            <el-card shadow="hover" style="height:auto">
              <el-row>
                <el-col :span="18">
                  <div>Course Name: {{course.courseNum}} </div>
                  <div>Tilte: {{course.title}} </div>
                  <div>Professor: {{course.professor}} </div>
                  <div>Description: {{course.description}}</div>
                  <div>Programming Language: {{course.keyWord}}</div>
                </el-col>
                <el-col :span="2">                
                </el-col>
                <el-col :span="4">
                  <div style="padding:0px;margin:0px;width:30px;top:0;right:0;position:absolute;">
                    <div><el-button type="warning" icon="el-icon-s-comment" circle size="mini" @click="checkComment(course.courseNum)"></el-button></div>
                    <div><el-button type="success" icon="el-icon-notebook-1" circle size="mini" @click="checkBook(course.courseNum)"></el-button></div>
                    <!-- <div><el-button type="danger" icon="el-icon-delete" circle size="mini" @click="deleteAlbum(album.code)"></el-button></div> -->
                  </div>
                </el-col>
              </el-row>
            </el-card>
          </el-row>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'CourseView',
  props: {
    msg: String
  },
  data() {
    return {
      defaultCourses: [],
      albums: [],
      searchValue: "",
      result: "",
      enroll: [],
      courses: []
    }
  },
  methods: {
    doSearch() {
      if(this.searchValue) {
        this.courses = this.courses.filter(course => course.professor.includes(this.searchValue)||course.courseNum.includes(this.searchValue)||course.title.includes(this.searchValue));
      } else {
        this.courses = this.defaultCourses;
      }
    },
    logOut() {
      localStorage.clear();
      this.$router.push('/');
    },
    back() {
      this.$router.push('/Homeview');
    },
    checkComment(code) {
      this.$router.push({path:`/comments/${code}`});
    },
    checkBook(code) {
      this.$router.push({path:`/books/${code}`});
    },
    async deleteAlbum(code) {
      await axios.delete(`http://localhost:8866/albums/${code}`).then((res)=>{
        this.result = res.data;
      });
      if(this.result) {
        this.$notify({
          title: 'Success',
          message: 'Update success',
          type: 'success'
        });
      } else {
        this.$notify.error({
          title: 'Error',
          message: 'Update failed'
        });
      }
      this.initData();
    },
    initData() {
      axios.get("http://localhost:8866/albums").then((res)=>{
      this.albums = res.data;
      this.defaultAlbums = res.data;
      });
    }
  },
  mounted() {
    this.initData();
  },
  created() {
    axios.get('http://localhost:8899/user/enrollment/'+localStorage.getItem('username'),
    {
      headers: {
        'token': localStorage.getItem('token'),
      }
    })
    .then(res => {
      let enrollClass = res.data;
      this.enroll = enrollClass.split(',');
      let storeCourses = JSON.parse(localStorage.getItem('courses'));
      for(let i=0;i<this.enroll.length;i++) {
        for(let j=0;j<storeCourses.length;j++) {
          if(storeCourses[j].courseNum == this.enroll[i]) {
            this.courses.push(storeCourses[j]);
          }
        }
      }
    })
  },
}
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.course {
  height: 100%;
}

.el-container {
  background-color: #FFFACD;
  height:100%;
}

.el-row {
  padding: 10px;
}
</style>
