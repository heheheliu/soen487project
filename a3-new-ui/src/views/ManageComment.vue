<template>
  <div class="manage-comment">
    <el-container>
      <el-header style="text-align:center;height:150px">
        <h1>Manage Comments List</h1>
        <div style="position:absolute;top:10px;right:10px;"><el-button @click="logOut">Log Out</el-button></div>
        <div style="position:absolute;top:10px;left:10px;"><el-button @click="back">Courses</el-button></div>
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
              <el-button icon="el-icon-document-add" circle size="mini" ></el-button>
              <el-button icon="el-icon-document" circle size="mini"></el-button>
            </div>
          </el-col>
        </el-row>
      </el-header>
      <el-main>
        <div v-if="commentList.length>0">
          <el-row v-for="(c,index) in commentList" :key="c.comment+index">
            <el-card shadow="hover" style="height:auto">
              <el-row>
                <el-col :span="18">
                  <div><h1>{{c.courseNum}}</h1></div>
                  <div>User Name: {{c.username}} </div>
                  <div>Comment: {{c.comment}} </div>                
                </el-col>
                <el-col :span="2">                
                </el-col>
                <el-col :span="4">
                  <div style="padding:0px;margin:0px;width:30px;top:0;right:0;position:absolute;">
                    <div><el-button type="danger" icon="el-icon-delete" circle size="mini" @click="deleteComment(c.username, c.courseNum, c.comment)"></el-button></div>
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
  name: 'ManageComment',
  data() {
    return {
      searchValue: "",
      result: "",
      enroll: [],
      courses: [],
      courseNum: "",
      comment: "",
      commentList: [],
      defaultComList: []
    }
  },
  methods: {
    doSearch() {
      if(this.searchValue) {
        this.commentList = this.commentList.filter(comment => comment.username.includes(this.searchValue)||comment.comment.includes(this.searchValue)||comment.courseNum.includes(this.searchValue));
      } else {
        this.commentList = this.defaultComList;
      }
    },
    logOut() {
      localStorage.clear();
      this.$router.push('/');
    },
    back() {
      this.$router.push('/Managecourse');
    },
    async deleteComment(username, classId, comment) {
      await axios.delete('http://localhost:8899/courses/deleteComment/'+username+'/'+classId+'/'+comment,
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
    initData() {
      axios.get('http://localhost:8899/courses/getAllComment',
        {
          headers: {
            'token': localStorage.getItem('token'),
          }
        })
        .then(res => {
          console.log(res);
          this.commentList = res.data;
          this.defaultComList = res.data;
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
.manage-comment {
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
