<template>
  <div class="book">
    <el-container>
      <el-header style="text-align:center;height:150px">
        <h1>{{courseNum}} - Books List</h1>
        <div style="position:absolute;top:10px;right:10px;"><el-button @click="logOut">Log Out</el-button></div>
        <div style="position:absolute;top:10px;left:10px;"><el-button @click="back">Back</el-button></div>
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
            <!-- <div style="padding:0px;margin:0px;width:70px;top:15px;right:15px;position:absolute;">
              <el-button icon="el-icon-document-add" circle size="mini" ></el-button>
              <el-button icon="el-icon-document" circle size="mini" ></el-button>
            </div> -->
          </el-col>
        </el-row>
      </el-header>
      <el-main>
        <div v-if="bookList.length>0">
          <el-row v-for="(b,index) in bookList" :key="b.volumeInfo.title+index">
            <el-card shadow="hover" style="height:auto">
              <el-row>
                <el-col :span="16">
                  <div>Book Title: {{b.volumeInfo.title}} </div>
                  <div>Author: {{b.volumeInfo.authors[0]}} </div> 
                  <div>Rating: {{b.volumeInfo.averageRating}} </div>
                  <div>Language: {{b.volumeInfo.language}} </div> 
                  <div>Page Count: {{b.volumeInfo.pageCount}} </div>
                  <div>Publisher: {{b.volumeInfo.publisher}} </div> 
                  <div>Publish Date: {{b.volumeInfo.publishedDate}} </div> 
                  <div>Description: {{b.volumeInfo.description}}</div>               
                </el-col>
                <el-col :span="4"> 
                  <img :src="b.volumeInfo.imageLinks.smallThumbnail" width="230px" height="350px" style="margin-left:15px;">              
                </el-col>
                <el-col :span="4">
                  <div style="padding:0px;margin:0px;width:30px;top:0;right:0;position:absolute;">
                    <!-- <div><el-button type="success" icon="el-icon-edit" circle size="mini" ></el-button></div>
                    <div><el-button type="danger" icon="el-icon-delete" circle size="mini" ></el-button></div> -->
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
  name: 'BookView',
  data() {
    return {
      searchValue: "",
      courses: [],
      courseNum: "",
      comment: "",
      bookList: [],
      defaultBookList: [],
      bookKeyWord: ""
    }
  },
  methods: {
    doSearch() {
      if(this.searchValue) {
        this.bookList = this.bookList.filter(book => book.volumeInfo.authors[0].includes(this.searchValue)||book.volumeInfo.title.includes(this.searchValue)||book.volumeInfo.description.includes(this.searchValue));
      } else {
        this.bookList = this.defaultBookList;
      }
    },
    logOut() {
      localStorage.clear();
      this.$router.push('/');
    },
    back() {
      this.$router.push('/Courseview');
    },
    initData() {
      let storeCourses = JSON.parse(localStorage.getItem('courses'));
      for(let j=0;j<storeCourses.length;j++) {
        if(storeCourses[j].courseNum == this.courseNum) {
          this.bookKeyWord = storeCourses[j].keyWord;
        }
      }
      axios.get('http://localhost:8899/courses/searchGoogleBook/'+this.bookKeyWord,
        {
          headers: {
            'token': localStorage.getItem('token'),
          }
        })
        .then(res => {
          this.bookList = res.data.items;
          this.defaultBookList = res.data.items;
      });
    }
  },
  created() {
    this.courseNum = this.$route.params.code;
    this.initData();
  },
}
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.book {
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
