<template>
  <div class="modify-album">
    <el-container>
      <el-header>
        <el-row>
          <el-col :span="2" class="back">
            <i class="el-icon-d-arrow-left" @click="back"></i>
          </el-col>
          <el-col :span="20" style="text-align:center">
            <h1>Modify Album Information</h1>
          </el-col>
        </el-row>
      </el-header>
      <el-main>
        <div class="item"> 
          <el-input :placeholder="album.code" v-model="album.code">
            <template slot="prepend">Code : </template>
          </el-input>
        </div>
        <div class="item"> 
          <el-input :placeholder="album.year" v-model="album.year">
            <template slot="prepend">Year : </template>
          </el-input>
        </div>
        <div class="item">
          <el-input :placeholder="album.title" v-model="album.title">
            <template slot="prepend">Title : </template>
          </el-input>
        </div>
        <div class="item">
          <el-input :placeholder="album.author" v-model="album.author">
            <template slot="prepend">Author : </template>
          </el-input>
        </div>
        <div class="item">
          <el-input :placeholder="album.description" v-model="album.description">
            <template slot="prepend">Description : </template>
          </el-input>
        </div>
        <div class="item"> 
          <el-input :placeholder="album.cover" v-model="album.cover">
            <template slot="prepend">Cover : </template>
          </el-input>
        </div>
        <div class="item">
          <el-button type="success" plain @click="modify">Modify</el-button>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'ModifyAlbum',
  data() {
    return {
      code: null,
      album: {},
      result: ""
    }
  },
  created() {
    this.code = this.$route.params.code;
    axios.get(`http://localhost:8866/albums/${this.code}`).then((res) => {
      this.album = res.data;
    });
  },
  methods: {
    back() {
      this.$router.back(-1);
    },
    async modify() {
      await axios.put(`http://localhost:8866/albums`,this.album).then((res) => {
        console.log(res);
        this.result = res.data;
        console.log(this.result);
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
    }
  },
  
}
</script>

<style scoped>
.modify-album {
  height: 100%;
}

.el-container {
  background-color: #DDF3FF;
  height:100%;
}

.item {
  margin-bottom: 10px;
}

.back {
  cursor: pointer;
}
</style>