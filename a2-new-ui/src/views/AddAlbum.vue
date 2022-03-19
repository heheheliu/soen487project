<template>
  <div class="add-album">
    <el-container>
      <el-header>
        <el-row>
          <el-col :span="2" class="back">
            <i class="el-icon-d-arrow-left" @click="back"></i>
          </el-col>
          <el-col :span="20" style="text-align: center">
            <h1>Add Album Information</h1>
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
          <el-input
            :placeholder="album.description"
            v-model="album.description"
          >
            <template slot="prepend">Description : </template>
          </el-input>
        </div>
        <div class="item">
          <el-input :placeholder="album.cover" v-model="album.cover">
            <template slot="prepend">Cover : </template>
          </el-input>
        </div>
        <div class="item">
          <el-button type="success" plain @click="add">Add</el-button>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "AddAlbum",
  data() {
    return {
      album: {
        code: null,
        title: "",
        description: "",
        year: 0,
        author: "",
        cover: null,
      },
      result: "",
    };
  },
  methods: {
    back() {
      this.$router.back(-1);
    },
    async add() {
      if (this.album.code) {
        await axios
          .post(`http://localhost:8866/albums`, this.album)
          .then((res) => {
            console.log(res);
            this.result = res.data;
            console.log(this.result);
          });
        if (this.result) {
          this.$notify({
            title: "Success",
            message: "Add success",
            type: "success",
          });
        } else {
          this.$notify.error({
            title: "Error",
            message: "Add failed",
          });
        }
      } else {
        this.$notify.error({
          title: "Error",
          message: "Miss some information",
        });
      }
    },
  },
};
</script>

<style scoped>
.add-album {
  height: 100%;
}

.el-container {
  background-color: #E8E8FF;
  height:100%;
}

.item {
  margin-bottom: 10px;
}

.back {
  cursor: pointer;
}
</style>