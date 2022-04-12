<template>
  <div class="manage-course">
    <el-container>
      <el-header style="text-align:center;height:150px">
        <h1>Manage Course List</h1>
        <div style="position:absolute;top:10px;right:10px;"><el-button @click="logOut">Log Out</el-button></div>
        <div style="position:absolute;top:10px;left:10px;"><el-button @click="back">Comments</el-button></div>
        <el-row>
          <el-col :span="12" :offset="6">
            <el-input
              placeholder="Enter anything"
              prefix-icon="el-icon-search"
              v-model="searchValue"
              @input="doSearch">
            </el-input>
          </el-col>
          <el-col :span="6">
            <div style="padding:0px;margin:0px;width:70px;top:15px;right:15px;position:absolute;">
              <el-button icon="el-icon-document-add" circle size="mini" @click="dialogVisible = true"></el-button>
            </div>
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
                    <div><el-button type="danger" icon="el-icon-delete" circle size="mini" @click="deleteCourse(course.courseNum)"></el-button></div>
                  </div>
                </el-col>
              </el-row>
            </el-card>
          </el-row>
        </div>
      </el-main>
      <el-dialog
        title="Add New Course"
        :visible.sync="dialogVisible"
        width="50%"
        >
        <el-form label-position="left" label-width="150px" :model="newCourse">
          <el-form-item label="Course ID">
            <el-input v-model="newCourse.courseNum"></el-input>
          </el-form-item>
          <el-form-item label="Course Title">
            <el-input v-model="newCourse.title"></el-input>
          </el-form-item>
          <el-form-item label="Course Professor">
            <el-input v-model="newCourse.professor"></el-input>
          </el-form-item>
          <el-form-item label="Course Key">
            <el-input v-model="newCourse.keyWord"></el-input>
          </el-form-item>
          <el-form-item label="Course Description">
            <el-input type="textarea" v-model="newCourse.description" maxlength="90" show-word-limit></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">Cancel</el-button>
          <el-button type="primary" @click="addCourse">Confirm</el-button>
        </span>
      </el-dialog>
    </el-container>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'ManageCourse',
  data() {
    return {
      newCourse: {
        courseNum: "",
        title: "",
        professor: "",
        description: "",
        keyWord: "",
        cover: ""
      },
      searchValue: "",
      result: "",
      enroll: [],
      courses: [],
      defaultCourses: [],
      courseNum: "",
      comment: "",
      commentList: [],
      defaultComList: [],
      dialogVisible: false
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
      this.$router.push('/Managecomment');
    },
    async deleteCourse(classId) {
      await axios.delete('http://localhost:8899/courses/deleteCourse/'+classId,
              {
                headers: {
                  'token': localStorage.getItem('token'),
                }
              })
      .then((res)=>{
        this.result = res.data;
      });
      if(this.result) {
        this.$notify({
          title: 'Success',
          message: 'Delete success',
          type: 'success'
        });
      } else {
        this.$notify.error({
          title: 'Error',
          message: 'Delete failed'
        });
      }
      this.initData();
    },
    async addCourse() {
      await axios.post('http://localhost:8899/courses/createCourse?courseNum='+this.newCourse.courseNum+
      '&title='+this.newCourse.title+'&professor='+this.newCourse.professor+'&description='+this.newCourse.description+
      '&keyWord='+this.newCourse.keyWord+'&cover=null',null,
      {
        headers: {
          'token': localStorage.getItem('token')
        }
      })
      .then((res)=>{
        this.result = res.data;
        this.dialogVisible = false;
      });
      if(this.result) {
        this.$notify({
          title: 'Success',
          message: 'Add success',
          type: 'success'
        });
      } else {
        this.$notify.error({
          title: 'Error',
          message: 'Add failed'
        });
      }
      this.initData();
    },
    initData() {
      axios.get('http://localhost:8899/courses/getAllCourse',
      {
        headers: {
          'token': localStorage.getItem('token'),
        }
      })
      .then(res => {
        this.courses = res.data;
        this.defaultCourses = res.data;
      });
    }
  },
  created() {
    this.initData();
  },
}
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.manage-course {
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
