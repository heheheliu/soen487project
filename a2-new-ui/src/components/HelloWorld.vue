<template>
  <div class="hello">
    <el-container>
      <el-header style="text-align:center;height:150px">
        <h1>{{ msg }}</h1>
        <el-row>
          <el-col :span="12" :offset="6">
            <el-input
              placeholder="Enter title of Album"
              prefix-icon="el-icon-search"
              v-model="searchValue"
              @input="doSearch">
            </el-input>
          </el-col>
          <el-col :span="6">
            <div style="padding:0px;margin:0px;width:70px;top:15px;right:15px;position:absolute;">
              <el-button icon="el-icon-document-add" circle size="mini" @click="addAlbum"></el-button>
              <el-button icon="el-icon-document" circle size="mini" @click="checkLog"></el-button>
            </div>
          </el-col>
        </el-row>
      </el-header>
      <el-main>
        <div v-if="albums.length>0">
          <el-row v-for="album in albums" :key="album.code">
            <el-card shadow="hover" style="height:160px">
              <el-row>
                <el-col :span="6">
                  <div>No: {{album.code}} </div>
                  <div>Tilte: {{album.title}} </div>
                  <div>Author: {{album.author}} </div>
                  <div>Description: {{album.description}}</div>
                </el-col>
                <el-col :span="14">
                  <div v-if="album.image">
                    hello
                  </div>
                  <div v-else><el-empty :image-size="50" description="no image" style="padding:0px"></el-empty></div>
                </el-col>
                <el-col :span="4">
                  <div style="padding:0px;margin:0px;width:30px;top:0;right:0;position:absolute;">
                    <div><el-button type="success" icon="el-icon-edit" circle size="mini" @click="modifyAlbum(album.code)"></el-button></div>
                    <div><el-button type="danger" icon="el-icon-delete" circle size="mini" @click="deleteAlbum(album.code)"></el-button></div>
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
  name: 'HelloWorld',
  props: {
    msg: String
  },
  data() {
    return {
      defaultAlbums: [],
      albums: [],
      searchValue: "",
      result: ""
    }
  },
  methods: {
    doSearch() {
      if(this.searchValue) {
        this.albums = this.albums.filter(album => album.title.includes(this.searchValue));
      } else {
        this.albums = this.defaultAlbums;
      }
    },
    modifyAlbum(code) {
      this.$router.push({path:`/modify/${code}`});
    },
    addAlbum() {
      this.$router.push({path:`/add`});
    },
    checkLog() {
      this.$router.push({path:`/log`});
    },
    async deleteAlbum(code) {
      await axios.delete(`http://localhost:8866/albums/${code}`).then((res)=>{
        this.result = res.data;
        console.log(res);
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
    
    // let xmlBody = `
    // <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
    //               xmlns:us="http://www.soen487.com/log-ws">
    // <soapenv:Header/>
    // <soapenv:Body>
    //     <us:getChangeLogsRequest>
    //         <us:from>2020</us:from>
    //         <us:to>2028</us:to>
    //         <us:type>create</us:type>
    //     </us:getChangeLogsRequest>
    // </soapenv:Body>
    // </soapenv:Envelope>
    // `;
    // axios.post(
    //   "http://localhost:8866/log/",
    //   xmlBody,
    //   {
    //     headers: {
    //       "Content-Type": "text/xml"
    //     }
    //   }
    // ).then((res)=>{
    //   console.log(res);
    // });
  },
}
</script>
<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.hello {
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
