<template>
  <div class="log">
    <el-container>
      <el-header>
        <el-row>
          <el-col :span="2" class="back">
            <i class="el-icon-d-arrow-left" @click="back"></i>
          </el-col>
          <el-col :span="20" style="text-align: center">
            <h1>Check the Log</h1>
          </el-col>
        </el-row>
      </el-header>
      <el-main>
        
      </el-main>
    </el-container>
  </div>
</template>

<script>
// import axios from "axios";

export default {
  name: "AddAlbum",
  
  methods: {
    back() {
      this.$router.back(-1);
    },
    
  },
};
</script>

<style scoped>
.log {
  height: 100%;
}

.el-container {
  background-color: #F0DAD2;
  height:100%;
}

.item {
  margin-bottom: 10px;
}

.back {
  cursor: pointer;
}
</style>